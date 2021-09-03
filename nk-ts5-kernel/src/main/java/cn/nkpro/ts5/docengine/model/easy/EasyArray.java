package cn.nkpro.ts5.docengine.model.easy;

import cn.nkpro.ts5.exception.NkOperateNotAllowedCaution;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class EasyArray extends EasyList{

    EasyArray(Object[] target) {
        this.easies = Arrays.stream(target).map(EasySingle::from).collect(Collectors.toList());
    }

    @Override
    public EasySingle append(int index){
        throw new NkOperateNotAllowedCaution("数组元数据不支持的添加、移除操作");
    }
    @Override
    public EasySingle append(){
        throw new NkOperateNotAllowedCaution("数组元数据不支持的添加、移除操作");
    }

    @Override
    public boolean add(EasySingle e) {
        throw new NkOperateNotAllowedCaution("不支持的操作");
    }

    @Override
    public boolean addAll(Collection<? extends EasySingle> c) {
        throw new NkOperateNotAllowedCaution("不支持的操作");
    }

    @Override
    public boolean remove(Object o) {
        throw new NkOperateNotAllowedCaution("数组元数据不支持的添加、移除操作");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new NkOperateNotAllowedCaution("不支持的操作");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NkOperateNotAllowedCaution("不支持的操作");
    }

    @Override
    public void clear() {
        throw new NkOperateNotAllowedCaution("数组元数据不支持的添加、移除操作");
    }
}
