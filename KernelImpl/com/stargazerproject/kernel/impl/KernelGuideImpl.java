package com.stargazerproject.kernel.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.impl.OrderParameterCache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.kernel.KernelGuide;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceAnnotationResources;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;
import com.stargazerproject.util.SequenceUtil;

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
		serviceList.add("localLogServerManage");
		serviceList.add("systemParameterCacheServerManage");
		serviceList.add("logQueueServerManage");
		serviceList.add("onlineLogServerManage");
		serviceList.add("bigCacheIndexCacheServerManage");
		serviceList.add("byteArrayCacheServerManage");
		serviceList.add("nodeNegotiateManage");
		serviceList.add("eventBusQueueServerManage");
		serviceList.add("eventBusServerManage");
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
//		Event registerSequenceBeanModel = new Event(Optional.of(SequenceUtil.getUUID()), Optional.of(cache));
//		standardSequence.addModel(Optional.of("cellsNodeParameterControlModel"), Optional.of(registerSequenceBeanModel));
//		
//		
//			Cache<String, String> cache2 = new OrderParameterCache();
//			cache2.put(Optional.of("CellsMethodName"), Optional.of("initializationCellsGroupModel"));
//			Event initializationCellsGroupModel = new Event(Optional.of(SequenceUtil.getUUID()), Optional.of(cache2));
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
	public KernelGuide startKernelGuide() {
		BaseCharacteristic<ServiceAnnotationResources> serviceResources = BeanContainer.instance().getBean(Optional.of("serviceResourcesResouecesCharacteristic"), BaseCharacteristic.class);
		serviceResources.characteristic().get().initializationServiceList(Optional.of(serviceList));
		BaseCharacteristic<ServiceControl> serviceControl = BeanContainer.instance().getBean(Optional.of("serviceControlResourcesCharacteristic"), BaseCharacteristic.class);
		serviceControl.characteristic().get().startAllservice();
//		if(Cells_Kind.equals("Cells_Master")){
//			startBaseSequence();
//		}
		return kernelGuide;
	}

}
