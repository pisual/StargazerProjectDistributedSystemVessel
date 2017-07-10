package com.stargazerproject.zookeeper.model.factory;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import com.stargazerproject.zookeeper.model.InfiniteWatcher;

public final class ZookeeepeInfiniteConfigurationFactory {
	private static InfiniteWatcher infiniteWatcher;
	private static ZookeeepeInfiniteConfigurationFactory zookeeeperConfigurationFactory;
	private static PathChildrenCacheListener pathChildrenCacheListener;

	public static InfiniteWatcher getInfiniteWatcherInstance(String nodeName) {
		if (null == infiniteWatcher) {
			if (null == zookeeeperConfigurationFactory) {
				zookeeeperConfigurationFactory = new ZookeeepeInfiniteConfigurationFactory();
			}
			zookeeeperConfigurationFactory.zookeeeperConfigurationInitialize();
			infiniteWatcher = new InfiniteWatcher(pathChildrenCacheListener,
					nodeName);
		}
		return infiniteWatcher;
	}

	private ZookeeepeInfiniteConfigurationFactory() {
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
					System.out.println("CHILD_UPDATED : ");
					break;
				case CONNECTION_RECONNECTED:
					System.out.println("CHILD_UPDATED : ");
					break;
				case CONNECTION_LOST:
					System.out.println("CHILD_UPDATED : ");
					break;
				case INITIALIZED:
					System.out.println("CHILD_UPDATED : ");
					break;
				default:
					break;
				}
			}
		};
	}
}
