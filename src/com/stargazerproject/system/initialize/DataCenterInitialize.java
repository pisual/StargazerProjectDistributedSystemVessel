package com.stargazerproject.system.initialize;

import com.stargazerproject.log.collocation.impl.LocalityLog;

public class DataCenterInitialize {
	private static DataCenterInitialize dataCenterInitialize;

	public static final DataCenterInitialize getInstance() {
		if (dataCenterInitialize == null) {
			dataCenterInitialize = new DataCenterInitialize();
		}
		return dataCenterInitialize;
	}

	public void initialize() {
		LocalityLog.getInstance().INFO(DataCenterInitialize.class, "DataCenterInitialize Initialize");
	}

	private DataCenterInitialize() {
	}
}
