package com.stargazerproject.service.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.util.concurrent.AbstractIdleService;
import com.google.common.util.concurrent.ServiceManager;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.ServiceInitialization;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value = "serviceControlCharacteristic")
@Qualifier("serviceControlCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceControlCharacteristic implements ServiceControl, BaseCharacteristic<ServiceControl> {
	
	@Autowired
	@Qualifier("serviceInitializationCharacteristic")
	private BaseCharacteristic<ServiceInitialization> ServiceInitialization;
	
	private Table<Integer, String, Boolean> serviceMenu;
	
	Map<String, Integer> serviceLayer = Maps.newTreeMap();
	
	private ServiceManager serviceManager;
	
	public ServiceControlCharacteristic() {}
	
	@Override
	public Optional<ServiceControl> characteristic() {
		return Optional.of(this);
	}

	@Override
	public void startAllservice() {
		serviceMenu = ServiceInitialization.characteristic().get().serviceMenu().get();
		serviceMenu.cellSet().forEach(x -> serviceLayer.put(x.getColumnKey(), x.getRowKey()));
		List<String> serviceList = serviceLayer.entrySet().stream().map(x -> x.getKey()).collect(Collectors.toList());
		serviceManager = new ServiceManager(serviceListConvertToAbstractIdleServiceList(Optional.of(serviceList)).get());
		Collection<Integer> serverLayerMenu = serviceLayer.values().stream().distinct().sorted().collect(Collectors.toList());
		serverLayerMenu.forEach(x -> {
			
			if(x != firstGroupService() || x != loadingInAdvanceService()){
				while(checkGroupServer(beforeServiceLayer(x)) == Boolean.FALSE){
					try {
						TimeUnit.MILLISECONDS.sleep(10);;
					} catch (InterruptedException interruptedException) {
						interruptedException.printStackTrace();
					}
				}
			}
			
			serviceMenu.rowMap().get(x).keySet().forEach(y->{
				new Thread(() -> {
					BeanContainer.instance().getBean(Optional.of(y), AbstractIdleService.class).startAsync().awaitRunning();
					serviceMenu.put(x, y, Boolean.TRUE);
				}).start();
			});
			
		});
	}

	@Override
	public void stopAllService() {
		serviceManager.stopAsync().awaitStopped();
	}

	private Optional<List<AbstractIdleService>> serviceListConvertToAbstractIdleServiceList(Optional<List<String>> serviceListArg) {
		List<AbstractIdleService> serviceList = new ArrayList<AbstractIdleService>();
		serviceListArg.get().forEach(x -> serviceList.add(BeanContainer.instance().getBean(Optional.of(x), AbstractIdleService.class)));
		return Optional.of(serviceList);
	}
	
	public Boolean checkGroupServer(int sercverIndex){
		Collection<Boolean> serverStateIterator = serviceMenu.row(sercverIndex).values();
		Boolean groupServerState = Boolean.TRUE;
		Iterator<Boolean> iterator = serverStateIterator.iterator();
		while(iterator.hasNext()){
			groupServerState = iterator.next()&&groupServerState;
		}
		
		return groupServerState;
	}
	
	private int firstGroupService(){
		return 1;
	}
	
	private int loadingInAdvanceService(){
		return 0;
	}
	
	private int beforeServiceLayer(int layer){
		return layer - 1;
	}

}