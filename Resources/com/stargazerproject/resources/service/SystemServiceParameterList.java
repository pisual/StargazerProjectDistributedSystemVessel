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
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import com.stargazerproject.annotation.AnnotationsScanner;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.annotation.ServiceZone;
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
public class SystemServiceParameterList implements BaseCharacteristic<Table<Integer, String, Boolean>>{
	
	@Autowired
	@Qualifier("annotationsImpl")
	private AnnotationsScanner annotationsScanner;
	
	Table<Integer, String, Boolean> serviceMenu;
	
	protected SystemServiceParameterList() {}
	
	@Override
	public Optional<Table<Integer, String, Boolean>> characteristic() {
		try {
			serviceMenu =  TreeBasedTable.create((x, y) -> Integer.compare(x, y), (x, y) -> x.compareTo(y));
			serviceListInitialize();
			return Optional.of(serviceMenu);
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
			case "serviceZone":
				if(valueMap.getValue().get(0).toString() == ServiceZone.Component.toString())
				{
					return;
				}
				break;
			case "name":
				servername = valueMap.getValue().get(0).toString();
				break;
			case "layer":
				layer = Integer.parseInt(valueMap.getValue().get(0).toString());
				break;
			default:
				continue;
				}
			}
		serviceMenu.put(layer, servername, Boolean.FALSE);
		}
	
}