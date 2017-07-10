package com.stargazerproject.system.initialize;

import com.stargazerproject.cache.impl.impl.OrderCache;
import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.queue.base.impl.ReceivEventQueue;

public class CacheInitialize {
	private static CacheInitialize cacheInitialize;

	public static final CacheInitialize getInstance() {
		if (cacheInitialize == null) {
			cacheInitialize = new CacheInitialize();
		}
		return cacheInitialize;
	}

	public void initialize() {
		LocalityLog.getInstance().INFO(CacheInitialize.class, "CacheInitialize Initialize");
		OrderCache.getInstance();
		ReceivEventQueue.getInstance();
	}

	private CacheInitialize() {
	}
}
