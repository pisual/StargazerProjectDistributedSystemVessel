package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.negotiate.NegotiateRegisteredWatcher;

@Component(value="negotiateRegisteredWatcherCharacteristic")
@Qualifier("negotiateRegisteredWatcherCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateRegisteredWatcherCharacteristic implements NegotiateRegisteredWatcher, BaseCharacteristic<NegotiateRegisteredWatcher>{

	@Autowired
	@Qualifier("treeCacheCache")
	private Cache<String, TreeCache> treeCacheCache;
	
	@Autowired
	@Qualifier("negotiateCuratorFrameworkCharacteristic")
	private BaseCharacteristic<CuratorFramework> negotiateCuratorFrameworkCharacteristic;
	
	private CuratorFramework curatorFramework;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private NegotiateRegisteredWatcherCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public NegotiateRegisteredWatcherCharacteristic(Optional<Cache<String, TreeCache>> treeCacheCacheArg,
												   Optional<BaseCharacteristic<CuratorFramework>> negotiateCuratorFrameworkCharacteristicArg) {
		treeCacheCache = treeCacheCacheArg.get();
		negotiateCuratorFrameworkCharacteristic = negotiateCuratorFrameworkCharacteristicArg.get();
	}
	
	@Override
	public Optional<NegotiateRegisteredWatcher> characteristic() {
		NegotiateRegisteredWatcherInitialize();
		return Optional.of(this);
	}
	
	private void NegotiateRegisteredWatcherInitialize(){
		curatorFramework = negotiateCuratorFrameworkCharacteristic.characteristic().get();
	}
	
	@Override
	public <T> void registeredWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<String> watchName, Optional<T> watch) throws Exception {
		if(treeCacheCache.get(watchName).isPresent() != Boolean.TRUE){
			TreeCache treeCache = new TreeCache(curatorFramework, nodePath.get() + nodeName.get()); 
			treeCache.getListenable().addListener((TreeCacheListener)watch.get());
			treeCache.start();
			treeCacheCache.put(watchName, Optional.of(treeCache));
		}
		else{
			throw new IllegalArgumentException(watchName.get() +" has registered");
		}
	}
	
	@Override
	public <T> void removeWatcher(Optional<String> watchName) throws Exception {
		if(treeCacheCache.get(watchName).isPresent()){
			TreeCache treeCache = treeCacheCache.get(watchName).get();
			treeCache.close();
		}
		else{
			throw new IllegalArgumentException(watchName.get() +" don't registered");
		}
	}

}
