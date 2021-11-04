package cn.nkpro.ts5.platform.gen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataView implements Serializable {
    private String id;

    private String name;

    private Integer width;

    private Integer height;

    private String theme;

    private String accountId;

    private Integer shared;

    private Long updatedTime;

    private Integer orderBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nk_data_view
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @cn.nkpro.ts5.annotation.CodeFieldNotes("")
    public Integer getShared() {
        return shared;
    }

    public void setShared(Integer shared) {
        this.shared = shared;
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