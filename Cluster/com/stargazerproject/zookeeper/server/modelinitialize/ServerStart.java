package com.stargazerproject.zookeeper.server.modelinitialize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.stargazerproject.zookeeper.model.InfiniteWatcher;
import com.stargazerproject.zookeeper.model.factory.PropertiesInfiniteConfigurationFactory;
import com.stargazerproject.zookeeper.model.factory.ServerInitializeZookeeepeInfiniteConfigurationFactory;
import com.stargazerproject.zookeeper.server.ServerZookeeperControl;

public class ServerStart {
	
	public static void main(String[] args) {
		ServerZookeeperControl.getInstance().initializeBaseNodes("StargazerSystem");
		ServerZookeeperControl.getInstance().initializeBaseNodes("StargazerSystem/cells");
		ServerZookeeperControl.getInstance().initializeBaseNodes("StargazerSystem/cells/tasks");
		ServerZookeeperControl.getInstance().initializeBaseNodes("StargazerSystem/cells/server");
		
		InfiniteWatcher infiniteWatcher = ServerInitializeZookeeepeInfiniteConfigurationFactory.getInfiniteWatcherInstance("StargazerSystem/cells/server");
		InfiniteWatcher propertiesInfiniteWatcher = PropertiesInfiniteConfigurationFactory.getInfiniteWatcherInstance("StargazerSystem/cells/tasks");
		try {
			ServerZookeeperControl.getInstance().registeredInfiniteWatcher(infiniteWatcher);
			ServerZookeeperControl.getInstance().registeredInfiniteWatcher(propertiesInfiniteWatcher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
