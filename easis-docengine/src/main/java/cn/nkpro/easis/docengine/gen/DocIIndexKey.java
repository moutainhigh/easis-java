package cn.nkpro.easis.docengine.gen;

import java.io.Serializable;

public class DocIIndexKey implements Serializable {
    private String docId;

    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_i_index
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}