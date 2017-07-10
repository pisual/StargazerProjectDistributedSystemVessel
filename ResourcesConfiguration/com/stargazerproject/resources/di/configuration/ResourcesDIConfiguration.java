package com.stargazerproject.resources.di.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration 
@ComponentScan(basePackages={"com.stargazerproject.resources.parameter","com.stargazerproject.resources.service"})
public class ResourcesDIConfiguration {

}
