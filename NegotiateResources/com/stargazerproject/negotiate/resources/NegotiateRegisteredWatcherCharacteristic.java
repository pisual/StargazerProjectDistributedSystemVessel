package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.negotiate.NegotiateRegisteredWatcher;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="negotiateRegisteredWatcher")
@Qualifier("negotiateRegisteredWatcher")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateRegisteredWatcherCharacteristic implements NegotiateRegisteredWatcher, BaseCharacteristic<NegotiateRegisteredWatcher>{

	private Optional<CuratorFramework> curatorFramework;
	
	@Override
	@Bean(name="negotiateRegisteredWatcherCharacteristic")
	@Lazy(true)
	public Optional<NegotiateRegisteredWatcher> characteristic() {
		NegotiateRegisteredWatcherInitialize();
		return Optional.of(this);
	}
	
	private void NegotiateRegisteredWatcherInitialize(){
		curatorFramework = BeanContainer.instance().getBean(Optional.of("negotiateCuratorFrameworkCharacteristic"), Optional.class);
	}
	
	@Override
	public <T> void registeredCirculationWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<T> watch) throws Exception {
		 PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework.get(), nodePath.get() + nodeName.get(), true); 
		 pathChildrenCache.getListenable().addListener((PathChildrenCacheListener)watch.get());
		 pathChildrenCache.start();
	}

	@Override
	public <T> void registeredSingleWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<T> watch) throws Exception{
		TreeCache treeCache = new TreeCache(curatorFramework.get(), nodePath.get() + nodeName.get()); 
		treeCache.getListenable().addListener((TreeCacheListener)watch.get());
		treeCache.start();
	}

}
