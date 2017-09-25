package com.stargazerproject.service.model.test;

import org.junit.Test;

import com.google.common.base.Optional;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;
import com.stargazerproject.test.pattern.WorkInTest;

public class ModuleServiceTest implements WorkInTest{

	@Test
	public void SpringInit(){
		String args[] = {"debug"};
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
	}

	@Override
	@Test
	public void interfaceInitialize() {
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}

}
