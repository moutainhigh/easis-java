package cn.nkpro.ts5.engine.doc;

import cn.nkpro.ts5.engine.co.NkCustomObject;
import cn.nkpro.ts5.engine.doc.model.DocDefHV;
import cn.nkpro.ts5.engine.doc.model.DocHBasis;
import cn.nkpro.ts5.engine.doc.model.DocHPersistent;
import cn.nkpro.ts5.engine.doc.model.DocHV;

public interface NkDocProcessor extends NkCustomObject {

    enum EnumDocClassify {
        PARTNER,
        TRANSACTION
    }
    EnumDocClassify classify();

    DocHV calculate(DocHV doc, String fromCard, String options);

    Object call(DocHV doc, String fromCard, String method, String options);

    DocHPersistent deserialize(DocDefHV def, DocHPersistent docHD);

    DocHV detail  (DocDefHV def, DocHPersistent docHD);

    DocHV toCreate(DocDefHV def, DocHV preDoc);

    DocHV doUpdate(DocHV doc, DocHV original, String optSource);

    void doOnBpmKilled(DocHV docHV, String processKey, String optSource);
}
