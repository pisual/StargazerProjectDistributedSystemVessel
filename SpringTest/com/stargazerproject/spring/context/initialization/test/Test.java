package com.stargazerproject.spring.context.initialization.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaServer
public class Test {
	public static void main(String[] args) throws Exception {
		System.setProperty("spring.profiles.active", "Run");
		System.setProperty("spring.profiles.default", "Run");
		SpringApplication.run(Test.class, args);
		//GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
	}


}
