package cn.nkpro.easis.docengine.gen;

import java.io.Serializable;

public class DocDefIWithBLOBs extends DocDefI implements Serializable {
    private String cardContent;

    private String markdown;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_def_i
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getCardContent() {
        return cardContent;
    }

    public void setCardContent(String cardContent) {
        this.cardContent = cardContent;
    }

    @cn.nkpro.easis.annotation.CodeFieldNotes("")
    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }
}