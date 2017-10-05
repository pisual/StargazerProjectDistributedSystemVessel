package com.stargazerproject.negotiate.resources;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.negotiate.NegotiateNodeMethod;
import com.stargazerproject.spring.container.impl.BeanContainer;

public class NegotiateNodeMethodCharacteristic implements NegotiateNodeMethod,BaseCharacteristic<NegotiateNodeMethod>{

	private Optional<CuratorFramework> curatorFramework;
	
	@Override
	public void creatPersistentNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		curatorFramework.get().create().withMode(CreateMode.PERSISTENT).withACL(Ids.OPEN_ACL_UNSAFE).forPath("/"+ nodePath + nodeName.get(), nodeData.get());
	}

	@Override
	public void creatEphemeralNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		curatorFramework.get().create().withMode(CreateMode.EPHEMERAL).withACL(Ids.OPEN_ACL_UNSAFE).forPath("/"+ nodePath + nodeName.get(), nodeData.get());
	}

	@Override
	public void deleteNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		curatorFramework.get().delete().forPath("/" + nodePath.get() + nodeName.get());
	}

	@Override
	public List<String> getPathNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return curatorFramework.get().getChildren().forPath("/" + nodePath.get() + nodeName.get());
	}

	@Override
	public byte[] getNodeData(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return curatorFramework.get().getData().forPath("/" + nodePath.get() + nodeName.get());
	}

	@Override
	public boolean checkNodeExists(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return (null == curatorFramework.get().checkExists().forPath("/" + nodePath.get() + nodeName.get()))?Boolean.FALSE:Boolean.TRUE;
	}

	@Override
	public Optional<NegotiateNodeMethod> characteristic() {
		curatorFramework = BeanContainer.instance().getBean(Optional.of("negotiateCuratorFrameworkCharacteristic"), Optional.class);
		return Optional.of(this);
	}

}