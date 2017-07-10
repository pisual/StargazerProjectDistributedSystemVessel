package com.stargazerproject.cache.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.stargazerproject.cache.aop.configuration.CacheAOPConfiguration;
import com.stargazerproject.cache.di.configuration.CacheDIConfiguration;

@Configuration 
@Import({CacheDIConfiguration.class,CacheAOPConfiguration.class})
public class GroupCacheConfiguration {

}
