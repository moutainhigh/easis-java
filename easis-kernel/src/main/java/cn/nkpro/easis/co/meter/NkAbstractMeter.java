package cn.nkpro.easis.co.meter;

import cn.nkpro.easis.co.NkAbstractCustomScriptObject;
import cn.nkpro.easis.co.NkScriptV;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class NkAbstractMeter<DT> extends NkAbstractCustomScriptObject implements NkMeter {

    @Override
    public Map<String,String> getVueTemplate(){

        NkScriptV scriptDef = scriptDefHV();

        if(scriptDef!=null){
            Map<String,String> vueMap = new HashMap<>();
            if(StringUtils.isNotBlank(scriptDef.getVueMain())){
                vueMap.put(scriptDef.getScriptName(),scriptDef.getVueMain());
            }
            return vueMap;
        }
        return Collections.emptyMap();
    }
}
