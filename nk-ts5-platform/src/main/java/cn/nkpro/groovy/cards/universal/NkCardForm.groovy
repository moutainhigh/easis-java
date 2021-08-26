package cn.nkpro.groovy.cards.universal

import cn.nkpro.groovy.cards.universal.ref.NkCardFormDefI
import cn.nkpro.groovy.cards.universal.ref.NkFormCardHelper
import cn.nkpro.ts5.basic.wsdoc.annotation.WsDocNote
import cn.nkpro.ts5.engine.doc.abstracts.NkAbstractCard
import cn.nkpro.ts5.engine.doc.model.DocDefIV
import cn.nkpro.ts5.engine.doc.model.DocHV
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@SuppressWarnings("unused")
@WsDocNote("基础表单")
@Component("NkCardForm")
class NkCardForm extends NkAbstractCard<Map,NkCardFormDef> {

    @Autowired
    private NkFormCardHelper nkFormCardHelper

    @Override
    Map afterCreate(DocHV doc, DocHV preDoc, Map data, DocDefIV defIV, NkCardFormDef d) {
        return nkFormCardHelper.execSpEL(doc, data, defIV, d.getItems(), true, true)
    }

    @Override
    Map afterGetData(DocHV doc, Map data, DocDefIV defIV, NkCardFormDef d) {
        return nkFormCardHelper.execSpEL(doc, data, defIV, d.getItems(), false, true)
    }

    @Override
    Map calculate(DocHV doc, Map data, DocDefIV defIV, NkCardFormDef d, boolean isTrigger, String options) {
        return nkFormCardHelper.execSpEL(doc, data, defIV, d.getItems(), false, true)
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    static class NkCardFormDef {
        private int col
        private List<NkCardFormDefI> items

        int getCol() {
            return col
        }

        void setCol(int col) {
            this.col = col
        }

        List<NkCardFormDefI> getItems() {
            return items
        }

        void setItems(List<NkCardFormDefI> items) {
            this.items = items
        }
    }
}
