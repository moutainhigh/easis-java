package cn.nkpro.easis.docengine.cards;

import cn.nkpro.easis.annotation.NkNote;
import cn.nkpro.easis.co.NkCustomObjectManager;
import cn.nkpro.easis.docengine.NkField;
import cn.nkpro.easis.docengine.model.DocDefIV;
import cn.nkpro.easis.docengine.model.DocHV;
import cn.nkpro.easis.co.easy.EasySingle;
import cn.nkpro.easis.docengine.service.NkDocEngineContext;
import cn.nkpro.easis.exception.NkDefineException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 联动表单模式下
 * <li>仅支持部分字段组件
 * <li>由于字段关联的特殊性，SpEL表达式的计算时点强制为Always
 * <li>通过NkFieldRef配置dataMappings   可来设置其他字段的默认值
 * <li>通过NkFieldRef配置optionMappings 可来设置其他字段的input选项
 */
@Order(1002)
@NkNote("联动表单")
@Component("NkLinkageForm")
public class NkLinkageForm extends NkDynamicBase<Map<String,Object>, NkLinkageFormDef> {

    @Autowired
    protected NkCustomObjectManager customObjectManager;

    @Override
    public String getDataComponentName() {
        return "NkLinkageForm";
    }

    @Override
    protected String[] getDefComponentNames() {
        return new String[]{"NkLinkageFormDef"};
    }


    @Override
    public Map<String,Object> afterCreate(DocHV doc, DocHV preDoc, Map<String,Object> data, DocDefIV defIV, NkLinkageFormDef d) {
        this.copyFromPre(preDoc, data, defIV, d.getItems());
        this.processOptions(EasySingle.from(data), doc, d.getItems());
        return super.afterCreate(doc, preDoc, data, defIV, d);
    }

    @Override
    public Map<String,Object> afterGetData(DocHV doc, Map<String,Object> data, DocDefIV defIV, NkLinkageFormDef d) {
        this.processOptions(EasySingle.from(data), doc, d.getItems());
        return super.afterGetData(doc, data, defIV, d);
    }

    @Override
    public Map<String, Object> calculate(DocHV doc, Map<String, Object> data, DocDefIV defIV, NkLinkageFormDef d, boolean isTrigger, Object options) {
        this.execLinkageSpEL(EasySingle.from(data), doc, d.getItems(), defIV.getCardKey(), isTrigger, (Map) options);
        return super.calculate(doc, data, defIV, d, isTrigger, options);
    }

    private void execLinkageSpEL(EasySingle data, DocHV doc, List<? extends NkLinkageFormDefI> fields, String cardKey, boolean isTrigger, Map options){

        EvaluationContext context = spELManager.createContext(doc);

        Map<String,Object> original = new HashMap<>();

        // 按计算顺序排序
        List<? extends NkLinkageFormDefI> sortedFields = fields.stream()
                .sorted(Comparator.comparing(NkDynamicFormDefI::getCalcOrder)).collect(Collectors.toList());

        // 初始化上下文及保留原始值
        sortedFields.forEach(field -> {
            original.put(field.getKey(), data.get(field.getKey()));
            context.setVariable(field.getKey(), data.get(field.getKey()));
        });

        // 设置需要跳过SpEL计算的字段
        List<String> skip = new ArrayList<>();
        if(isTrigger && options !=null){
            skip.add((String) options.get("triggerKey"));
        }

        // 创建一个计算上下文
        NkCalculateContext calculateContext = new NkCalculateContext();
        calculateContext.setDoc(doc);
        calculateContext.setFields(sortedFields);
        calculateContext.setSkip(skip);
        calculateContext.setOptions(options);
        calculateContext.setTrigger(isTrigger);
        calculateContext.setOriginal(original);

        // 处理字段的inputOptions
        sortedFields.forEach(field -> {
            NkField nkField = customObjectManager.getCustomObject(field.getInputType(), NkField.class);
            nkField.processOptions(field, context, data, calculateContext);
        });

        // 执行字段计算
        sortedFields.forEach(field -> {

            // 执行字段的SpEL
            if(!skip.contains(field.getKey()) && StringUtils.isNotBlank(field.getSpELContent())){
                if (log.isInfoEnabled())
                    log.info("{}\t\t{} 执行表达式 KEY={} EL={}",
                            NkDocEngineContext.currLog(),
                            cardKey,
                            field.getKey(),
                            field.getSpELContent()
                    );

                try {
                    data.set(field.getKey(), spELManager.invoke(field.getSpELContent(), context));
                } catch (Exception e) {
                    throw new NkDefineException(
                            String.format("KEY=%s %s",
                                    field.getKey(),
                                    e.getMessage()
                            )
                    );
                }
            }

            calculateContext.setFieldTrigger(
                    isTrigger
                && options !=null
                && StringUtils.equals(field.getKey(), (String) options.get("triggerKey"))
            );

            // 执行字段的计算逻辑
            customObjectManager.getCustomObject(field.getInputType(), NkField.class)
                    .afterCalculate(field, context, data, calculateContext);

            // 更新上下文中的值
            context.setVariable(field.getKey(), data.get(field.getKey()));
        });
    }

    @Override
    public Object callDef(NkLinkageFormDef def, Object options) {
        return customObjectManager.getCustomObjectDescriptionList(NkField.class, false, (entry)-> entry.getValue() instanceof NkLinkageFormField);
    }
}
