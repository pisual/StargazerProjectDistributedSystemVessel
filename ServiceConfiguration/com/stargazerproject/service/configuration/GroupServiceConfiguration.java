package com.stargazerproject.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.stargazerproject.service.di.configuration.ServiceDIConfiguration;

/**聚合配置**/
@Configuration
@Import({ServiceDIConfiguration.class})
public class GroupServiceConfiguration {

}
