package cn.nkpro.easis.docengine.interceptor;

import cn.nkpro.easis.co.NkCustomObject;
import cn.nkpro.easis.docengine.model.DocHV;
import cn.nkpro.easis.docengine.service.impl.NkDocCycleContext;

public interface NkDocCycleInterceptor extends NkCustomObject {

//    default DocHV beforeCreate(DocHV doc, DocHV ref){return doc;}
//    default DocHV afterCreated(DocHV doc, DocHV ref){return doc;}
//    default DocHV afterCopied(DocHV doc, DocHV ref){return doc;}
//
//    default DocHV beforeCalculate(DocHV doc){return doc;}
//    default DocHV afterCalculated(DocHV doc){return doc;}
//
//    default DocHV beforeUpdate(DocHV doc, DocHV original){return doc;}
//    default DocHV afterUpdated(DocHV doc, DocHV original){return doc;}
//    @Transactional(propagation = Propagation.NEVER)
//    default void afterUpdateCommit(DocHV doc){}
//
//    default DocHV beforeDelete(DocHV doc, DocHV original){return doc;}
//    default DocHV afterDeleted(DocHV doc, DocHV original){return doc;}
//    @Transactional(propagation = Propagation.NEVER)
//    default void afterDeleteCommit(DocHV doc){}

    default void apply(DocHV doc, NkDocCycleContext context){}
}
