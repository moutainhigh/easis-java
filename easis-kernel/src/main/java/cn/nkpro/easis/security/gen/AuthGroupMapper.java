package cn.nkpro.easis.security.gen;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AuthGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    int countByExample(AuthGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    int insert(AuthGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    int insertSelective(AuthGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    List<AuthGroup> selectByExample(AuthGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    AuthGroup selectByPrimaryKey(String groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AuthGroup record, @Param("example") AuthGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AuthGroup record, @Param("example") AuthGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AuthGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AuthGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_auth_group
     *
     * @mbggenerated
     */
    List<AuthGroup> selectByExample(AuthGroupExample example, RowBounds rowBounds);
}