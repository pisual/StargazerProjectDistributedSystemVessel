package com.stargazerproject.kernel.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.kernel.KernelGuide;
import com.stargazerproject.service.ServiceInitialization;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.aop.configuration.ServerDependDetectionAOPConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

public class KernelGuideImpl implements KernelGuide{
	
	private static List<String> serviceList = new ArrayList<String>();
	private static KernelGuide kernelGuide = new KernelGuideImpl();
	private static String Cells_Kind = "Cells_Master";

	@Override
	public KernelGuide differentiation() {
		Cells_Kind = System.getProperty("Cells_Kind");
		return kernelGuide;
	}

	@Override
	public KernelGuide startContainer(String[] args) {
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
		return kernelGuide;
	}

	@Override
	public KernelGuide loadKernelServer() {
		return kernelGuide;
	}

	@Override
	public KernelGuide loadBootSequence() {
//		serviceList.add("bootInitializationServerManage");
//		Sequence bootInitializationSequence = BeanContainer.instance().getBean(Optional.of("sequenceResourcesShell"), Sequence.class);
//		
//		switch (Cells_Kind) {
//		case "Cells_Master":å
//
//			System.out.println("################  Cells_Master ############");
//			
//			break;
//	    
//		case "Cells_Child":
//		
////		   SequenceMethod initializationCellsGroupModel = BeanContainer.instance().getBean(Optional.of("initializationCellsGroupModel"), SequenceMethod.class);
////		   SequenceMethod acquireParameterModel = BeanContainer.instance().getBean(Optional.of("acquireParameterModel"), SequenceMethod.class);
////		   SequenceMethod injectParameterModel = BeanContainer.instance().getBean(Optional.of("injectParameterModel"), SequenceMethod.class);
////		   SequenceMethod deletedParameterNodeModel = BeanContainer.instance().getBean(Optional.of("deletedParameterNodeModel"), SequenceMethod.class);
////		   SequenceMethod buildGroupModel = BeanContainer.instance().getBean(Optional.of("buildGroupModel"), SequenceMethod.class);
////		   bootInitializationSequence.addModel(Optional.of("bootInitializationSequence"), Optional.of(initializationCellsGroupModel))
////		                             .addModel(Optional.of("bootInitializationSequence"), Optional.of(acquireParameterModel))
////		                             .addModel(Optional.of("bootInitializationSequence"), Optional.of(injectParameterModel))
////		                             .addModel(Optional.of("bootInitializationSequence"), Optional.of(buildGroupModel));
////		   
//			System.out.println("################  Cells_Master ############");
//		  break;
//		  
//		default:
//			break;
//		}
		return kernelGuide;
	}

	@Override
	public KernelGuide loadModelServer() {
//		serviceList.add("eventQueueServerManage");
//		serviceList.add("orderExportQueueServerManage");
//		serviceList.add("orderCacheServerManage");
//		serviceList.add("orderMessageQueueManage");
//		serviceList.add("standardServerManage");
//		serviceList.add("frameUserInterfaceManage");
		return kernelGuide;
	}

	
//	private KernelGuide startBaseSequence() {
//		Sequence standardSequence = BeanContainer.instance().getBean(Optional.of("standardSequence"), Sequence.class);
//		Cache<String, String> cache = new OrderParameterCache();
//		cache.put(Optional.of("CellsMethodName"), Optional.of("registerSequenceBeanModel"));
//		BaseEvent registerSequenceBeanModel = new BaseEvent(Optional.of(SequenceUtil.getUUID()), Optional.of(cache));
//		standardSequence.addModel(Optional.of("cellsNodeParameterControlModel"), Optional.of(registerSequenceBeanModel));
//		
//		
//			Cache<String, String> cache2 = new OrderParameterCache();
//			cache2.put(Optional.of("CellsMethodName"), Optional.of("initializationCellsGroupModel"));
//			BaseEvent initializationCellsGroupModel = new BaseEvent(Optional.of(SequenceUtil.getUUID()), Optional.of(cache2));
//			standardSequence.addModel(Optional.of("cellsNodeParameterControlModel"), Optional.of(initializationCellsGroupModel));
//			
//		
//		try {
//			standardSequence.startSequence(Optional.of("cellsNodeParameterControlModel"));
//		} catch (BusEventTimeoutException e) {
//			e.printStackTrace();
//		}
//		return kernelGuide;
//	}

	@Override
	@SuppressWarnings("unchecked")
	public KernelGuide startKernelGuide() {
		
//		/**预先加载**/
//		StanderCharacteristicShell<Annotations> annotationsImpl =  BeanContainer.instance().getBean(Optional.of("annotationsImpl"), StanderCharacteristicShell.class);
//		BaseCharacteristic<Annotations> annotationsShell = BeanContainer.instance().getBean(Optional.of("annotationsShell"), BaseCharacteristic.class);
//		annotationsImpl.initialize(annotationsShell.characteristic());
		
//		BaseCharacteristic<Service> serverShell = BeanContainer.instance().getBean(Optional.of("serverShell"), BaseCharacteristic.class);
//		StanderCharacteristicShell<Service> server =  BeanContainer.instance().getBean(Optional.of("kernelService"), StanderCharacteristicShell.class);
//		server.initialize(serverShell.characteristic());
		
		BaseCharacteristic<ServiceInitialization> serverInitialization = BeanContainer.instance().getBean(Optional.of("serviceInitializationCharacteristic"), BaseCharacteristic.class);
		serverInitialization.characteristic().get().initializationFromAnnotationsScan();
		
//		StanderCharacteristicShell<Log> logRecord =  BeanContainer.instance().getBean(Optional.of("logRecord"), StanderCharacteristicShell.class);
//		BaseCharacteristic<Log> logRecordShell = BeanContainer.instance().getBean(Optional.of("localLogShell"), BaseCharacteristic.class);
//		logRecord.initialize(logRecordShell.characteristic());

		BaseCharacteristic<ServiceControl> serviceControl = BeanContainer.instance().getBean(Optional.of("serviceControlCharacteristic"), BaseCharacteristic.class);
		
		Thread closeJVM = new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("Start Stop Sequence");
//				serviceControl.characteristic().get().stopAllService();
				System.out.println("StopAllservice");
			}
		});
		
		Runtime.getRuntime().addShutdownHook(closeJVM); 
//		System.out.println("    startAllservice Start");
		serviceControl.characteristic().get().startAllservice();
//		System.out.println("    startAllservice Over");
//
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		serviceControl.characteristic().get().stopAllService();
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.exit(1);
		
		return kernelGuide;
	}

}
