package com.stargazerproject.zookeeper.model.factory;

import com.stargazerproject.model.util.WebUtil;
import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.zookeeper.model.ZookeeperNodeData;

public class ZookeeperNodeDataFactory {
	
	private static class ZookeeperNodeDataInstance {
		private static ZookeeperNodeData zookeeperNodeData;
		private static final String basePath;
		private static final String cellsID; 
		private static final String ip;
		private static final String socket;
		
		static {
			basePath = SystemParameterCahce.getInstance().getString("Cells_Base_Path");
			cellsID = SystemParameterCahce.getInstance().getString("CellsID");
			socket = SystemParameterCahce.getInstance().getString("Cells_Socket");
			ip = WebUtil.getServerIP();
			zookeeperNodeData = new ZookeeperNodeData(ip, socket, basePath,cellsID);
		}
		
	}

	public static ZookeeperNodeData getZookeeperNodeDataInstance() {
		return ZookeeperNodeDataInstance.zookeeperNodeData;
	}

	private ZookeeperNodeDataFactory() {
	}
}
