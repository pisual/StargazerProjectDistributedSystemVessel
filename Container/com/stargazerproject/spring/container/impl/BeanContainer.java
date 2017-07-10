package com.stargazerproject.spring.container.impl;

import com.google.common.base.Optional;
import com.stargazerproject.spring.container.BeanControl;
import com.stargazerproject.spring.context.GlobalApplicationContext;

/** 
 *  @name 全局的Spring容器控制 InstanceContainer（实例容器）
 *  @illustrate 针对于全局的Spring容器进行初始化及控制
 *  @author Felixerio
 *  **/
public class BeanContainer extends GlobalApplicationContext implements BeanControl{
	
	private BeanContainer() {}
	
	@Override
	public <T> T getBean(Optional<String> className, Class<T> t) {
		return applicationContext.getBean(className.get(),t);
	}
	
	public static BeanContainer instance(){
		return InstanceContainerInstance.instanceContainer;
	}
	
	private static class InstanceContainerInstance{
		private final static BeanContainer instanceContainer;
		static{
			instanceContainer = new BeanContainer();
		}
	}
}
