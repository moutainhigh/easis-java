package cn.nkpro.easis.docengine.gen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocRecord implements Serializable {
    private String id;

    private String docId;

    private Integer version;

    private String stateOriginal;

    private String stateOriginalDesc;

    private String state;

    private String stateDesc;

    private String cardNames;

    private String userId;

    private String userRealname;

    private String source;

    private String logGroupId;

    private Long updatedTime;

    private String data;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_record
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getStateOriginal() {
        return stateOriginal;
    }

    public void setStateOriginal(String stateOriginal) {
        this.stateOriginal = stateOriginal;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getStateOriginalDesc() {
        return stateOriginalDesc;
    }

    public void setStateOriginalDesc(String stateOriginalDesc) {
        this.stateOriginalDesc = stateOriginalDesc;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getCardNames() {
        return cardNames;
    }

    public void setCardNames(String cardNames) {
        this.cardNames = cardNames;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getLogGroupId() {
        return logGroupId;
    }

    public void setLogGroupId(String logGroupId) {
        this.logGroupId = logGroupId;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}