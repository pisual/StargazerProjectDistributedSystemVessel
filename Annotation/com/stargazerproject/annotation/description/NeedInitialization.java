package com.stargazerproject.annotation.description;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @name 需要初始化Cache的内容
* @illustrate 将标注的必要内容Map注入Cache之中
* **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedInitialization {
	
	public String content() default "";

}
