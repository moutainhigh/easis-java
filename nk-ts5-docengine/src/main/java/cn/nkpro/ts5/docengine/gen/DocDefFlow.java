package cn.nkpro.ts5.docengine.gen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocDefFlow extends DocDefFlowKey implements Serializable {
    private String refObjectType;

    private String state;

    private Long updatedTime;

    private Integer orderBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_doc_def_flow
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getRefObjectType() {
        return refObjectType;
    }

    public void setRefObjectType(String refObjectType) {
        this.refObjectType = refObjectType;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }
}