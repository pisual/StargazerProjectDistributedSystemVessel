package com.stargazerproject.spring.container.impl;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import com.google.common.base.Optional;
import com.stargazerproject.spring.container.BeanControl;
import com.stargazerproject.spring.container.model.Scope;
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
	
	@Override
	public void setBean(Optional<String> className, Optional<Scope> scope, Class<?> ClassArg) {
		System.out.println("#############################");
		BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
		//beanFactory.registerBeanDefinition(className.get(), BeanDefinitionBuilder.genericBeanDefinition(ClassArg).setScope("singleton").getBeanDefinition());
		//beanFactory.autowireBean(ClassArg);
	//	beanFactory.createBean(ClassArg,1, false);
	//	beanFactory.registerSingleton(className.get(), ClassArg);
	//	beanFactory.autowireBean(ClassArg);
	//	beanFactory.autowireBeanProperties(ClassArg, 1, true);
	//	ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory);
    //    scanner.scan("com.stargazerproject.messagequeue");
        applicationContext.getAutowireCapableBeanFactory().createBean(ClassArg);
//        applicationContext
		System.out.println("#############################");
		//beanFactory
		
	}
	
	@Override
	public void removeBean(Optional<String> className) {
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
		beanFactory.removeBeanDefinition(className.get());
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
