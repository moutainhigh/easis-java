package cn.nkpro.easis.docengine.model.es;

import cn.nkpro.easis.data.elasticearch.AbstractESModel;
import cn.nkpro.easis.data.elasticearch.ESAnalyzerType;
import cn.nkpro.easis.data.elasticearch.ESFieldType;
import cn.nkpro.easis.data.elasticearch.annotation.ESField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by bean on 2020/7/6.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractBaseES extends AbstractESModel {

    @ESField(type= ESFieldType.Keyword)
    private String classify;

    @ESField(type= ESFieldType.Keyword)
    private String docType;

    @ESField(type= ESFieldType.Keyword)
    private String docTypeDesc;

    @ESField(type= ESFieldType.Keyword)
    private String docNumber;

    @ESField(type= ESFieldType.Keyword)
    private String docState;

    @ESField(type= ESFieldType.Keyword)
    private String docStateDesc;

    @ESField(type= ESFieldType.Integer)
    private Integer docDefVersion;

    @ESField(type= ESFieldType.Date, format = "epoch_second||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd")
    private Long createdTime;

    @ESField(type= ESFieldType.Date, format = "epoch_second||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd")
    private Long updatedTime;

    @ESField(type= ESFieldType.Text,analyzer = ESAnalyzerType.ik_max_word, copyToKeyword = true, original = true)
    private String docName;

    @ESField(type= ESFieldType.Keyword)
    private String preDocId;

    @ESField(type= ESFieldType.Keyword)
    private String[] tags;

    @ESField(type= ESFieldType.Keyword)
    private String partnerId;

    @ESField(type= ESFieldType.Keyword)
    private String processInstanceId;

    @ESField(type= ESFieldType.Text,analyzer = ESAnalyzerType.standard, copyToKeyword = true, original = true)
    private String partnerName;
}
