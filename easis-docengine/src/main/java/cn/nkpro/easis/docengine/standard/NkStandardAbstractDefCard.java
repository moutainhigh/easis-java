package cn.nkpro.easis.docengine.standard;

import cn.nkpro.easis.docengine.NkAbstractCard;
import cn.nkpro.easis.docengine.model.DocDefHV;
import cn.nkpro.easis.docengine.model.DocDefIV;
import cn.nkpro.easis.docengine.model.DocHV;
import cn.nkpro.easis.co.easy.EasySingle;
import cn.nkpro.easis.docengine.service.NkDocEngineContext;
import cn.nkpro.easis.exception.NkDefineException;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.EvaluationContext;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class NkStandardAbstractDefCard<DT,DDT extends NkStandardDef> extends NkAbstractCard<DT,DDT> {

    @Override
    public DDT afterGetDef(DocDefHV defHV, DocDefIV defIV, DDT def) {

        def.getFields()
                .stream()
                .filter(e -> StringUtils.equals(e.getType(),"select"))
                .filter(e -> StringUtils.isNotBlank((CharSequence) e.getOptions()))
                .forEach(e -> e.setOptions(JSON.parseArray(spELManager.convert((String) e.getOptions(), null))));

        return super.afterGetDef(defHV, defIV, def);
    }

    @Override
    public DT afterCreate(DocHV doc, DocHV preDoc, DT data, DocDefIV defIV, DDT d) {

        this.execSpEL(EasySingle.from(data), doc, d.getFields(), defIV.getCardKey(), true);

        return super.afterCreate(doc, preDoc, data, defIV, d);
    }

    @Override
    public DT calculate(DocHV doc, DT data, DocDefIV defIV, DDT d, boolean isTrigger, Object options) {

        this.execSpEL(EasySingle.from(data), doc, d.getFields(), defIV.getCardKey(), false);

        return super.calculate(doc, data, defIV, d, isTrigger, options);
    }

    protected void execSpEL(EasySingle data, DocHV doc, List<NkStandardDefField> fields, String cardKey, boolean isNewCreate){

        EvaluationContext context = spELManager.createContext(doc);

        fields.stream()
                .sorted(Comparator.comparing(NkStandardDefField::getCalcOrder))
                .forEach( field -> {
                    if (StringUtils.isNotBlank(field.getSpELContent())) {

                        String trigger = null;
                        if (ArrayUtils.contains(field.getSpELTriggers(), "ALWAYS")) {
                            trigger = "ALWAYS";
                        } else if (ArrayUtils.contains(field.getSpELTriggers(), "INIT") && isNewCreate) {
                            trigger = "INIT";
                        } else if (ArrayUtils.contains(field.getSpELTriggers(), "BLANK") && isBlank(data.get(field.getKey()))) {
                            trigger = "BLANK";
                        }

                        if (trigger != null) {

                            if (log.isInfoEnabled())
                                log.info("{}\t\t{} ??????????????? KEY={} T={} EL={}",
                                        NkDocEngineContext.currLog(),
                                        cardKey,
                                        field.getKey(),
                                        trigger,
                                        field.getSpELContent()
                                );

                            try {
                                data.set(field.getKey(), spELManager.invoke(field.getSpELContent(), context));
                            } catch (Exception e) {
                                throw new NkDefineException(
                                        String.format("KEY=%s T=%s %s",
                                                field.getKey(),
                                                trigger,
                                                e.getMessage()
                                        )
                                );
                            }
                        }
                    }
                    context.setVariable(field.getKey(), data.get(field.getKey()));
                });
    }

    private static boolean isBlank(Object value){
        return value == null ||
                (value instanceof Array && Array.getLength(value)==0) ||
                (value instanceof Collection && CollectionUtils.isEmpty((Collection<?>) value)) ||
                StringUtils.isBlank(value.toString());
    }
}
