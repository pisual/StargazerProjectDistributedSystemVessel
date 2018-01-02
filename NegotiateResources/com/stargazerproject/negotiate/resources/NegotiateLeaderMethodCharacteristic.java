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
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private NegotiateLeaderMethodCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public NegotiateLeaderMethodCharacteristic(Optional<Cache<String, LeaderLatch>> objectParameterCacheArg,
			                                   Optional<BaseCharacteristic<CuratorFramework>> negotiateCuratorFrameworkCharacteristicArg) {
		objectParameterCache = objectParameterCacheArg.get();
		negotiateCuratorFrameworkCharacteristic = negotiateCuratorFrameworkCharacteristicArg.get();
	}

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
