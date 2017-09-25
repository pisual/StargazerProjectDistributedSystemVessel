package com.stargazerproject.spring.context.impl;

import org.springframework.boot.builder.SpringApplicationBuilder;

import com.stargazerproject.spring.context.GlobalApplicationContext;

public class GlobalAnnotationApplicationContext extends GlobalApplicationContext{
	
	private GlobalAnnotationApplicationContext() {}
	
	public static void ApplicationContextInitialize(String args[], Class<?>... annotatedClasses){
		GlobalApplicationContextInstance.globalAnnotationApplicationContext.setApplicationContext(new SpringApplicationBuilder().sources(annotatedClasses).run(args));
	}
	
	protected static class GlobalApplicationContextInstance{
		private static GlobalAnnotationApplicationContext globalAnnotationApplicationContext;
		static{
			globalAnnotationApplicationContext = new GlobalAnnotationApplicationContext();
		}
	}
	
}