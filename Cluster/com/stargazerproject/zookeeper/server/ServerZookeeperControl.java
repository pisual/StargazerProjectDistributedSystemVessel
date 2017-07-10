package com.stargazerproject.zookeeper.server;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;

import com.stargazerproject.zookeeper.model.InfiniteWatcher;
import com.stargazerproject.zookeeper.model.SynchronousZookeeperData;
import com.stargazerproject.zookeeper.model.ZookeeperPropertiesNodeData;

public class ServerZookeeperControl {

	private static class ServerZookeeperControlInstance{
		
		private static ServerConnectingZookeeper serverConnectingZookeeper;
		private static ServerZookeeperControl serverZookeeperControl;
		
		static{
			serverZookeeperControl = new ServerZookeeperControl();
			serverConnectingZookeeper = ServerConnectingZookeeper.getInstance();
		}
		
		private ServerZookeeperControlInstance() {}
	}
	
	public static ServerZookeeperControl getInstance(){
		return ServerZookeeperControlInstance.serverZookeeperControl;
	}
	
	private ServerZookeeperControl() {}
	
	/**
	 * 配置基本节点数据
	 * 使用方法例如：进行基本节点存在初始化
	 * 		ServerZookeeperControl.getInstance().initializeBaseNodes("StargazerSystem");
	 *	    ServerZookeeperControl.getInstance().initializeBaseNodes("StargazerSystem/cells");
	 *	    ServerZookeeperControl.getInstance().initializeBaseNodes("StargazerSystem/cells/server");
	 * 
	 * **/
	public void initializeBaseNodes(String rootNodeName){
		SynchronousZookeeperData synchronousZookeeperData = new SynchronousZookeeperData(
				                                                rootNodeName, 
				                                                rootNodeName.getBytes(), 
                                                                Ids.OPEN_ACL_UNSAFE,
                                                                CreateMode.PERSISTENT);
		try {
			Boolean result = ServerZookeeperControlInstance.serverConnectingZookeeper.checkNodeExists(rootNodeName);
			if(result == true){
				System.out.println(rootNodeName+" 节点已经存在 添加失败");
			}
			else{
				ServerZookeeperControlInstance.serverConnectingZookeeper.creatNode(synchronousZookeeperData);
				System.out.println(rootNodeName+" 节点已经添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void creatEphemeralNodes(String rootNodeName, ZookeeperPropertiesNodeData zookeeperPropertiesNodeData){
		SynchronousZookeeperData synchronousZookeeperData = new SynchronousZookeeperData(
				                                                rootNodeName, 
				                                                zookeeperPropertiesNodeData.getByteData(), 
                                                                Ids.OPEN_ACL_UNSAFE,
                                                                CreateMode.EPHEMERAL);
		try {
			Boolean result = ServerZookeeperControlInstance.serverConnectingZookeeper.checkNodeExists(rootNodeName);
			if(result == true){
				System.out.println(rootNodeName+" 节点已经存在 添加失败");
			}
			else{
				ServerZookeeperControlInstance.serverConnectingZookeeper.creatNode(synchronousZookeeperData);
				System.out.println(rootNodeName+" 节点已经添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**注册循环触发监听器
	 * 
	 * **/
	public void registeredInfiniteWatcher(InfiniteWatcher infiniteWatcher){
		try {
			ServerZookeeperControlInstance.serverConnectingZookeeper.registeredInfiniteWatcher(infiniteWatcher);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
