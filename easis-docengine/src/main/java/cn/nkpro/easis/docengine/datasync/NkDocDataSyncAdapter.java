package cn.nkpro.easis.docengine.datasync;

import cn.nkpro.easis.docengine.gen.DocDefDataSync;

import java.util.Map;

public interface NkDocDataSyncAdapter<K> extends NkDocDataAdapter {

    default void onInsert(Map<K, Map<String, Object>> listMapped, DocDefDataSync config){}
    default void onModify(Map<K, Map<String, Object>> listMapped, DocDefDataSync config){}
    default void onRemove(Map<K, Map<String, Object>> listMapped, DocDefDataSync config){}
}
