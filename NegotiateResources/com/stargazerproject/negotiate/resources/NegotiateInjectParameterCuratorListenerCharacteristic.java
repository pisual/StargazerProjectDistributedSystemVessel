package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
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

@Component(value="negotiateNodeCuratorListener")
@Qualifier("negotiateNodeCuratorListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateInjectParameterCuratorListenerCharacteristic implements BaseCharacteristic<CuratorListener>{

	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameterCahce;
	
	@Autowired
	@Qualifier("byteArrayCache")
	protected Cache<String, byte[]> byteArrayCache;
	
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
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case DELETE:
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case EXISTS:
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case GET_DATA:
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case SET_DATA:
					
					systemParameterCahce.put(Optional.of("InjectParameter"), Optional.of("Continue"));
					byteArrayCache.put(Optional.of("InjectParameterData"), value);
					
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case CHILDREN:
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case SYNC:
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case GET_ACL:
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case SET_ACL:
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case WATCHED:
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				case CLOSING:
					baseLog.INFO(this, "NegotiateInjectParameterCuratorListener " + event.getType());
					break;
				default:
					break;
				}
            }  
        };
	}

}
