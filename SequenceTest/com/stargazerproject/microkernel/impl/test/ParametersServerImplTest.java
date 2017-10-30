package com.stargazerproject.microkernel.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceMethod;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceResources;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

@FixMethodOrder(MethodSorters.JVM)
public class ParametersServerImplTest {
	
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
		
		Optional<ServiceResources> serviceResources = BeanContainer.instance().getBean(Optional.of("serviceResourcesResouecesCharacteristic"), Optional.class);
		List<String> serviceList = new ArrayList<String>();
		
		serviceList.add("localLogServerManage");
		serviceList.add("systemParameterCacheServerManage");
		serviceList.add("logQueueServerManage");
		serviceList.add("onlineLogServerManage");
		serviceList.add("bigCacheIndexCacheServerManage");
		serviceList.add("byteArrayCacheServerManage");
		serviceList.add("nodeNegotiateManage");
		
		serviceList.add("bootInitializationServerManage");

		serviceList.add("eventQueueServerManage");
		serviceList.add("orderExportQueueServerManage");
		serviceList.add("orderCacheServerManage");
		serviceList.add("orderMessageQueueManage");
		serviceList.add("standardServerManage");
		//serviceList.add("frameUserInterfaceManage");
		serviceResources.get().initializationServiceList(Optional.of(serviceList));
		
		Optional<ServiceControl> serviceControl = BeanContainer.instance().getBean(Optional.of("serviceControlResourcesCharacteristic"), Optional.class);
		serviceControl.get().startAllservice();	
		
		Sequence standardSequence = BeanContainer.instance().getBean(Optional.of("standardSequence"), Sequence.class);
		SequenceMethod cellsNodeParameterControlModel = BeanContainer.instance().getBean(Optional.of("cellsNodeParameterControlModel"), SequenceMethod.class);
		standardSequence.addModel(Optional.of("cellsNodeParameterControlModel"), Optional.of(cellsNodeParameterControlModel));
		standardSequence.startSequence(Optional.of("cellsNodeParameterControlModel"));
	}
	
}
