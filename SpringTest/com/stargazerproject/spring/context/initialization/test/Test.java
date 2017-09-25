package com.stargazerproject.spring.context.initialization.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value="Spring.properties")
public class Test {
	public static void main(String[] args) throws Exception {
		System.setProperty("spring.profiles.active", "Run");
		System.setProperty("spring.profiles.default", "Run");
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
	}

}
