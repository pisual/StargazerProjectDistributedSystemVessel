package com.stargazerproject.annotation.description;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 *  @name 非Spring依赖
 *  @illustrate 非Spring依赖是一个非常特殊的注解，虽然StargazerCloud框架是基于Spring构建的，
 *              但绝对不是脱离Spring就运行不下去，为了贯彻这种这种设计哲学，一旦类加上NoSpringDepend注解，
 *              就表明这个类在Spring和非Spring依赖环境下都可以正常运行。
 *              一旦被NoSpringDepend标注，就会在Check（全局自动代码规范及运行检测）中进行双重测试，即Spring集成测试和非Spring集成测试
 *              StargazerCloud框架中，除核心（Kernel）程序外（核心程序需要一些Spring的特殊方法，是对代码具有侵入性的，所以无法进行NoSpringDepend构造），
 *              其他的类都要通过NoSpringDepend检测。
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoSpringDepend {

}
