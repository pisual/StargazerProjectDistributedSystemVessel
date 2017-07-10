package com.stargazerproject.cells.model;

import com.stargazerproject.cache.base.impl.PermanentCacheImpl;

public class CellsZone {
	
	/**
	 *区域名称 
	 * **/
	private String cellsZoneName;
	
	/**
	 * 计算节点列表
	 * **/
	private PermanentCacheImpl<Integer,ComputingNode> CcomputingNodeList;
	
	public CellsZone() {
		this.CcomputingNodeList = new PermanentCacheImpl<Integer,ComputingNode>();
	}
	
	public void addComputingNode(Integer serverNumber, ComputingNode computingNode){
		CcomputingNodeList.put(serverNumber, computingNode);
	}
	
	public void removeComputingNode(Integer serverNumber){
		CcomputingNodeList.remove(serverNumber);
	}
	
	public ComputingNode getComputingNode(Integer serverNumber){
		return CcomputingNodeList.get(serverNumber);
	}

}
