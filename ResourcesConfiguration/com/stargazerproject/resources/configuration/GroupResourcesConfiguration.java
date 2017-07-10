package com.stargazerproject.resources.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.stargazerproject.resources.di.configuration.ResourcesDIConfiguration;

/**聚合配置**/
@Configuration
@Import({ResourcesDIConfiguration.class})
public class GroupResourcesConfiguration {

}
