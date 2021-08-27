package cn.nkpro.ts5.engine.spel;

import cn.nkpro.ts5.engine.co.NkCustomObjectManager;
import cn.nkpro.ts5.engine.doc.model.DocHV;
import cn.nkpro.ts5.exception.TfmsDefineException;
import cn.nkpro.ts5.utils.BeanUtilz;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TfmsSpELManager {

    private static final ExpressionParser parser = new SpelExpressionParser();
    private static final Pattern pattern = Pattern.compile("\"?\\$\\{(?:[^\"'}]|\"[^\"]*\"|'[^']*'|\\{\\{|}})*}\"?");


    @Autowired@SuppressWarnings("all")
    private NkCustomObjectManager customObjectManager;

    public EvaluationContext createContext(DocHV doc){
        StandardEvaluationContext ctx = new StandardEvaluationContext(doc);
        ctx.addPropertyAccessor(new MapAccessor());
        getSpELMap().forEach(ctx::setVariable);

        return ctx;
    }

    private Map<String, Object> getSpELMap(){
        return customObjectManager.getCustomObjects(TfmsSpELInjection.class)
                .values()
                .stream()
                .collect(Collectors.toMap(TfmsSpELInjection::getSpELName,t->t));
    }

    public Object invoke(String el, EvaluationContext context){
        try{
            return parser.parseExpression(el).getValue(context);
        }catch (ParseException | EvaluationException e){
            throw new TfmsDefineException(String.format("表达式 %s 错误: %s",el, e.getMessage()),e);
        }
    }

    public String convert(DocHV doc,String input){
        return convert(createContext(doc),input);
    }

    public String convert(EvaluationContext context,String input){

        if(StringUtils.isBlank(input)){
            return StringUtils.EMPTY;
        }

        Matcher matcher = pattern.matcher(input);
        boolean bool  = true;
        boolean bool2 = false;

        while(matcher.find()){

            bool2 = true;
            if(bool && log.isDebugEnabled()){
                bool = false;
                log.debug("解析SpEL模版 {}",input);
            }

            String expression = matcher.group(0);
            String el = expression
                    .replaceAll("^\"?\\$\\{",StringUtils.EMPTY)
                    .replaceAll("}\"?$",StringUtils.EMPTY)
                    .replaceAll("\\{\\{","{")
                    .replaceAll("}}","}");

            try{
                Object value = parser.parseExpression(el).getValue(context);

                String strValue;
                if(value == null){
                    strValue = "null";
                }else if(value instanceof Boolean || value instanceof Number){
                    strValue = value.toString();
                }else{
                    strValue = JSON.toJSONString(value);
                }
                input = input.replace(expression, strValue);

                if(log.isDebugEnabled())log.debug("\t {} => {} >> {}",expression, el, strValue);

            }catch (ParseException | EvaluationException e){
                throw new TfmsDefineException(String.format("表达式模版 %s 错误 : %s",input, el),e);
            }
        }
        if(bool2 && log.isDebugEnabled()){
            log.debug("解析SpEL模版 完成 {}",input);
        }

        return input;
    }
}
