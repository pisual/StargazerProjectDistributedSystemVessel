package com.stargazerproject.zookeeper.server;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.recipes.cache.PathChildrenCache;

import com.stargazerproject.zookeeper.ZookeeperInitialize;
import com.stargazerproject.zookeeper.model.InfiniteWatcher;
import com.stargazerproject.zookeeper.model.SingleWatcher;
import com.stargazerproject.zookeeper.model.SynchronousZookeeperData;


public final class ServerConnectingZookeeper extends ZookeeperInitialize implements ServerZookeeperMethod{
	
	private static ServerConnectingZookeeper serverConnectingZookeeper;
	
	public static ServerConnectingZookeeper getInstance(){
		if(null == serverConnectingZookeeper){
			serverConnectingZookeeper = new ServerConnectingZookeeper();
		}
		return serverConnectingZookeeper;
	}
	
	private ServerConnectingZookeeper() {
		super();
	}

	@Override
	public void creatNode(SynchronousZookeeperData synchronousZookeeperData) throws Exception{
			client.create().withMode(synchronousZookeeperData.getCreateMode())
			               .withACL(synchronousZookeeperData.getIds())
			               .forPath("/" + synchronousZookeeperData.getNodeName(), synchronousZookeeperData.getNodeData());

	}

	@Override
	public List<String> getPathNode(String nodeName) throws Exception {
		return client.getChildren().forPath("/" + nodeName);
	}
	
	@Override
	public void deleteNode(String NodeName) throws Exception {
		client.delete().forPath("/" + NodeName);
	}

	@Override
	public byte[] getNodeData(String nodeName) throws Exception {
		return client.getData().forPath("/" + nodeName);
	}

	@Override
	public boolean checkNodeExists(String nodeName) throws Exception {
		return (null == client.checkExists().forPath("/" + nodeName))?Boolean.FALSE:Boolean.TRUE;
	}

	@Override
	public PathChildrenCache registeredInfiniteWatcher(InfiniteWatcher watcherModel) throws Exception {
		 PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/" + watcherModel.getNodeName(), true); 
		 pathChildrenCache.getListenable().addListener(watcherModel.getPathChildrenCacheListener());
		 pathChildrenCache.start();
		 return pathChildrenCache;
	}

	@Override
	public void registeredSingleWatcher(SingleWatcher singleWatcher){
		ExecutorService executorService = Executors.newCachedThreadPool();
		client.getCuratorListenable().addListener(singleWatcher.getCuratorListener(),executorService);
	}
	
}
