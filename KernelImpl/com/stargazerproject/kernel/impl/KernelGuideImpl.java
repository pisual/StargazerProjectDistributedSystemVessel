package com.stargazerproject.kernel.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.kernel.KernelGuide;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceMethod;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceResources;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

public class KernelGuideImpl implements KernelGuide{
	
	private static List<String> serviceList = new ArrayList<String>();
	private static KernelGuide kernelGuide = new KernelGuideImpl();

	@Override
	public KernelGuide differentiation() {
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
		return kernelGuide;
	}

	@Override
	public KernelGuide startBootSequence() {
		serviceList.add("bootInitializationServerManage");
		Sequence bootInitializationSequence = BeanContainer.instance().getBean(Optional.of("sequenceResourcesShell"), Sequence.class);
		SequenceMethod initializationCellsGroupModel = BeanContainer.instance().getBean(Optional.of("initializationCellsGroupModel"), SequenceMethod.class);
		SequenceMethod acquireParameterModel = BeanContainer.instance().getBean(Optional.of("acquireParameterModel"), SequenceMethod.class);
		SequenceMethod injectParameterModel = BeanContainer.instance().getBean(Optional.of("injectParameterModel"), SequenceMethod.class);
		bootInitializationSequence.addModel(Optional.of("bootInitializationSequence"), Optional.of(initializationCellsGroupModel))
		                          .addModel(Optional.of("bootInitializationSequence"), Optional.of(acquireParameterModel))
		                          .addModel(Optional.of("bootInitializationSequence"), Optional.of(injectParameterModel));
		return kernelGuide;
	}

	@Override
	public KernelGuide loadModelServer() {
		serviceList.add("eventQueueServerManage");
		serviceList.add("orderExportQueueServerManage");
		serviceList.add("orderCacheServerManage");
		serviceList.add("orderMessageQueueManage");
		serviceList.add("standardServerManage");
		serviceList.add("frameUserInterfaceManage");
		return kernelGuide;
	}

	@Override
	public KernelGuide startBaseSequence() {
		return kernelGuide;
	}

	@Override
	public KernelGuide startKernelGuide() {
		Optional<ServiceResources> serviceResources = BeanContainer.instance().getBean(Optional.of("serviceResourcesResouecesCharacteristic"), Optional.class);
		serviceResources.get().initializationServiceList(Optional.of(serviceList));
		Optional<ServiceControl> serviceControl = BeanContainer.instance().getBean(Optional.of("serviceControlResourcesCharacteristic"), Optional.class);
		serviceControl.get().startAllservice();		
		return kernelGuide;
	}

}
