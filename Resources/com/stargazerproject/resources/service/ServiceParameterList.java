package com.stargazerproject.resources.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.AbstractIdleService;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.inject.AnnotationScanner;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="serviceParameterList")
@Qualifier("serviceParameterList")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceParameterList implements BaseCharacteristic<List<AbstractIdleService>>{

	@Autowired
	@Qualifier("annotationScannerImpl")
	private AnnotationScanner annotationScanner;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	/** @illustrate 内部服务列表**/
	private List<AbstractIdleService> serviceList = new ArrayList<AbstractIdleService>();
	
	protected ServiceParameterList() {}
	
	private void serviceListInitialize(){
		try {
			Multimap<Class<?>, Map.Entry<String, List<Object>>> scoreMultimap = annotationScanner.getClassAnnotationContent(Optional.of("com.stargazerproject"), Optional.of(com.stargazerproject.service.Service.class));
			Multimap<Integer, String> serverLoadSequence = LinkedHashMultimap.create();
			Map<Class<?>, Collection<Entry<String, List<Object>>>> servermap = scoreMultimap.asMap();
			List<Class<?>> keyList = scoreMultimap.keys().stream().collect(Collectors.toList());
			
			for(int i=0;i<keyList.size();i++){
				Collection<Entry<String, List<Object>>> single = servermap.get(keyList.get(i));
				String name = "";
				int order= 1;
				for(int j=0; j<single.size(); j++){
					Iterator<Entry<String, List<Object>>> singleit = single.iterator();
					while(singleit.hasNext()){
						Entry<String, List<Object>> anv = singleit.next();
						if(anv.getKey().toString().equals("value")){
							name = anv.getValue().get(0).toString();
						}
						else{
							order = Integer.parseInt(anv.getValue().get(0).toString());
						}
					}
				}
				serverLoadSequence.put(order, name);
			}
			
			
			List<Integer> orderList = serverLoadSequence.asMap().keySet().stream().sorted().collect(Collectors.toList());
		
			System.out.println("############ " + serverLoadSequence.toString());
			
			
			orderList.forEach(x -> serverLoadSequence.get(x).forEach(z -> serviceList.add((BeanContainer.instance().getBean(Optional.of(z), AbstractIdleService.class)))));
			
	//		serviceList.forEach(x -> System.out.println(x.toString()));
//			scoreMultimap.values().stream().filter(x -> x.getKey().equals("value"))
//			                               .map(z -> z.getValue().get(0).toString())
//			                               .collect(Collectors.toList())
//					              .forEach(s -> serviceList.add((BeanContainer.instance().getBean(Optional.of(s), AbstractIdleService.class))));
		} catch (ClassNotFoundException e) {
			baseLog.ERROR(this, e.getMessage());
		} catch (IOException e) {
			baseLog.ERROR(this, e.getMessage());
		}
		
	}

	@Override
	@Bean(name="serviceListCharacteristic")
	@Lazy(true)
	public Optional<List<AbstractIdleService>> characteristic() {
		serviceListInitialize();
		return Optional.of(serviceList);
	}
}
