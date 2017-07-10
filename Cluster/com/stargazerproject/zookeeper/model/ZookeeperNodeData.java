package com.stargazerproject.zookeeper.model;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class ZookeeperNodeData {
	
	private String ip;
	private String socket;
	private String basePath;
	private String cellsID;
	
	private static final Schema<ZookeeperNodeData> zookeeperNodeData_SCHEMA;
	
	static{
		zookeeperNodeData_SCHEMA = RuntimeSchema.getSchema(ZookeeperNodeData.class);
	}
	
	public ZookeeperNodeData(String ip, String socket, String basePath, String cellsID) {
		this.ip = ip;
		this.socket = socket;
		this.basePath = basePath;
		this.cellsID = cellsID;
	}
	
	public String getIp() {
		return ip;
	}
	public String getSocket() {
		return socket;
	}

	public String getBasePath() {
		return basePath;
	}

	public String getCellsID() {
		return cellsID;
	}
	
	public byte[] getByteData(){
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		LinkedBuffer BUFFER = LinkedBuffer.allocate();
		byte[] zookeeperNode = null;
		try {
			ProtobufIOUtil.writeTo(temp, this, zookeeperNodeData_SCHEMA, BUFFER);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		zookeeperNode = temp.toByteArray();
		return zookeeperNode;
	}
	
	@Override
	public String toString() {
		String data = "IP: "+ip;
		       data += " socket: "+socket;
		       data += " basePath: "+basePath;
		       data += " cellsID: "+cellsID;
		return data;
	}
}
