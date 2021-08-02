package cn.nkpro.ts5.engine.doc.interceptor;

import cn.nkpro.ts5.engine.co.NkCustomScriptObject;
import cn.nkpro.ts5.engine.doc.model.DocHV;
import lombok.Getter;

@SuppressWarnings("unused")
public interface NkDocFlowInterceptor extends NkCustomScriptObject {

    default FlowDescribe apply(DocHV docHV){return FlowDescribe.visible();}

    class FlowDescribe{

        @Getter
        private boolean visible;

        @Getter
        private String visibleDesc;

        private FlowDescribe(boolean visible,String visibleDesc){
            this.visible = visible;
            this.visibleDesc = visibleDesc;
        }

        @SuppressWarnings("all")
        public static FlowDescribe visible(){
            return new FlowDescribe(true,null);
        }
        public static FlowDescribe invisible(String visibleDesc){
            return new FlowDescribe(false,visibleDesc);
        }
    }
}
