package com.stargazerproject.zookeeper.model;

import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

public class InfiniteWatcher {
	private PathChildrenCacheListener pathChildrenCacheListener;
	private String nodeName;
	
	public InfiniteWatcher(PathChildrenCacheListener pathChildrenCacheListener, String nodeName) {
		super();
		this.pathChildrenCacheListener = pathChildrenCacheListener;
		this.nodeName = nodeName;
	}

	public PathChildrenCacheListener getPathChildrenCacheListener() {
		return pathChildrenCacheListener;
	}

	public String getNodeName() {
		return nodeName;
	}
	
}
