package com.stargazerproject.system.initialize;

import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.model.server.ListenSocketConfiguration;
import com.stargazerproject.server.ReceiveObjectServer;

public class NetworkInitialize {
	private static NetworkInitialize networkInitialize;

	public static final NetworkInitialize getInstance() {
		if (networkInitialize == null) {
			networkInitialize = new NetworkInitialize();
		}
		return networkInitialize;
	}

	public void initialize() {
		LocalityLog.getInstance().INFO(NetworkInitialize.class, "NetworkInitialize Initialize");
		ReceiveObjectServer.getInstance(new ListenSocketConfiguration(10741,"127.0.0.1",false,4));
	}

	private NetworkInitialize() {
	}
}
