package com.stargazerproject.ui.assembly.di.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.stargazerproject.ui.assembly.impl.BaseFrame;

@Configuration 
@ComponentScan(basePackageClasses={BaseFrame.class})
public class BaseFrameConfiguration {
	@Bean 
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() 
	{ return new PropertySourcesPlaceholderConfigurer(); }
}
