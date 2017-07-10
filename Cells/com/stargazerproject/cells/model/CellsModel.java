package com.stargazerproject.cells.model;

import com.stargazerproject.cache.base.impl.PermanentCacheImpl;
import com.stargazerproject.zookeeper.model.BackupLevel;

/**
 * Cells Model Zookeeper配置
 * 
 * 
 * **/
public class CellsModel{
	
	/**Cells Zookeeper 特定集群名称(特定集群项目，同名分化)
	 * $CellsBasicPath$/Yandere_Cluster
	 * **/
	private String cellsSpeciesCluster;
	
	/**Cells Zookeeper 集群事务备份级别**/
	private BackupLevel cellsBackupLevel;
	
	/**Cells 需要计算节点数目**/
	private Integer cellsNumber;
	
	/**区域列表**/
	private PermanentCacheImpl<String,CellsZone> zoneList;
	
	public CellsModel(String cellsSpeciesCluster, BackupLevel cellsBackupLevel) {
		this.cellsSpeciesCluster = cellsSpeciesCluster;
		this.cellsBackupLevel = cellsBackupLevel;
	}
	
	public void addComputingNode(String zoneName, CellsZone cellsZone){
		zoneList.put(zoneName, cellsZone);
	}
	
	public void removeComputingNode(String zoneName){
		zoneList.remove(zoneName);
	}
	
	public CellsZone getComputingNode(String zoneName){
		return zoneList.get(zoneName);
	}
}
