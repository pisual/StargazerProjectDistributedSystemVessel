package com.stargazerproject.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.stargazerproject.zookeeper.model.ZookeeeperConfiguration;
import com.stargazerproject.zookeeper.model.factory.ZookeeeperConnectionConfigurationFactory;


public class ZookeeperInitialize{
	protected static CuratorFramework client;
	private static RetryPolicy retryPolicy;
	private static ZookeeeperConfiguration zookeeeperConfiguration;
	
	static{
		zookeeeperConfiguration = ZookeeeperConnectionConfigurationFactory.getZookeeeperConfigurationInstance();
	}
	
	protected ZookeeperInitialize() {
			    retryPolicy = new ExponentialBackoffRetry(zookeeeperConfiguration.getSessionTimeout(),
			    		                                      zookeeeperConfiguration.getRetryConnectNumber());
				client = CuratorFrameworkFactory.newClient(zookeeeperConfiguration.getConnectString(), retryPolicy);
				client.getConnectionStateListenable().addListener(zookeeeperConfiguration.getStrategyCellsConnectionStateListener());
				client.start();
	}
}
