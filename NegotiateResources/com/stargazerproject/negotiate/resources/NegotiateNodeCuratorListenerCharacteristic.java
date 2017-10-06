package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;

@Component(value="negotiateNodeCuratorListener")
@Qualifier("negotiateNodeCuratorListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateNodeCuratorListenerCharacteristic implements BaseCharacteristic<CuratorListener>{

	private CuratorListener curatorListener;
	
	@Override
	@Bean(name="negotiateNodeCuratorListenerCharacteristic")
	@Lazy(true)
	public Optional<CuratorListener> characteristic() {
		zookeeeperConfigurationInitialize();
		return Optional.of(curatorListener);
	}
	
	private void zookeeeperConfigurationInitialize() {
		curatorListener = new CuratorListener() {  
            @Override  
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {  
				switch (event.getType()) {
				case CREATE:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case DELETE:
					System.out.println("已经触发了" + event.getType() + "事件！");
					break;
				case EXISTS:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case GET_DATA:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case SET_DATA:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case CHILDREN:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case SYNC:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case GET_ACL:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case SET_ACL:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case WATCHED:
					System.out.println("已经触发了" + event.getType() + "事件！");
					break;
				case CLOSING:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				default:
					break;
				}
            }  
        };
	}

}
