package cn.nkpro.ts5.docengine.gen;

import cn.nkpro.ts5.annotation.CodeFieldNotes;

import java.io.Serializable;

public class DocDefDataSyncKey implements Serializable {
    private String docType;

    private Integer orderBy;

    private String targetSvr;

    private String version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_def_data_sync
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    @CodeFieldNotes("")
    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    @CodeFieldNotes("")
    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    @CodeFieldNotes("")
    public String getTargetSvr() {
        return targetSvr;
    }

    public void setTargetSvr(String targetSvr) {
        this.targetSvr = targetSvr;
    }

    @CodeFieldNotes("")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}