package com.stargazerproject.system.initialize;

import java.util.concurrent.TimeUnit;

import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.zookeeper.client.ClientStart;

public class ClusterInitialize {
	private static ClusterInitialize clusterInitialize;

	public static final ClusterInitialize getInstance() {
		if (clusterInitialize == null) {
			clusterInitialize = new ClusterInitialize();
		}
		return clusterInitialize;
	}

	public void initialize() {
		LocalityLog.getInstance().INFO(ClusterInitialize.class, "ClusterInitialize Initialize");
		ClientStart clientStart = new ClientStart();
		clientStart.clusterStart();

		for(int i=0; i<10; i++){
			if(SystemParameterCahce.getInstance().getString("CluStar_Cache_Initialize").equals("true")){
				System.out.println("获取到服务器参数，系统完成初始化");
				break;
			}
			else{
				System.out.println("等待系统完成初始化");
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
		}
	}

	private ClusterInitialize() {
	}
}
