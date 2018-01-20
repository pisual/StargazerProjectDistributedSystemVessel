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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.negotiate.NegotiateNodeMethod;

@Component(value="negotiateNodeMethodCharacteristic")
@Qualifier("negotiateNodeMethodCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateNodeMethodCharacteristic implements NegotiateNodeMethod, BaseCharacteristic<NegotiateNodeMethod>{

	/** @name 创建锁的超时时间 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_Mode_Lock_CreatLockOutTime;
	
	@Autowired
	@Qualifier("negotiateCuratorFrameworkCharacteristic")
	private BaseCharacteristic<CuratorFramework> negotiateCuratorFrameworkCharacteristic;
	
	@Autowired
	@Qualifier("interProcessSemaphoreMutexCache")
	private Cache<String, InterProcessSemaphoreMutex> interProcessSemaphoreMutexCache;
	
	private CuratorFramework curatorFramework;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private NegotiateNodeMethodCharacteristic() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public NegotiateNodeMethodCharacteristic(Optional<BaseCharacteristic<CuratorFramework>> negotiateCuratorFrameworkCharacteristicArg,
											Optional<Cache<String, InterProcessSemaphoreMutex>> interProcessSemaphoreMutexCacheArg,
											Optional<String> Kernel_Negotiate_Mode_Lock_CreatLockOutTimeArg) {
		Kernel_Negotiate_Mode_Lock_CreatLockOutTime = Kernel_Negotiate_Mode_Lock_CreatLockOutTimeArg.get();
		negotiateCuratorFrameworkCharacteristic = negotiateCuratorFrameworkCharacteristicArg.get();
		interProcessSemaphoreMutexCache = interProcessSemaphoreMutexCacheArg.get();
	}
	
	@Override
	public Optional<NegotiateNodeMethod> characteristic() {
		curatorFramework = negotiateCuratorFrameworkCharacteristic.characteristic().get();
		return Optional.of(this);
	}
	
	@Override
	public void creatPersistentNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(Ids.OPEN_ACL_UNSAFE).forPath( nodePath.get() + nodeName.get(), nodeData.orNull());
	}
	
	@Override
	public void updateNodeData(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		curatorFramework.setData().forPath(nodePath.get() + nodeName.get(), nodeData.get());
	}

	@Override
	public void creatEphemeralNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		curatorFramework.create().withMode(CreateMode.EPHEMERAL).withACL(Ids.OPEN_ACL_UNSAFE).forPath( nodePath.get() + nodeName.get(), nodeData.orNull());
	}

	@Override
	public void deleteNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		curatorFramework.delete().deletingChildrenIfNeeded().forPath(nodePath.get() + nodeName.get());
	}

	@Override
	public List<String> getPathNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return curatorFramework.getChildren().forPath( nodePath.get() + nodeName.get());
	}

	@Override
	public byte[] getNodeData(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return curatorFramework.getData().forPath(nodePath.get() + nodeName.get());
	}

	@Override
	public boolean checkNodeExists(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return (null == curatorFramework.checkExists().forPath( nodePath.get() + nodeName.get()))?Boolean.FALSE:Boolean.TRUE;
	}
	
	@Override
	public boolean creatLock(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		InterProcessSemaphoreMutex interProcessSemaphoreMutex = new InterProcessSemaphoreMutex(curatorFramework, nodePath.get() + nodeName.get());
		if(interProcessSemaphoreMutex.acquire(Integer.parseInt(Kernel_Negotiate_Mode_Lock_CreatLockOutTime), TimeUnit.SECONDS) == Boolean.TRUE){
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
