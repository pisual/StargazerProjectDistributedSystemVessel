package com.stargazerproject.zookeeper.model;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public final class ZookeeperPropertiesNodeData {
	
	private HashMap<String, String> propertiesMap;
	private static final Schema<ZookeeperPropertiesNodeData> zookeeperPropertiesNodeData_SCHEMA;
	
	static{
		zookeeperPropertiesNodeData_SCHEMA = RuntimeSchema.getSchema(ZookeeperPropertiesNodeData.class);
	}
	
	public ZookeeperPropertiesNodeData(HashMap<String, String> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

	public HashMap<String, String> getPropertiesMap() {
		return propertiesMap;
	}

	/**
	 * 获取序列化Byte数组数据
	 * **/
	public byte[] getByteData(){
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		LinkedBuffer BUFFER = LinkedBuffer.allocate();
		byte[] zookeeperPropertiesNodeData = null;
		try {
			ProtobufIOUtil.writeTo(temp, this, zookeeperPropertiesNodeData_SCHEMA, BUFFER);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		zookeeperPropertiesNodeData = temp.toByteArray();
		return zookeeperPropertiesNodeData;
	}
	
	@Override
	public String toString() {
		String data = "";
		Iterator<Entry<String, String>> iterator = propertiesMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry = (Entry<String, String>)iterator.next();
			data += entry.getKey() + " : " + entry.getValue() + " ";
		}
		return data;
	}
}
