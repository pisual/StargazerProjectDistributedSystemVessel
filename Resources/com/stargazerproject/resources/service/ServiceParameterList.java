package com.stargazerproject.resources.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractIdleService;
import com.stargazerproject.characteristic.BaseCharacteristic;

@Component
@Qualifier("serviceParameterList")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceParameterList implements BaseCharacteristic<List<AbstractIdleService>>{
	
	/** @illustrate systemParameterCacheServerManage**/
	@Autowired
	@Qualifier("systemParameterCacheServerManage")
	private AbstractIdleService systemParameterCacheServerManage;
	
	/** @illustrate OrderCacheServerManage**/
	@Autowired
	@Qualifier("orderCacheServerManage")
	private AbstractIdleService orderCacheServerManage;
	
	/** @illustrate localLogServerManage**/
	@Autowired
	@Qualifier("localLogServerManage")
	private AbstractIdleService localLogServerManage;
	
	/** @illustrate logQueueServerManage**/
	@Autowired
	@Qualifier("logQueueServerManage")
	private AbstractIdleService logQueueServerManage;
	
	/** @illustrate OrderCacheServerManage**/
	@Autowired
	@Qualifier("eventQueueServerManage")
	private AbstractIdleService eventQueueServerManage;
	
	/** @illustrate onlineLogServerManage**/
	@Autowired
	@Qualifier("onlineLogServerManage")
	private AbstractIdleService onlineLogServerManage;
	
	/** @illustrate bigCacheIndexCacheServerManage**/
	@Autowired
	@Qualifier("bigCacheIndexCacheServerManage")
	private AbstractIdleService bigCacheIndexCacheServerManage;
	
//	/** @illustrate byteArrayCacheServerManage**/
//	@Autowired
//	@Qualifier("byteArrayCacheServerManage")
//	private AbstractIdleService byteArrayCacheServerManage;
	
	/** @illustrate bigCacheIndexCacheServerManage**/
	@Autowired
	@Qualifier("orderExportQueueServerManage")
	private AbstractIdleService orderExportQueueServerManage;
	
//	/** @illustrate bigCacheIndexCacheServerManage**/
//	@Autowired
//	@Qualifier("orderMessageQueueManage")
//	private AbstractIdleService orderMessageQueueManage;
	
	/** @illustrate bigCacheIndexCacheServerManage**/
	@Autowired
	@Qualifier("frameUserInterfaceManage")
	private AbstractIdleService frameUserInterfaceManage;
	
	/** @illustrate 内部服务列表**/
	private List<AbstractIdleService> serviceList = new ArrayList<AbstractIdleService>();
	
	protected ServiceParameterList() {}
	
	@PostConstruct
	private void serviceListInitialize(){
		serviceList.add(localLogServerManage);
		serviceList.add(bigCacheIndexCacheServerManage);
		serviceList.add(systemParameterCacheServerManage);
		serviceList.add(logQueueServerManage);
		serviceList.add(onlineLogServerManage);
		serviceList.add(eventQueueServerManage);
		serviceList.add(orderCacheServerManage);
		serviceList.add(orderExportQueueServerManage);
//		serviceList.add(byteArrayCacheServerManage);
//		serviceList.add(orderMessageQueueManage);
		serviceList.add(frameUserInterfaceManage);
	}

	@Override
	@Bean(name="serviceListCharacteristic")
	public Optional<List<AbstractIdleService>> characteristic() {
		return Optional.of(serviceList);
	}
}
