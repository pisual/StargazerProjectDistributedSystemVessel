package com.stargazerproject.negotiate.resources.impl;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.resources.parameter.InjectParameters;
import com.stargazerproject.util.SerializableUtil;

/** 
 *  @name 参数服务监听器（模拟模块）
 *  @illustrate 监听指定目录下节点的注册，一旦注册，根据相应规则注入参数
 *  @author Felixerio
 *  **/
@Component(value="negotiateParametersInjectInitializationListenerCharacteristic")
@Qualifier("negotiateParametersInjectInitializationListenerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateParametersInjectInitializationListenerCharacteristic implements BaseCharacteristic<TreeCacheListener>{
	
	/** @name 建组区路径 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_ZoneNodePath;
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;
	
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;
	
	private TreeCacheListener treeCacheListener;
	
	public NegotiateParametersInjectInitializationListenerCharacteristic() {
	}
	
	@Override
	public Optional<TreeCacheListener> characteristic() {
		treeCacheListenerConfigurationInitialize();
		return Optional.of(treeCacheListener);
	}
	
	protected void treeCacheListenerConfigurationInitialize() {
		treeCacheListener = new TreeCacheListener(){
			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				switch (event.getType()) {  
				case NODE_REMOVED:  
					nodeNegotiate.deleteNode(Optional.of(event.getData().getPath().substring(event.getData().getPath().lastIndexOf("/"))), Optional.of(Kernel_Negotiate_BasePath_ZoneNodePath));
					log.INFO(this, event.getData().getPath()+" Has Remove, Deletes the primary node operation");
					break;
				case NODE_ADDED:  
					log.INFO(this, "The node is detected to be added : " + event.getData().getData());
					TimeUnit.SECONDS.sleep(1);
					byte[] byteArray = SerializableUtil.serialize(new InjectParameters());
					nodeNegotiate.updateNodeData(Optional.of(event.getData().getPath()), Optional.of(""),Optional.of(byteArray));
					log.INFO(this, "The newly added node has been placed in the parameters " + event.getData().getPath());
					break;  
			    default:  
			    	    break;  
                  }  
			}
		};
	}

}
