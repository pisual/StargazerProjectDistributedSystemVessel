package com.stargazerproject.spring.context.impl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stargazerproject.spring.context.GlobalApplicationContext;

public class GlobalAnnotationApplicationContext extends GlobalApplicationContext{
	
	private GlobalAnnotationApplicationContext() {}
	
	public static void ApplicationContextInitialize(Class<?>... annotatedClasses){
		GlobalApplicationContextInstance.globalAnnotationApplicationContext.setApplicationContext(new AnnotationConfigApplicationContext(annotatedClasses));
	}
	
	protected static class GlobalApplicationContextInstance{
		private static GlobalAnnotationApplicationContext globalAnnotationApplicationContext;
		static{
			globalAnnotationApplicationContext = new GlobalAnnotationApplicationContext();
		}
	}
	
}