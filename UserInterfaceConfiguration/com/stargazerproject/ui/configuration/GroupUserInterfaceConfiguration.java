package com.stargazerproject.ui.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.stargazerproject.ui.assembly.di.configuration.BaseFrameConfiguration;

@Configuration 
@ComponentScan(basePackageClasses={BaseFrameConfiguration.class})
public class GroupUserInterfaceConfiguration {

}
