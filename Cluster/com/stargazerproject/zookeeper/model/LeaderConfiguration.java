package com.stargazerproject.zookeeper.model;

import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

public class LeaderConfiguration {
	
	private String leaderPath;
	private LeaderLatchListener leaderLatchListener;
	
	public LeaderConfiguration(String leaderPath, LeaderLatchListener leaderLatchListener) {
		this.leaderPath = leaderPath;
		this.leaderLatchListener = leaderLatchListener;
	}

	public String getLeaderPath() {
		return leaderPath;
	}

	public LeaderLatchListener getLeaderLatchListener() {
		return leaderLatchListener;
	}
	
}
