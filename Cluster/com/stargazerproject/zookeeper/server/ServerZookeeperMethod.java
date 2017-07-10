package com.stargazerproject.zookeeper.server;

import java.util.List;

import org.apache.curator.framework.recipes.cache.PathChildrenCache;

import com.stargazerproject.zookeeper.model.InfiniteWatcher;
import com.stargazerproject.zookeeper.model.SingleWatcher;
import com.stargazerproject.zookeeper.model.SynchronousZookeeperData;

public interface ServerZookeeperMethod {
	
	/**
	 * @MethodName 注册节点数据
	 * @param SynchronousZookeeperData Zookeeper 连接模版数据
	 * @throws Exception
	 * @author Felixerio
	 * **/
	public void creatNode(SynchronousZookeeperData synchronousZookeeperData) throws Exception;
	
	/**
	 * @MethodName 获取Zookeeper指定根路径下的一级节点
	 * @rerurn 节点列表数据
	 * @author Felixerio
	 * @throws Exception 
	 * **/
	public List<String> getPathNode(String nodeName) throws Exception;
	
	/**
	 * @MethodName 获取Zookeeper指定节点数据
	 * @param 节点名称
	 * @return bytr[] 数据
	 * @throws Exception 
	 * @author Felixerio
	 * **/
	public byte[] getNodeData(String nodeName) throws Exception;
	
	/**
	 * @MethodName 删除Zookeeper指定节点
	 * @param 节点名称
	 * @throws Exception 
	 * @author Felixerio
	 * **/
	public void deleteNode(String nodeName) throws Exception;
	
	/**
	 * @MethodName 检测节点是否存在
	 * @param 节点名称
	 * @return Boolean.True :节点存在
	 *         Boolean.False : 节点不存在
	 * @throws Exception 
	 * @author Felixerio
	 * **/
	public boolean checkNodeExists(String nodeName) throws Exception;
	
	/**
	 * @MethodName 注册无限触发的监听器
	 * @param InfiniteWatcher 监听器组合配置
	 * @return PathChildrenCache 
	 * @throws Exception
	 * @author Felixerio
	 * **/
	public PathChildrenCache registeredInfiniteWatcher(InfiniteWatcher infiniteWatcher) throws Exception;
	
	/**
	 * @MethodName 注册单次触发的监听器
	 * @param SingleWatcher 监听器组合配置
	 * @author Felixerio
	 * **/
	public void registeredSingleWatcher(SingleWatcher singleWatcher);
	
}
