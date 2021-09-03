package cn.nkpro.ts5.docengine.gen;

import cn.nkpro.ts5.annotation.CodeFieldNotes;

import java.io.Serializable;

public class DocDefStateKey implements Serializable {
    private String docState;

    private String docType;

    private String preDocState;

    private String version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_def_state
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    @CodeFieldNotes("")
    public String getDocState() {
        return docState;
    }

    public void setDocState(String docState) {
        this.docState = docState;
    }

    @CodeFieldNotes("")
    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    @CodeFieldNotes("")
    public String getPreDocState() {
        return preDocState;
    }

    public void setPreDocState(String preDocState) {
        this.preDocState = preDocState;
    }

    @CodeFieldNotes("")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}