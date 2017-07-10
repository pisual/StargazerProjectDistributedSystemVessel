package com.stargazerproject.cells.model;

/**
 * 计算节点
 * 
 * **/
public class ComputingNode {
	
	/**Cells Zookeeper 基本路径 
	 * StargazerSystem/Cells/Server
	 * **/
	private String cellsBasicPath;
	
	/**
	 * Cells集群服务器编号
	 * $CellsBasicPath$/Server/UUID
	 * **/
	private String cellsServer;
	
	/**
	 * Cells集群服务器地址IP
	 * $CellsServer$/CellsServerAddressIP/"10.0.1.12"
	 * **/
	private String cellsServerAddressIP;
	
	/**
	 * Cells集群服务器地址Socket
	 * $CellsServer$/CellsServerAddressSocket/"10841"
	 * **/
	private String cellsServerAddressSocket;
	
	/**
	 * Cells集群服务器地址Socket
	 * $CellsServer$/CellsServerAddressSocket/"10841"
	 * **/
	private String cellsServerLeader;

	
	public ComputingNode(String cellsBasicPath, String cellsServer, String cellsServerAddressIP, String cellsServerAddressSocket) {
		this.cellsBasicPath = cellsBasicPath;
		this.cellsServer = cellsServer;
		this.cellsServerAddressIP = cellsServerAddressIP;
		this.cellsServerAddressSocket = cellsServerAddressSocket;
	}
	
}
