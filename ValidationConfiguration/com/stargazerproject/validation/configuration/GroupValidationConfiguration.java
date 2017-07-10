package com.stargazerproject.validation.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**聚合配置**/
@Configuration
@Import({GroupValidationDIConfiguration.class})
public class GroupValidationConfiguration {

}
