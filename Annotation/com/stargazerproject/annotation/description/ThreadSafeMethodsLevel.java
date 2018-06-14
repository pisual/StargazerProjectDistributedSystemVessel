package com.stargazerproject.annotation.description;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 *  @name 线程安全方法注解
 *  @illustrate 线程安全性的分类方法：不可变、线程安全、有条件线程安全、线程兼容和线程对立
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ThreadSafeMethodsLevel {
	
	public ThreadSafeLevel threadSafeLevel();

}
