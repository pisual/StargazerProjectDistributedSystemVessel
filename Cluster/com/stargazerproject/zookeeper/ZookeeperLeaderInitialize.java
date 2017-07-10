package com.stargazerproject.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.stargazerproject.zookeeper.model.ZookeeeperConfiguration;


public class ZookeeperLeaderInitialize{
	
	/****/
	protected CuratorFramework client;
	/****/
	private RetryPolicy retryPolicy;
	
	protected ZookeeperLeaderInitialize(ZookeeeperConfiguration zookeeeperConfiguration) {
			    retryPolicy = new ExponentialBackoffRetry(zookeeeperConfiguration.getSessionTimeout(),zookeeeperConfiguration.getRetryConnectNumber());
				client = CuratorFrameworkFactory.newClient(zookeeeperConfiguration.getConnectString(), retryPolicy);
				client.getConnectionStateListenable().addListener(zookeeeperConfiguration.getStrategyCellsConnectionStateListener());
				client.start();
	}
}
