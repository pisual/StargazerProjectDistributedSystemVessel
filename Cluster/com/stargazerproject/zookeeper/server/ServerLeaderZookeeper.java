package com.stargazerproject.zookeeper.server;

import java.io.IOException;

import org.apache.curator.framework.recipes.leader.LeaderLatch;

import com.stargazerproject.zookeeper.ZookeeperLeaderInitialize;
import com.stargazerproject.zookeeper.model.LeaderConfiguration;
import com.stargazerproject.zookeeper.model.ZookeeeperConfiguration;


public final class ServerLeaderZookeeper extends ZookeeperLeaderInitialize implements ServerZookeeperLeaderMethod{
	
	/****/
	protected LeaderLatch leade;
	
	public ServerLeaderZookeeper(ZookeeeperConfiguration zookeeeperConfiguration, LeaderConfiguration leaderConfiguration) {
		super(zookeeeperConfiguration);
		leade = new LeaderLatch(client, "/" + leaderConfiguration.getLeaderPath());
		leade.addListener(leaderConfiguration.getLeaderLatchListener());
	}

	@Override
	public void startSelectLeader() throws Exception {
		leade.start();
	}

	@Override
	public void giveUpLeader() throws IOException {
		leade.close();
	}

	@Override
	public void startSelectLeaderAwait() throws Exception {
		leade.await();
	}
	
	@Override
	public String getSelectLeader() throws Exception {
		return leade.getLeader().getId();
	}
	
}
