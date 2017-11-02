package com.stargazerproject.negotiate.base.impl;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.negotiate.Negotiate;

public abstract class BaseNegotiateImpl implements Negotiate{
	
	protected Negotiate negotiate;

	@Override
	public void creatPersistentNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		negotiate.creatPersistentNode(nodeName, nodePath, nodeData);
	}
	
	@Override
	public void updateNodeData(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		negotiate.updateNodeData(nodeName, nodePath, nodeData);
	}

	@Override
	public void creatEphemeralNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		negotiate.creatEphemeralNode(nodeName, nodePath, nodeData);
	}

	@Override
	public void deleteNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		negotiate.deleteNode(nodeName, nodePath);
	}

	@Override
	public List<String> getPathNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiate.getPathNode(nodeName, nodePath);
	}

	@Override
	public byte[] getNodeData(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiate.getNodeData(nodeName, nodePath);
	}

	@Override
	public boolean checkNodeExists(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiate.checkNodeExists(nodeName, nodePath);
	}

	@Override
	public <T> void registeredCirculationWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<T> watch) throws Exception {
		negotiate.registeredCirculationWatcher(nodeName, nodePath, watch);
	}

	@Override
	public <T> void registeredSingleWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<T> watch) throws Exception{
		negotiate.registeredSingleWatcher(nodeName, nodePath, watch);
	}

	@Override
	public <T> void startSelectLeader(Optional<String> nodeName, Optional<String> nodePath, Optional<T> listener) throws Exception {
		negotiate.startSelectLeader(nodeName, nodePath, listener);
	}

	@Override
	public void giveUpLeader(Optional<String> nodeName, Optional<String> nodePath) throws IOException {
		negotiate.giveUpLeader(nodeName, nodePath);
	}

	@Override
	public String getSelectLeader(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiate.getSelectLeader(nodeName, nodePath);
	}
	
	@Override
	public boolean creatLock(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiate.creatLock(nodeName, nodePath);
	}
	
	@Override
	public void releaseLock(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		negotiate.releaseLock(nodeName, nodePath);
	}

	@Override
	public void start() {
		negotiate.start();
	}
	
	@Override
	public void close(){
		negotiate.close();
	}

}
