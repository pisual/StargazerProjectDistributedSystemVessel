package com.stargazerproject.cache.resources.temporarycache.cacheremovallistener;

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
import com.stargazerproject.model.order.impl.Order;

@Component
@Qualifier("temporaryCacheOrderCacheRemovalListener")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TemporaryCacheOrderCacheRemovalListener implements BaseCharacteristic<RemovalListener<String, Order>>{

	private RemovalListener<String, Order> removalListener;
	
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	public TemporaryCacheOrderCacheRemovalListener() {
		initializationRemovalListener();
	}
	
	@Override
	@Bean(name="temporaryCacheOrderCacheRemovalListenerCharacteristic")
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
//					CoreLogManaged.getInstance().WARN(OrderCacheConfigurationFactory.class, "Order Has Been Remove By Garbage Collection");
					break;
					/**表明最近一次写条目或获取条目的时间超时**/
				case "EXPIRED":
//					CoreLogManaged.getInstance().WARN(OrderCacheConfigurationFactory.class, "Order Has Been Remove By Timeout");
					break;
					/**表明用户手动的移除条目**/
				case "EXPLICIT":
			//		CoreLogManaged.getInstance().INFO(OrderCacheConfigurationFactory.class, "Order Has Been Remove By Normal Way");
					break;
					/**表明条目不是真正的被移除，只是value值被改变**/
				case "REPLACED":
//					CoreLogManaged.getInstance().INFO(OrderCacheConfigurationFactory.class, "Order Value Has Been Change");
					break;
					/**表明由于Cache的长度达到或接近设置的最大限制，条目被移除**/
				case "SIZE":
//					CoreLogManaged.getInstance().WARN(OrderCacheConfigurationFactory.class, "Order Has Been Remove By Reached The Maximum Queue Length");
					break;
				default:
//					CoreLogManaged.getInstance().ERROR(OrderCacheConfigurationFactory.class, "An Unknown Error");
					break;
				}
			}	
		};
	}

}
