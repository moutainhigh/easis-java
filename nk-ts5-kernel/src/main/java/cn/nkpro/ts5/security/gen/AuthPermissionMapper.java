package cn.nkpro.ts5.security.gen;

import cn.nkpro.ts5.security.gen.AuthPermission;
import cn.nkpro.ts5.security.gen.AuthPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AuthPermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int countByExample(AuthPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String permId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int insert(AuthPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int insertSelective(AuthPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    List<AuthPermission> selectByExampleWithBLOBs(AuthPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    List<AuthPermission> selectByExample(AuthPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    AuthPermission selectByPrimaryKey(String permId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AuthPermission record, @Param("example") AuthPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") AuthPermission record, @Param("example") AuthPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AuthPermission record, @Param("example") AuthPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AuthPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(AuthPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AuthPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    List<AuthPermission> selectByExample(AuthPermissionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_permission
     *
     * @mbggenerated
     */
    List<AuthPermission> selectByExampleWithBLOBs(AuthPermissionExample example, RowBounds rowBounds);
}