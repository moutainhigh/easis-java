package cn.nkpro.ts5.docengine.gen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocH implements Serializable {
    private String docId;

    private String classify;

    private String defVersion;

    private String docType;

    private String docName;

    private String docDesc;

    private String docNumber;

    private String docState;

    private String docTags;

    private String preDocId;

    /**
     * 交易伙伴ID（注意是roleID）
     *
     * @mbggenerated
     */
    private String partnerId;

    private String identification;

    private String refObjectId;

    private String businessKey;

    private String processInstanceId;

    private Long createdTime;

    private Long updatedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_h
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getDefVersion() {
        return defVersion;
    }

    public void setDefVersion(String defVersion) {
        this.defVersion = defVersion;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getDocDesc() {
        return docDesc;
    }

    public void setDocDesc(String docDesc) {
        this.docDesc = docDesc;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getDocState() {
        return docState;
    }

    public void setDocState(String docState) {
        this.docState = docState;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getDocTags() {
        return docTags;
    }

    public void setDocTags(String docTags) {
        this.docTags = docTags;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getPreDocId() {
        return preDocId;
    }

    public void setPreDocId(String preDocId) {
        this.preDocId = preDocId;
    }

    /**
     * 获取 交易伙伴ID（注意是roleID）
     *
     * @return 交易伙伴ID（注意是roleID）
     *
     * @mbggenerated
     */
    @cn.nkpro.ts5.annotation.CodeFieldNotes("交易伙伴ID（注意是roleID）")
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * 设置 交易伙伴ID（注意是roleID）
     *
     * @return 交易伙伴ID（注意是roleID）
     *
     * @mbggenerated
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getRefObjectId() {
        return refObjectId;
    }

    public void setRefObjectId(String refObjectId) {
        this.refObjectId = refObjectId;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }
}