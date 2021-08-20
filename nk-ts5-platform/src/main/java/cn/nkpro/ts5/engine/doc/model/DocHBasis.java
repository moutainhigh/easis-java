package cn.nkpro.ts5.engine.doc.model;

import cn.nkpro.ts5.utils.BeanUtilz;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 单据数据对象，基础格式
 */
@EqualsAndHashCode(callSuper = false)
@Data
class DocHBasis extends DocHPersistent implements Cloneable {

    private String docTypeDesc;

    private String docStateDesc;

    private DocDefHV def;

    private Map<String,Object> data;

    private Map<String,Object> dynamics;

    DocHBasis() {
        super();
        this.data       = new HashMap<>();
        this.dynamics   = new HashMap<>();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DocHBasis clone = (DocHBasis) super.clone();
        clone.setDocTypeDesc(docTypeDesc);
        clone.setDocStateDesc(docStateDesc);
        clone.setDef(def);
        clone.dynamics = new HashMap<>(dynamics);
        clone.data     = new HashMap<>();
        data.forEach((k,v)-> clone.data.put(k,BeanUtilz.cloneWithFastjson(v)));

        return clone;
    }
}
