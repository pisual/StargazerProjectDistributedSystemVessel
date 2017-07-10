package com.stargazerproject.classLoader;

import java.net.URL;
import java.net.URLClassLoader;

public class LoadingJarFile {

	public ClassLoader jarClassLoading(URL[] urls){
		ClassLoader classLoader = new URLClassLoader(urls);  
//		Thread.currentThread().setContextClassLoader(classLoader);
		return classLoader;
	}
	
	public ClassLoader jarClassLoading(URL urls){
		URL[] url = new URL[]{urls};
		ClassLoader classLoader = new URLClassLoader(url);
//		Thread.currentThread().setContextClassLoader(classLoader);
		return classLoader;
	}
	
}
