package cn.nkpro.easis.docengine.model;

import cn.nkpro.easis.docengine.gen.DocDefFlow;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude()
public class DocDefFlowV extends DocDefFlow {
    private String classify;
    private String docName;
    private boolean visible;
    private String visibleDesc;
}
