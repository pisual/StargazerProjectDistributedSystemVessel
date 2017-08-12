package com.stargazerproject.cache.impl.resources.shell;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.model.order.impl.Order;

/** 
 *  @name SystemParameter的Map初始化
 *  @illustrate 对SystemParameter所需要的特征Cache进行初始化
 *  @author Felixerio
 *  **/
@Component(value="orderCahceShell")
@Qualifier("orderCahceShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderCahceShell implements BaseCharacteristic<Cache<String, Order>>{

	@Autowired
	@Qualifier("OrderCahceCharacteristic")
	protected Cache<String, Order> orderCahceCharacteristic;
	
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	private OrderCahceShell() {}
	
	@Override
	@Bean(name="orderCahceCharacteristicInitialize")
	@Lazy(true)
	public Optional<Cache<String, Order>> characteristic() {
		getParamentListFromClass();
		return Optional.of(systemParameterCahceCharacteristic);
	}
	
	/**
	 * @illustrate 从CLass文件获取参数并注入到Mao列表
	 * **/
	private void getParamentListFromClass(){
		try {
			Field[] valueFields = stargazerProjectParameterList.getClass().getDeclaredFields();
			for(Field valueField : valueFields){
				valueField.setAccessible(true);
				systemParameterCahceCharacteristic.put(Optional.of(valueField.getName()), Optional.of(valueField.get(valueField.getName()).toString()));
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			baseLog.ERROR(this, e.getLocalizedMessage());
		}
	}
}