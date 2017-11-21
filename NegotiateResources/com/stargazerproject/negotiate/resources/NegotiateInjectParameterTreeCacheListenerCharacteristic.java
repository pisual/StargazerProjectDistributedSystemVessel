package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.negotiate.Negotiate;

@Component(value="negotiateInjectParameterTreeCacheListener")
@Qualifier("negotiateInjectParameterTreeCacheListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateInjectParameterTreeCacheListenerCharacteristic implements BaseCharacteristic<TreeCacheListener>{

	@Autowired
	@Qualifier("logRecord")
	private LogMethod baseLog;
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;
	
	@Autowired
	@Qualifier("byteArrayCache")
	protected BigCache<String, byte[]> byteArrayCache;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameterCahce;
	
	private TreeCacheListener treeCacheListener;

	@Override
	@Bean(name="negotiateInjectParameterTreeCacheListenerCharacteristic")
	@Lazy(true)
	public Optional<TreeCacheListener> characteristic() {
		zookeeeperConfigurationInitialize();
		return Optional.of(treeCacheListener);
	}
	
	
	protected void zookeeeperConfigurationInitialize() {
		treeCacheListener = new TreeCacheListener(){
			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				switch (event.getType()) {  
				case NODE_REMOVED:  
					baseLog.INFO(this, event.getData().getPath()+" Has Remove");
					break;
				case NODE_ADDED:  
					baseLog.INFO(this, event.getData().getPath()+" Has NODE_ADDED");
					break;  
				case NODE_UPDATED:
					byteArrayCache.put(Optional.of("AcquireCellsParameter"), event.getData().getData());
					baseLog.INFO(this, event.getData().getPath()+" Has Ipdated , Data : " + event.getData().getData());
					break;  
			    default:  
			    	    break;  
                  }  
			}
		};
	}

}
