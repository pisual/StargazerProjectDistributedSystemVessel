package com.stargazerproject.ui.assembly.di.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.stargazerproject.userinterface.resources.MainFrameJFrameCharacteristic;

@Configuration 
@ComponentScan(basePackageClasses={MainFrameJFrameCharacteristic.class})
public class BaseFrameConfiguration {
	@Bean 
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() 
	{ return new PropertySourcesPlaceholderConfigurer(); }
}
