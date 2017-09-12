package com.stargazerproject.cache.impl.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.order.impl.Order;

@Component
@Qualifier("tOrderCacheRemovalListener")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderCacheRemovalListenerCharacteristic implements BaseCharacteristic<RemovalListener<String, Order>>{

	private RemovalListener<String, Order> removalListener;
	
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	public OrderCacheRemovalListenerCharacteristic() {
		initializationRemovalListener();
	}
	
	@Override
	@Bean(name="OrderCacheRemovalListenerCharacteristic")
	public Optional<RemovalListener<String, Order>> characteristic() {
		initializationRemovalListener();
		return Optional.of(removalListener);
	}
	
	
	private void initializationRemovalListener(){
		removalListener = new RemovalListener<String, Order>(){
			@Override
			public void onRemoval(RemovalNotification<String, Order> notification) {
				switch (notification.getCause().name()) {
				/**表明键或值被垃圾回收**/
				case "COLLECTED":
					log.WARN(OrderCacheRemovalListenerCharacteristic.class, "Order Has Been Remove By Garbage Collection");
					break;
					/**表明最近一次写条目或获取条目的时间超时**/
				case "EXPIRED":
					log.WARN(OrderCacheRemovalListenerCharacteristic.class, "Order Has Been Remove By Timeout");
					break;
					/**表明用户手动的移除条目**/
				case "EXPLICIT":
					log.INFO(OrderCacheRemovalListenerCharacteristic.class, "Order Has Been Remove By Normal Way");
					break;
					/**表明条目不是真正的被移除，只是value值被改变**/
				case "REPLACED":
					log.INFO(OrderCacheRemovalListenerCharacteristic.class, "Order Value Has Been Change");
					break;
					/**表明由于Cache的长度达到或接近设置的最大限制，条目被移除**/
				case "SIZE":
					log.WARN(OrderCacheRemovalListenerCharacteristic.class, "Order Has Been Remove By Reached The Maximum Queue Length");
					break;
				default:
					log.ERROR(OrderCacheRemovalListenerCharacteristic.class, "An Unknown Error");
					break;
				}
			}	
		};
	}

}
