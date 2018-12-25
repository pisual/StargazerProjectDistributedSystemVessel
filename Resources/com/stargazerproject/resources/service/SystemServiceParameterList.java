package com.stargazerproject.resources.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.annotation.AnnotationsScanner;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.annotation.Services;

/**
 * 服务区域概念
 * ServiceZone(zone = Zone.System, layer = 1-999)
 * Zone为服务区域，分为Zone.System（layer = 1-999）， Zone.Component（1000-9999）
 * 每一个layer为一个组，内部可以乱序并行执行多个服务，对外，只有上一个layer执行完毕才可以执行下一个
 * Zone.System为系统级服务，是系统的关键支撑服务
 * Zone.Component为用户组件级服务，将在Zone.System启动完毕后，在后期按照系统指令进行服务启动等相关操作
 * 
 * **/
@Component(value="systemServiceParameterList")
@Qualifier("systemServiceParameterList")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemServiceParameterList implements BaseCharacteristic<Multimap<Optional<Integer>, Optional<String>>>{
	
	@Autowired
	@Qualifier("annotationsImpl")
	private AnnotationsScanner annotationsScanner;
	
	/** @illustrate 系统级（Zone.System）服务列表 
	 * 一对多结构Map
	 * {
	 * 1{a.server,b.server}
	 * 2{c.server}
	 * }
	 * 
	 * **/
	private Multimap<Optional<Integer>, Optional<String>> systemServiceterList;
	
	protected SystemServiceParameterList() {}
	
	@Override
	public Optional<Multimap<Optional<Integer>, Optional<String>>> characteristic() {
		try {
			systemServiceterList = ArrayListMultimap.create();
			serviceListInitialize();
			return Optional.of(systemServiceterList);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	private void serviceListInitialize() throws ClassNotFoundException, IOException{
		Map<Class<?>, Collection<Entry<String, List<Object>>>> mapList = annotationsScanner.scannerAnnotation(Optional.of("com.stargazerproject"), Optional.of(Services.class)).get().asMap();
		for(Entry<Class<?>, Collection<Entry<String, List<Object>>>> mapEntry : mapList.entrySet()){
			creatSystemServiceterList(mapEntry.getValue());
		}
	}
	
	private void creatSystemServiceterList(Collection<Entry<String, List<Object>>> mapSet){
		String servername = null;
		Integer layer = null;
		for(Entry<String, List<Object>> valueMap : mapSet){
			
			switch (valueMap.getKey()) {
			/**非本级别服务，略过**/
			case "Component":
				return;
			case "name":
				servername = valueMap.getValue().get(0).toString();
				break;
			case "layer":
				layer = Integer.parseInt(valueMap.getValue().get(0).toString());
				break;
			default:
				break;
				}
			}
		systemServiceterList.put(Optional.of(layer), Optional.of(servername));
		}
	
}