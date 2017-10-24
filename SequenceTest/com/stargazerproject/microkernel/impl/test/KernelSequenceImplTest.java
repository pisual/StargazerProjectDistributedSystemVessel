package com.stargazerproject.microkernel.impl.test;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

@FixMethodOrder(MethodSorters.JVM)
public class KernelSequenceImplTest {
	
//	@Test
//	public void SpringInit(){
//		String args[] = {"debug"};
//		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
//	}
//	
//	@Test
//	public void serviceStartTest(){
//		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"), ServiceControl.class);
//		serviceControl.startAllservice();
//	}
//	
//	@Test
//	public void kernelSequenceTest(){
//	}
//	
	public static void main(String[] args) {
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
		
		Optional<ServiceManager> ServiceManagerInits = BeanContainer.instance().getBean(Optional.of("moduleServiceServiceManagerCharacteristic"), Optional.class);
		StanderCharacteristicShell<ServiceManager> serviceManager = BeanContainer.instance().getBean(Optional.of("moduleService"), StanderCharacteristicShell.class);;
		serviceManager.initialize(ServiceManagerInits);
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"), ServiceControl.class);
		serviceControl.startAllservice();
//		Sequence kernelSequence = BeanContainer.instance().getBean(Optional.of("cellsGenerateSequence"), Sequence.class);
//		SequenceMethod initializationUUIDModel = BeanContainer.instance().getBean(Optional.of("initializationUUIDModel"), SequenceMethod.class);
//		SequenceMethod injectParameterModel = BeanContainer.instance().getBean(Optional.of("injectParameterModel"), SequenceMethod.class);
//		kernelSequence.addModel(Optional.of(initializationUUIDModel)).addModel(Optional.of(injectParameterModel));
//		kernelSequence.startSequence();
	}
	
}
