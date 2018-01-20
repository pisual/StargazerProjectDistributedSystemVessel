package com.stargazerproject.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @name 需要注入参数
* @illustrate 被此注解标注的参数，会被注入参数
* **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedInject {
	
	public String type() default "SystemParametersCache";

}
