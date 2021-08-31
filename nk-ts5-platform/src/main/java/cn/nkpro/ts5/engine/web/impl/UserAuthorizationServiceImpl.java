package cn.nkpro.ts5.engine.web.impl;

import cn.nkpro.ts5.basic.Constants;
import cn.nkpro.ts5.config.id.GUID;
import cn.nkpro.ts5.config.redis.RedisSupport;
import cn.nkpro.ts5.config.security.NkGrantedAuthority;
import cn.nkpro.ts5.engine.doc.model.DocHV;
import cn.nkpro.ts5.engine.spel.TfmsSpELManager;
import cn.nkpro.ts5.engine.web.UserAuthorizationService;
import cn.nkpro.ts5.engine.web.model.UserGroupBO;
import cn.nkpro.ts5.orm.mb.gen.*;
import cn.nkpro.ts5.utils.BeanUtilz;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserAuthorizationServiceImpl implements UserAuthorizationService {

    @Autowired@SuppressWarnings("all")
    private GUID guid;
    @Autowired@SuppressWarnings("all")
    private SysAuthGroupMapper authGroupMapper;
    @Autowired@SuppressWarnings("all")
    private SysAuthGroupRefMapper authGroupRefMapper;
    @Autowired@SuppressWarnings("all")
    private SysAuthPermissionMapper authPermissionMapper;
    @Autowired@SuppressWarnings("all")
    private SysAuthLimitMapper authLimitMapper;
    @Autowired@SuppressWarnings("all")
    private SysAccountMapper accountMapper;

    @Autowired@SuppressWarnings("all")
    private RedisSupport<UserGroupBO> redisSupport;
    @Autowired@SuppressWarnings("all")
    private RedisSupport<SysAuthLimit> redisSupportLimit;
    @Autowired@SuppressWarnings("all")
    private TfmsSpELManager spELManager;


    /**
     * 创建指定账号的权限集合
     * @param accountId 账号ID
     * @return List<NkGrantedAuthority>
     */
    @Override
    public List<NkGrantedAuthority> buildGrantedPerms(String accountId, String partnerId){

        //DocHV partner = StringUtils.isNotBlank(partnerId)?docEngine.getDocDetail(partnerId):new DocHV();
        DocHV partner = new DocHV();

        // 构造权限列表
        List<NkGrantedAuthority> permList = new ArrayList<>();

        SysAuthGroupRefExample authGroupRefExample = new SysAuthGroupRefExample();
        authGroupRefExample.createCriteria()
                .andRefIdEqualTo(accountId)
                .andRefTypeEqualTo(GROUP_TO_ACCOUNT);

        authGroupRefMapper.selectByExample(authGroupRefExample)
                .forEach((ref->{
                    UserGroupBO group = buildUserGroup(ref.getGroupId());
                    if(group!=null&&group.getAuthorities()!=null){
                        permList.addAll(group.getAuthorities());
                    }
                }));

        // 处理limit
        Set<String> limitIds = new HashSet<>();
        permList.forEach(tfmsGrantedAuthority -> {
            if(tfmsGrantedAuthority.getLimitIds()!=null){
                limitIds.addAll(Arrays.asList(tfmsGrantedAuthority.getLimitIds()));
            }
        });

        Map<String,SysAuthLimit> limits = limitIds.stream()
                .map(limitId->
                    redisSupportLimit.getIfAbsent(Constants.CACHE_AUTH_LIMIT,limitId,
                            ()-> authLimitMapper.selectByPrimaryKey(limitId))
                ).collect(Collectors.toMap(SysAuthLimit::getLimitId,v->v));

        permList.forEach(authority -> {
            if(authority.getLimitIds()!=null){
                List<String> querys = Arrays.stream(authority.getLimitIds())
                        .map(limits::get)
                        .filter(limit->limit!=null && limit.getContent()!=null)
                        .map(SysAuthLimit::getContent)
                        .map(limit->spELManager.convert(limit,partner))
                        .collect(Collectors.toList());
                if(querys.size()>1){
                    authority.setLimitQuery(
                            querys.stream()
                                    .collect(Collectors.joining(",", "{\"bool\":{\"must\":[", "]}}"))
                    );
                }else if(querys.size()==1){
                    authority.setLimitQuery(querys.get(0));
                }
            }
        });

        return permList.stream()
                    .sorted()
                    .collect(Collectors.toList());
    }

    /**
     * 创建一个用户组模型
     * @param groupId 用户组Id
     * @return 用户组
     */
    private UserGroupBO buildUserGroup(String groupId){

        return redisSupport.getIfAbsent(Constants.CACHE_AUTH_GROUP,groupId,()->{

            SysAuthGroup sysAuthGroup = authGroupMapper.selectByPrimaryKey(groupId);

            if(sysAuthGroup!=null){

                UserGroupBO g = BeanUtilz.copyFromObject(sysAuthGroup, UserGroupBO.class);

                SysAuthGroupRefExample authGroupRefExample = new SysAuthGroupRefExample();
                authGroupRefExample.createCriteria()
                        .andGroupIdEqualTo(groupId)
                        .andRefTypeEqualTo(GROUP_TO_PERM);

                List<String> permIds = authGroupRefMapper.selectByExample(authGroupRefExample)
                        .stream()
                        .map(SysAuthGroupRefKey::getRefId)
                        .collect(Collectors.toList());

                if(!permIds.isEmpty()){

                    // 查询Group下的权限定义
                    SysAuthPermissionExample authPermissionExample = new SysAuthPermissionExample();
                    authPermissionExample.createCriteria()
                            .andPermIdIn(permIds);
                    authPermissionExample.setOrderByClause("PERM_DESC");

                    g.setPermissions(authPermissionMapper.selectByExampleWithBLOBs(authPermissionExample));

                    // 创建Group下的授权模型
                    List<NkGrantedAuthority> authoritys = new ArrayList<>();
                    g.getPermissions()
                        .forEach(permission -> {
                            if(StringUtils.startsWith(permission.getPermResource(),Constants.BIZ_DEFAULT_EMPTY)
                                    &&StringUtils.containsAny(permission.getPermResource(),'|',',')){
                                // 多单据权限
                                authoritys.addAll(
                                    Arrays.stream(
                                        permission.getPermResource().substring(1).split("[|,]")
                                    )
                                    .map(resource->
                                        buildAuthority(StringUtils.join(Constants.BIZ_DEFAULT_EMPTY,resource),permission,g)
                                    )
                                    .collect(Collectors.toList())
                                );
                            }else{
                                // 单单据及其他权限
                                authoritys.add(
                                    buildAuthority(permission.getPermResource(),permission,g)
                                );
                            }
                        });

                    g.setAuthorities(authoritys.stream().sorted().collect(Collectors.toList()));
                }
                return g;
            }
            return null;
        });
    }

    /**
     * 创建一个授权对象
     * @param resource resource
     * @param perm perm
     * @param group group
     * @return NkGrantedAuthority
     */
    private NkGrantedAuthority buildAuthority(String resource, SysAuthPermission perm, SysAuthGroup group){
        NkGrantedAuthority authority = new NkGrantedAuthority();
        authority.setPermResource(resource);
        authority.setPermOperate(perm.getPermOperate());
        authority.setSubResource(perm.getSubResource());
        authority.setLimitIds(StringUtils.split(perm.getLimitId(),'|'));
        authority.setLevel(perm.getPermLevel());
        authority.setFromPermissionId(perm.getPermId());
        authority.setFromPermissionDesc(perm.getPermDesc());
        authority.setFromGroupId(group.getGroupId());
        authority.setFromGroupDesc(group.getGroupDesc());
        authority.setAuthority(String.format("%s:%s",authority.getPermResource(),authority.getPermOperate()));
        return authority;
    }

    //// 以下为管理代码

    @Override
    public List<SysAuthLimit> getLimits(String[] limitIds){
        SysAuthLimitExample example = new SysAuthLimitExample();
        if(ArrayUtils.isNotEmpty(limitIds))
            example.createCriteria().andLimitIdIn(Arrays.asList(limitIds));
        example.setOrderByClause("LIMIT_DESC");
        return authLimitMapper.selectByExample(example);
    }

    @Override
    public SysAuthLimit getLimitDetail(String limitId){
        if(StringUtils.isNotBlank(limitId)){
            return redisSupportLimit.getIfAbsent(Constants.CACHE_AUTH_LIMIT,limitId,
                ()-> authLimitMapper.selectByPrimaryKey(limitId));
        }
        return null;
    }
    @Override
    public void updateLimit(SysAuthLimit limit){
        Assert.hasText(limit.getLimitDesc(),"限制描述不能为空");

        QueryBuilders.wrapperQuery(limit.getContent());

        if(StringUtils.isBlank(limit.getLimitId())){
            limit.setLimitId(guid.nextId(SysAuthLimit.class));
            authLimitMapper.insert(limit);
        }else{
            authLimitMapper.updateByPrimaryKeyWithBLOBs(limit);
            redisSupportLimit.deleteHash(Constants.CACHE_AUTH_LIMIT,limit.getLimitId());
        }
    }
    @Override
    public void removeLimit(String limitId){
        authLimitMapper.deleteByPrimaryKey(limitId);
        redisSupportLimit.deleteHash(Constants.CACHE_AUTH_LIMIT,limitId);
    }


    @Override
    public List<SysAuthPermission> getPerms(){
        SysAuthPermissionExample example = new SysAuthPermissionExample();
        example.setOrderByClause("PERM_DESC");
        return authPermissionMapper.selectByExample(example);
    }

    @Override
    public SysAuthPermission getPermDetail(String permId){
        return authPermissionMapper.selectByPrimaryKey(permId);
    }
    @Override
    public void updatePerm(SysAuthPermission perm){
        Assert.hasText(perm.getPermDesc(),"权限描述不能为空");
        Assert.hasText(perm.getPermResource(),"权限资源不能为空");
        Assert.hasText(perm.getPermOperate(),"权限操作不能为空");

        if(StringUtils.isNotBlank(perm.getLimitId())){
            perm.setLimitId(
                Arrays.stream(StringUtils.split(perm.getLimitId(),'|'))
                    .sorted()
                    .collect(Collectors.joining("|"))
            );
        }

        if(StringUtils.isBlank(perm.getPermId())){
            perm.setPermId(guid.nextId(SysAuthPermission.class));
            authPermissionMapper.insert(perm);
        }else{
            authPermissionMapper.updateByPrimaryKeyWithBLOBs(perm);
            clearGroupByPerm(perm.getPermId());
        }
    }
    @Override
    public void removePerm(String permId){

        Assert.isTrue(!permId.startsWith("nk-default-"),"系统权限不可移除");

        authPermissionMapper.deleteByPrimaryKey(permId);
        clearGroupByPerm(permId);
    }

    private void clearGroupByPerm(String permId){

        SysAuthGroupRefExample authGroupRefExample = new SysAuthGroupRefExample();
        authGroupRefExample.createCriteria()
                .andRefIdEqualTo(permId)
                .andRefTypeEqualTo(GROUP_TO_PERM);

        Object[] groupIds = authGroupRefMapper.selectByExample(authGroupRefExample)
                .stream()
                .map(SysAuthGroupRefKey::getGroupId)
                .distinct()
                .toArray(String[]::new);

        if(groupIds.length>0){
            redisSupport.deleteHash(Constants.CACHE_AUTH_GROUP,groupIds);
        }

    }



    @Override
    public List<SysAuthGroup> getGroups(){
        SysAuthGroupExample example = new SysAuthGroupExample();
        example.setOrderByClause("GROUP_DESC");
        return authGroupMapper.selectByExample(example);
    }

    @Override
    public UserGroupBO getGroupDetail(String groupId){

        UserGroupBO group = buildUserGroup(groupId);

        // 查询用户组下的账号
        SysAuthGroupRefExample authGroupRefExample = new SysAuthGroupRefExample();
        authGroupRefExample.createCriteria()
                .andGroupIdEqualTo(groupId)
                .andRefTypeEqualTo(GROUP_TO_ACCOUNT);

        List<String> accountIds = authGroupRefMapper.selectByExample(authGroupRefExample)
                .stream()
                .map(SysAuthGroupRefKey::getRefId)
                .collect(Collectors.toList());

        if(!accountIds.isEmpty()){

            SysAccountExample accountExample = new SysAccountExample();
            accountExample.createCriteria()
                    .andIdIn(accountIds);
            accountExample.setOrderByClause("USERNAME");

            group.setAccounts(
                    accountMapper.selectByExample(accountExample)
                            .stream()
                            .peek(a -> a.setPassword(null))
                            .collect(Collectors.toList()));
        }

        return group;
    }
    @Override
    public void updateGroup(UserGroupBO group){


        Assert.isTrue(StringUtils.isBlank(group.getGroupId()) || !group.getGroupId().startsWith("nk-default-"),"系统用户组不可更新");
        Assert.hasText(group.getGroupDesc(),"权限描述不能为空");

        if(StringUtils.isBlank(group.getGroupId())){
            group.setGroupId(guid.nextId(SysAuthGroup.class));
            authGroupMapper.insert(group);
        }else{
            authGroupMapper.updateByPrimaryKey(group);

            SysAuthGroupRefExample example = new SysAuthGroupRefExample();
            example.createCriteria()
                    .andGroupIdEqualTo(group.getGroupId())
                    .andRefTypeEqualTo(GROUP_TO_PERM);
            authGroupRefMapper.deleteByExample(example);
        }

        if(group.getPermissions()!=null){
            group.getPermissions()
                    .forEach(perm->{
                        SysAuthGroupRefKey ref = new SysAuthGroupRefKey();
                        ref.setGroupId(group.getGroupId());
                        ref.setRefId(perm.getPermId());
                        ref.setRefType(GROUP_TO_PERM);
                        authGroupRefMapper.insert(ref);

                    });
        }

        redisSupport.deleteHash(Constants.CACHE_AUTH_GROUP,group.getGroupId());
    }
    @Override
    public void removeGroup(String groupId){

        Assert.isTrue(!groupId.startsWith("nk-default-"),"系统用户组不可删除");

        SysAuthGroupRefExample example = new SysAuthGroupRefExample();
        example.createCriteria().andGroupIdEqualTo(groupId);
        authGroupRefMapper.deleteByExample(example);

        authGroupMapper.deleteByPrimaryKey(groupId);

        redisSupport.deleteHash(Constants.CACHE_AUTH_GROUP,groupId);
    }

    @Override
    public void removeAccountFromGroup(String groupId, String accountId){

        Assert.isTrue(!(groupId.startsWith("nk-default-") && accountId.startsWith("nk-default-")),"系统用户不可移除");

        SysAuthGroupRefExample example = new SysAuthGroupRefExample();
        example.createCriteria()
                .andGroupIdEqualTo(groupId)
                .andRefIdEqualTo(accountId)
                .andRefTypeEqualTo(GROUP_TO_ACCOUNT);
        authGroupRefMapper.deleteByExample(example);
    }

    @Override
    public void addAccountFromGroup(String groupId, String accountId){
        removeAccountFromGroup(groupId,accountId);

        SysAuthGroupRefKey ref = new SysAuthGroupRefKey();
        ref.setGroupId(groupId);
        ref.setRefId(accountId);
        ref.setRefType(GROUP_TO_ACCOUNT);
        authGroupRefMapper.insert(ref);
    }

    @Override
    public List<SysAccount> accounts(String keyword){
        SysAccountExample example = new SysAccountExample();
        example.createCriteria()
                .andUsernameLike(String.format("%%%s%%",keyword));

        return accountMapper.selectByExample(example,new RowBounds(0,10))
                .stream()
                .peek(a -> a.setPassword(null))
                .collect(Collectors.toList());
    }
}
