package com.stargazerproject.zookeeper.client;

import com.stargazerproject.system.initialize.ParameterInitialize;
import com.stargazerproject.zookeeper.model.InfiniteWatcher;
import com.stargazerproject.zookeeper.model.factory.ClientInitializeZookeeepeInfiniteConfigurationFactory;

public class ClientStart {
	
	public void clusterStart(){
		ParameterInitialize.getInstance().initialize();
		ConnectionServer.getInstance();
		InfiniteWatcher infiniteWatcher = ClientInitializeZookeeepeInfiniteConfigurationFactory.getInfiniteWatcherInstance("StargazerSystem/cells/tasks");
		try {
			ConnectionServer.getInstance().registeredInfiniteWatcher(infiniteWatcher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionServer.getInstance().registeredSystemInformationNodes();
		
	}
	
	public static void main(String[] args) {
		ParameterInitialize.getInstance().initialize();
		ConnectionServer.getInstance();
		InfiniteWatcher infiniteWatcher = ClientInitializeZookeeepeInfiniteConfigurationFactory.getInfiniteWatcherInstance("StargazerSystem/cells/tasks");
		try {
			ConnectionServer.getInstance().registeredInfiniteWatcher(infiniteWatcher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ConnectionServer.getInstance().registeredSystemInformationNodes();
	}
}
