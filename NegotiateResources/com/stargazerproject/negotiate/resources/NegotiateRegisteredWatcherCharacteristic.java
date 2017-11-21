package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
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
import com.stargazerproject.negotiate.NegotiateRegisteredWatcher;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="negotiateRegisteredWatcher")
@Qualifier("negotiateRegisteredWatcher")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateRegisteredWatcherCharacteristic implements NegotiateRegisteredWatcher, BaseCharacteristic<NegotiateRegisteredWatcher>{

	private Optional<CuratorFramework> curatorFramework;
	
	@Autowired
	@Qualifier("treeCacheCache")
	private Cache<String, TreeCache> treeCacheCache;
	
	@Override
	@Bean(name="negotiateRegisteredWatcherCharacteristic")
	@Lazy(true)
	public Optional<NegotiateRegisteredWatcher> characteristic() {
		NegotiateRegisteredWatcherInitialize();
		return Optional.of(this);
	}
	
	@SuppressWarnings("unchecked")
	private void NegotiateRegisteredWatcherInitialize(){
		curatorFramework = BeanContainer.instance().getBean(Optional.of("negotiateCuratorFrameworkCharacteristic"), Optional.class);
	}
	
	@Override
	public <T> void registeredWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<String> watchName, Optional<T> watch) throws Exception {
		if(treeCacheCache.get(watchName).isPresent() != Boolean.TRUE){
			TreeCache treeCache = new TreeCache(curatorFramework.get(), nodePath.get() + nodeName.get()); 
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
