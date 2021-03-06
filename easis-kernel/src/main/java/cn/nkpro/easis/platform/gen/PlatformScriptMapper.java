package cn.nkpro.easis.platform.gen;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PlatformScriptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int countByExample(PlatformScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(PlatformScriptKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int insert(PlatformScriptWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int insertSelective(PlatformScriptWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    List<PlatformScriptWithBLOBs> selectByExampleWithBLOBs(PlatformScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    List<PlatformScript> selectByExample(PlatformScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    PlatformScriptWithBLOBs selectByPrimaryKey(PlatformScriptKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PlatformScriptWithBLOBs record, @Param("example") PlatformScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") PlatformScriptWithBLOBs record, @Param("example") PlatformScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PlatformScript record, @Param("example") PlatformScriptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PlatformScriptWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(PlatformScriptWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PlatformScript record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    List<PlatformScript> selectByExample(PlatformScriptExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_platform_script
     *
     * @mbggenerated
     */
    List<PlatformScriptWithBLOBs> selectByExampleWithBLOBs(PlatformScriptExample example, RowBounds rowBounds);
}