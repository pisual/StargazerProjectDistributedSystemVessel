package com.stargazerproject.zookeeper.model;

import org.apache.curator.framework.api.CuratorListener;

public class SingleWatcher {
	private CuratorListener curatorListener;
	private String nodeName;
	
	public SingleWatcher(CuratorListener curatorListener, String nodeName) {
		super();
		this.curatorListener = curatorListener;
		this.nodeName = nodeName;
	}

	public CuratorListener getCuratorListener() {
		return curatorListener;
	}

	public String getNodeName() {
		return nodeName;
	}
	
}
