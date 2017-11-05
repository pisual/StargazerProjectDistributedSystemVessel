package com.stargazerproject.negotiate.resources;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
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
import com.stargazerproject.negotiate.NegotiateNodeMethod;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="negotiateNodeMethod")
@Qualifier("negotiateNodeMethod")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateNodeMethodCharacteristic implements NegotiateNodeMethod, BaseCharacteristic<NegotiateNodeMethod>{

	private Optional<CuratorFramework> curatorFramework;
	
	@Autowired
	@Qualifier("interProcessSemaphoreMutexCache")
	private Cache<String, InterProcessSemaphoreMutex> interProcessSemaphoreMutexCache;
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="negotiateNodeMethodCharacteristic")
	@Lazy(true)
	public Optional<NegotiateNodeMethod> characteristic() {
		curatorFramework = BeanContainer.instance().getBean(Optional.of("negotiateCuratorFrameworkCharacteristic"), Optional.class);
		return Optional.of(this);
	}
	
	@Override
	public void creatPersistentNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		curatorFramework.get().create().withMode(CreateMode.PERSISTENT).withACL(Ids.OPEN_ACL_UNSAFE).forPath( nodePath.get() + nodeName.get(), nodeData.orNull());
	}
	
	@Override
	public void updateNodeData(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		curatorFramework.get().setData().forPath( nodePath.get() + nodeName.get(), nodeData.get());
	}

	@Override
	public void creatEphemeralNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		curatorFramework.get().create().withMode(CreateMode.EPHEMERAL).withACL(Ids.OPEN_ACL_UNSAFE).forPath( nodePath.get() + nodeName.get(), nodeData.orNull());
	}

	@Override
	public void deleteNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		curatorFramework.get().delete().forPath( nodePath.get() + nodeName.get());
	}

	@Override
	public List<String> getPathNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return curatorFramework.get().getChildren().forPath( nodePath.get() + nodeName.get());
	}

	@Override
	public byte[] getNodeData(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return curatorFramework.get().getData().forPath(nodePath.get() + nodeName.get());
	}

	@Override
	public boolean checkNodeExists(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return (null == curatorFramework.get().checkExists().forPath( nodePath.get() + nodeName.get()))?Boolean.FALSE:Boolean.TRUE;
	}
	
	@Override
	public boolean creatLock(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		InterProcessSemaphoreMutex interProcessSemaphoreMutex = new InterProcessSemaphoreMutex(curatorFramework.get(), nodePath.get() + nodeName.get());
		if(interProcessSemaphoreMutex.acquire(1, TimeUnit.SECONDS) == Boolean.TRUE){
			interProcessSemaphoreMutexCache.put(Optional.of(nodePath.get() + nodeName.get()), Optional.of(interProcessSemaphoreMutex));
			return Boolean.TRUE;
		}
		else{
			return false;
		}
	}
	
	@Override
	public void releaseLock(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		InterProcessSemaphoreMutex interProcessSemaphoreMutex = interProcessSemaphoreMutexCache.get(Optional.of(nodePath.get() + nodeName.get())).get();
		interProcessSemaphoreMutex.release();
	}
}
