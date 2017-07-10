package com.stargazerproject.cache.aop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration 
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.stargazerproject.cache.aop"})
public class CacheAOPConfiguration {

}
