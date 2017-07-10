package com.stargazerproject.zookeeper.client;

import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;

import com.stargazerproject.zookeeper.model.InfiniteWatcher;
import com.stargazerproject.zookeeper.model.SingleWatcher;
import com.stargazerproject.zookeeper.model.SynchronousZookeeperData;
import com.stargazerproject.zookeeper.model.ZookeeperNodeData;
import com.stargazerproject.zookeeper.model.factory.ZookeeperNodeDataFactory;

public class ConnectionServer {

	private static class ConnectionServerInstance{
		private static ClientConnectingZookeeper clientConnectingZookeeper;
		private static ConnectionServer connectionServer;
		private static ZookeeperNodeData zookeeperNodeData;
	
		static{
			connectionServer = new ConnectionServer();
			zookeeperNodeData = ZookeeperNodeDataFactory.getZookeeperNodeDataInstance();
			clientConnectingZookeeper = ClientConnectingZookeeper.getInstance();
		}
	}
	
	public static ConnectionServer getInstance(){
		return ConnectionServerInstance.connectionServer;
	}
	
	private ConnectionServer() {}
	
	/**初始化注册服务器信息**/
	public void registeredSystemInformationNodes(){
		final String dataNodePath = ConnectionServerInstance.zookeeperNodeData.getBasePath()+"/"+ConnectionServerInstance.zookeeperNodeData.getCellsID();
		final byte[] nodeBytedata = ConnectionServerInstance.zookeeperNodeData.getByteData();
		SynchronousZookeeperData synchronousZookeeperData = new SynchronousZookeeperData(
				                                                dataNodePath, 
				                                                nodeBytedata, 
				                                                Ids.OPEN_ACL_UNSAFE,
				                                                CreateMode.EPHEMERAL);
		try {
			ConnectionServerInstance.clientConnectingZookeeper.creatNode(synchronousZookeeperData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**注册循环触发监听器
	 * @throws Exception 
	 * 
	 * **/
	public PathChildrenCache registeredInfiniteWatcher(InfiniteWatcher infiniteWatcher) throws Exception{
		return ConnectionServerInstance.clientConnectingZookeeper.registeredInfiniteWatcher(infiniteWatcher);
	}
	
	/**
	 * 注册单次监听器
	 * **/
	public void registeredSingleWatcher(SingleWatcher singleWatcher){
		ConnectionServerInstance.clientConnectingZookeeper.registeredSingleWatcher(singleWatcher);
	}
	
	public void deleteNode(String NodeName){
		try {
			ConnectionServerInstance.clientConnectingZookeeper.deleteNode(NodeName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
