package com.stargazerproject.resources.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.AnnotationsScanner;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.annotation.Services;

@Component(value="serviceParameterList")
@Qualifier("serviceParameterList")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ServiceParameterList implements BaseCharacteristic<List<String>>{
	
	@Autowired
	@Qualifier("annotationsImpl")
	private AnnotationsScanner annotationsScanner;
	
	/** @illustrate 方法级无依赖服务 **/
	private List<String> methodServiceList;
	
	/** @illustrate 序列启动依赖服务 **/
	private List<String> sequenceServiceList;
	
	private Map<Integer, String> transferServerMap;
	
	protected ServiceParameterList() {}
	
	@Override
	public Optional<List<String>> characteristic() {
		try {
			methodServiceList = new ArrayList<String>();
			sequenceServiceList = new ArrayList<String>();
			transferServerMap  = new HashMap<Integer, String>();
			serviceListInitialize();
			serviceListSort();
			return totalServerList();
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	private void serviceListInitialize() throws ClassNotFoundException, IOException{
		Map<Class<?>, Collection<Entry<String, List<Object>>>> mapList = annotationsScanner.scannerAnnotation(Optional.of("com.stargazerproject"), Optional.of(Services.class)).get().asMap();
		for(Entry<Class<?>, Collection<Entry<String, List<Object>>>> mapEntry : mapList.entrySet()){
			creatTransferServerMap(mapEntry.getValue());
		}
	}
	
	private void creatTransferServerMap(Collection<Entry<String, List<Object>>> mapSet){
		Optional<String> servername = Optional.absent();
		Optional<Integer> order = Optional.absent();
		for(Entry<String, List<Object>> valueMap : mapSet){
			if(valueMap.getKey().equals("value")){
				servername = Optional.of(valueMap.getValue().get(0).toString());
			}
			if(valueMap.getKey().equals("order")){
				order = Optional.of(Integer.parseInt(valueMap.getValue().get(0).toString()));
			}
		}
		
		if(null != transferServerMap.get(order.get())){
			throw new IllegalArgumentException("The Service list duplication : 服务列表重复 : " + servername.get() + " 与 " + transferServerMap.get(order.get()) + "重复");
		}
		
		if(order.get().equals(0)){
			methodServiceList.add(servername.get());
		}
		else{
			transferServerMap.put(order.get(), servername.get());
		}
	}
	
	private void serviceListSort(){
		for(int i=1 ; i<transferServerMap.size();i++){
			if(null == transferServerMap.get(i)){
				System.err.println("服务模块诊断 : ");
				for(int j=1 ; j<transferServerMap.size();j++){
					
					if(null == transferServerMap.get(j)){
						System.err.println(" ServerNumber " + j + " : Null ");
					}
					else{
						System.out.println("SercereSequence : " + j + " : " +transferServerMap.get(j));
					}
					
				}
				throw new IllegalArgumentException("The service list is discontinuous : 服务列表不连续");
			}
			sequenceServiceList.add(transferServerMap.get(i));
		}
	}
	
	private Optional<List<String>> totalServerList(){
		List<String> totalServiceList = new ArrayList<String>();
		methodServiceList.forEach(x -> totalServiceList.add(x));
		sequenceServiceList.forEach(x -> totalServiceList.add(x));
		return Optional.of(totalServiceList);
	}
	
}
