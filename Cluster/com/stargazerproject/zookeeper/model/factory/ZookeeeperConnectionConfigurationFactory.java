package com.stargazerproject.zookeeper.model.factory;

import com.stargazerproject.zookeeper.listener.StrategyCellsConnectionStateListener;
import com.stargazerproject.zookeeper.model.ZookeeeperConfiguration;


public final class ZookeeeperConnectionConfigurationFactory {
	private static ZookeeeperConfiguration zookeeeperConfiguration;
	private static ZookeeeperConnectionConfigurationFactory zookeeeperConfigurationFactory;
	
	public static ZookeeeperConfiguration getZookeeeperConfigurationInstance(){
		if(null == zookeeeperConfiguration){
			zookeeeperConfigurationFactory = new ZookeeeperConnectionConfigurationFactory();
			zookeeeperConfigurationFactory.zookeeeperConfigurationInitialize();
		}
		return zookeeeperConfiguration;
	}
	
	private ZookeeeperConnectionConfigurationFactory() {
	}
	
	private void zookeeeperConfigurationInitialize(){
		zookeeeperConfiguration = new ZookeeeperConfiguration(
				"10.0.1.13:2181",
				3000,
				3,
				new StrategyCellsConnectionStateListener(3)
				);
	}
}
