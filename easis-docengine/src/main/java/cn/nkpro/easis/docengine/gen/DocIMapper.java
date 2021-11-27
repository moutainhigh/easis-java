package cn.nkpro.easis.docengine.gen;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DocIMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int countByExample(DocIExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(DocIKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int insert(DocI record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int insertSelective(DocI record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    List<DocI> selectByExampleWithBLOBs(DocIExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    List<DocI> selectByExample(DocIExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    DocI selectByPrimaryKey(DocIKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DocI record, @Param("example") DocIExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") DocI record, @Param("example") DocIExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DocI record, @Param("example") DocIExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DocI record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(DocI record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DocI record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    List<DocI> selectByExample(DocIExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nk_doc_i
     *
     * @mbggenerated
     */
    List<DocI> selectByExampleWithBLOBs(DocIExample example, RowBounds rowBounds);
}