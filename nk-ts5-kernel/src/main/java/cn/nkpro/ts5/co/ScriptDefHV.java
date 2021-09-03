package cn.nkpro.ts5.co;

import cn.nkpro.ts5.basic.Keep;
import cn.nkpro.ts5.co.gen.ScriptDefHWithBLOBs;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude()
@Keep
public class ScriptDefHV extends ScriptDefHWithBLOBs {
    private boolean debug;
}
