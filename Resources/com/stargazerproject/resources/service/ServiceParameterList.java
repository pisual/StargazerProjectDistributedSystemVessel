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
	
//	/** @illustrate OrderCacheServerManage**/
//	@Autowired
//	@Qualifier("orderCacheServerManage")
//	private AbstractIdleService orderCacheServerManage;
//	
	/** @illustrate OrderCacheServerManage**/
	@Autowired
	@Qualifier("localLogServerManage")
	private AbstractIdleService localLogServerManage;
	
//	/** @illustrate OrderCacheServerManage**/
//	@Autowired
//	@Qualifier("logQueueServerManage")
//	private AbstractIdleService logQueueServerManage;
	
//	/** @illustrate OrderCacheServerManage**/
//	@Autowired
//	@Qualifier("orderQueueServerManage")
//	private AbstractIdleService orderQueueServerManage;
//	
//	/** @illustrate OrderCacheServerManage**/
//	@Autowired
//	@Qualifier("onlineLogServerManage")
//	private AbstractIdleService onlineLogServerManage;
	
//	/** @illustrate OrderCacheServerManage**/
//	@Autowired
//	@Qualifier("byteArrayCacheServerManage")
//	private AbstractIdleService byteArrayCacheServerManage;
//	
	
	/** @illustrate 内部服务列表**/
	private List<AbstractIdleService> serviceList = new ArrayList<AbstractIdleService>();
	
	protected ServiceParameterList() {}
	
	@PostConstruct
	private void serviceListInitialize(){
		serviceList.add(localLogServerManage);
		serviceList.add(systemParameterCacheServerManage);
	//	serviceList.add(logQueueServerManage);
	//	serviceList.add(onlineLogServerManage);
	//	serviceList.add(orderQueueServerManage);
	//	serviceList.add(orderCacheServerManage);
	//	serviceList.add(byteArrayCacheServerManage);
	}

	@Override
	@Bean(name="serviceListCharacteristic")
	public Optional<List<AbstractIdleService>> characteristic() {
		return Optional.of(serviceList);
	}
}
