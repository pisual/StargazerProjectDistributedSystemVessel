package com.stargazerproject.negotiate;

import java.util.List;

import com.google.common.base.Optional;

public interface NegotiateNodeMethod {
	
	/**
	 * @MethodName 注册永久节点数据
	 * @param Optional<String> nodeName 节点名称
	 *        Optional<byte[]> nodeData 节点数据
	 * @throws Exception
	 * @author Felixerio
	 * **/
	public void creatPersistentNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception;
	
	/**
	 * @MethodName 注册临时节点数据
	 * @param Optional<String> nodeName 节点名称
	 *        Optional<byte[]> nodeData 节点数据
	 * @throws Exception
	 * @author Felixerio
	 * **/
	public void creatEphemeralNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception;
	
	/**
	 * @MethodName 删除指定节点
	 * @param 节点名称
	 * @throws Exception 
	 * @author Felixerio
	 * **/
	public void deleteNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception;
	
	/**
	 * @MethodName 获取指定根路径下的一级节点
	 * @rerurn 节点列表数据
	 * @author Felixerio
	 * @throws Exception 
	 * **/
	public List<String> getPathNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception;
	
	/**
	 * @MethodName 获取指定节点数据
	 * @param 节点名称
	 * @return bytr[] 数据
	 * @throws Exception 
	 * @author Felixerio
	 * **/
	public byte[] getNodeData(Optional<String> nodeName, Optional<String> nodePath) throws Exception;
	
	/**
	 * @MethodName 检测节点是否存在
	 * @param 节点名称
	 * @return Boolean.True :节点存在
	 *         Boolean.False : 节点不存在
	 * @throws Exception 
	 * @author Felixerio
	 * **/
	public boolean checkNodeExists(Optional<String> nodeName, Optional<String> nodePath) throws Exception;
	
}
