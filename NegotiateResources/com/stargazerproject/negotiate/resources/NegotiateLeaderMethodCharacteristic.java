package com.stargazerproject.negotiate.resources;

import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.negotiate.NegotiateLeaderMethod;

@Component(value="negotiateLeaderMethodCharacteristic")
@Qualifier("negotiateLeaderMethodCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateLeaderMethodCharacteristic implements NegotiateLeaderMethod, BaseCharacteristic<NegotiateLeaderMethod>{

	@Autowired
	@Qualifier("leaderLatchParameterCache")
	private Cache<String, LeaderLatch> objectParameterCache;
	
	@Autowired
	@Qualifier("negotiateCuratorFrameworkCharacteristic")
	private BaseCharacteristic<CuratorFramework> negotiateCuratorFrameworkCharacteristic;
	
	private LeaderLatch leaderLatch;
	
	private NegotiateLeaderMethodCharacteristic() {}
	
	public NegotiateLeaderMethodCharacteristic(Optional<Cache<String, LeaderLatch>> objectParameterCache) {}

	@Override
	public Optional<NegotiateLeaderMethod> characteristic() {
		return Optional.of(this);
	}

	@Override
	public <T> void startSelectLeader(Optional<String> nodeName, Optional<String> nodePath, Optional<T> listener)throws Exception {
		leaderLatchInitialization(nodeName, nodePath, Optional.of((LeaderLatchListener)listener));
	}

	@Override
	public void giveUpLeader(Optional<String> nodeName, Optional<String> nodePath) throws IOException {
		objectParameterCache.get(Optional.of(nodePath.get() + nodeName.get())).get().close();;
	}

	@Override
	public String getSelectLeader(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return objectParameterCache.get(Optional.of(nodePath.get() + nodeName.get())).get().getLeader().getId();
	}
	
	private void leaderLatchInitialization(Optional<String> nodeName, Optional<String> nodePath, Optional<LeaderLatchListener> leaderLatchListener){
		leaderLatch = new LeaderLatch(negotiateCuratorFrameworkCharacteristic.characteristic().get(), nodePath.get() + nodeName.get());
		leaderLatch.addListener(leaderLatchListener.get());
		objectParameterCache.put(Optional.of(nodePath.get() + nodeName.get()), Optional.of(leaderLatch));
	}

}
