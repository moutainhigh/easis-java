package cn.nkpro.ts5.model.mb.gen;

import java.io.Serializable;

public class DefDocTypeKey implements Serializable {
    private String docType;

    private Integer version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table def_doc_type
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    @cn.nkpro.ts5.basic.wsdoc.annotation.CodeFieldNotes("")
    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    @cn.nkpro.ts5.basic.wsdoc.annotation.CodeFieldNotes("")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}