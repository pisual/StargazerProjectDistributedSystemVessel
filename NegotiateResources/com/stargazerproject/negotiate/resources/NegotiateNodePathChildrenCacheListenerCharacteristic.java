package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;

@Component(value="negotiateRegisteredWatcher")
@Qualifier("negotiateRegisteredWatcher")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateNodePathChildrenCacheListenerCharacteristic implements BaseCharacteristic<PathChildrenCacheListener>{

	private PathChildrenCacheListener pathChildrenCacheListener;
	
	@Override
	@Bean(name="negotiateNodePathChildrenCacheListenerCharacteristic")
	@Lazy(true)
	public Optional<PathChildrenCacheListener> characteristic() {
		zookeeeperConfigurationInitialize();
		return Optional.of(pathChildrenCacheListener);
	}
	
	private void zookeeeperConfigurationInitialize() {
		pathChildrenCacheListener = new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				ChildData data = event.getData();
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED : ");
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED : ");
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED : ");
					break;
				case CONNECTION_SUSPENDED:
					System.out.println("CONNECTION_SUSPENDED: ");
					break;
				case CONNECTION_RECONNECTED:
					System.out.println("CONNECTION_RECONNECTED : ");
					break;
				case CONNECTION_LOST:
					System.out.println("CONNECTION_LOST : ");
					break;
				case INITIALIZED:
					System.out.println("INITIALIZED : ");
					break;
				default:
					break;
				}
			}
		};
	}

}
