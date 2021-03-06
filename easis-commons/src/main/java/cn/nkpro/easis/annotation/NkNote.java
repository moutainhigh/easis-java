package cn.nkpro.easis.annotation;

import cn.nkpro.easis.wsdoc.WsConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER,ElementType.FIELD,ElementType.METHOD,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NkNote {
	String value();

	String desc() default "";

	String retur() default "";

	String warn() default "";
	
	Class<?> returClass() default Object.class;
	
	ReturType returType() default ReturType.KeyValue;
	
	Class<? extends WsConstant<?,?>>[] dependentEnums() default {};
	
	enum ReturType{
		KeyValue,List
	}
}
