package com.stargazerproject.negotiate.resources;

import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.negotiate.NegotiateLeaderMethod;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="negotiateLeaderMethod")
@Qualifier("negotiateLeaderMethod")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateLeaderMethodCharacteristic implements NegotiateLeaderMethod, BaseCharacteristic<NegotiateLeaderMethod>{

	@Autowired
	@Qualifier("leaderLatchParameterCache")
	private Cache<String, LeaderLatch> objectParameterCache;
	
	private Optional<CuratorFramework> curatorFramework;
	private LeaderLatch leaderLatch;
	
	private void leaderLatchInitialization(Optional<String> nodeName, Optional<String> nodePath, Optional<LeaderLatchListener> leaderLatchListener){
		leaderLatch = new LeaderLatch(curatorFramework.get(), nodePath.get() + nodeName.get());
		leaderLatch.addListener(leaderLatchListener.get());
		objectParameterCache.put(Optional.of(nodePath.get() + nodeName.get()), Optional.of(leaderLatch));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="negotiateLeaderMethodCharacteristic")
	@Lazy(true)
	public Optional<NegotiateLeaderMethod> characteristic() {
		curatorFramework = BeanContainer.instance().getBean(Optional.of("negotiateCuratorFrameworkCharacteristic"), Optional.class);
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

}
