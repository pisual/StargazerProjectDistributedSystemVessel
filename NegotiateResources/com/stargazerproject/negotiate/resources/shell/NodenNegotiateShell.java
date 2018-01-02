package com.stargazerproject.negotiate.resources.shell;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.negotiate.NegotiateControl;
import com.stargazerproject.negotiate.NegotiateLeaderMethod;
import com.stargazerproject.negotiate.NegotiateNodeMethod;
import com.stargazerproject.negotiate.NegotiateRegisteredWatcher;

@Component(value="nodenNegotiateShell")
@Qualifier("nodenNegotiateShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NodenNegotiateShell implements Negotiate, BaseCharacteristic<Negotiate>{

	private BaseCharacteristic<NegotiateControl> negotiateControlCharacteristic;
	private BaseCharacteristic<NegotiateNodeMethod> negotiateNodeMethodCharacteristic;
	private BaseCharacteristic<NegotiateLeaderMethod> negotiateLeaderMethodCharacteristic;
	private BaseCharacteristic<NegotiateRegisteredWatcher> negotiateRegisteredWatcherCharacteristic;
	
	private NegotiateControl negotiateControl;
	private NegotiateNodeMethod negotiateNodeMethod;
	private NegotiateLeaderMethod negotiateLeaderMethod;
	private NegotiateRegisteredWatcher negotiateRegisteredWatcher;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private NodenNegotiateShell() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public NodenNegotiateShell(Optional<BaseCharacteristic<NegotiateControl>> negotiateControlCharacteristicArg,
			                   Optional<BaseCharacteristic<NegotiateNodeMethod>> negotiateNodeMethodCharacteristicArg,
			                   Optional<BaseCharacteristic<NegotiateLeaderMethod>> negotiateLeaderMethodCharacteristicArg,
			                   Optional<BaseCharacteristic<NegotiateRegisteredWatcher>> negotiateRegisteredWatcherCharacteristicArg) {
		negotiateControlCharacteristic = negotiateControlCharacteristicArg.get();
		negotiateNodeMethodCharacteristic = negotiateNodeMethodCharacteristicArg.get();
		negotiateLeaderMethodCharacteristic = negotiateLeaderMethodCharacteristicArg.get();
		negotiateRegisteredWatcherCharacteristic = negotiateRegisteredWatcherCharacteristicArg.get();
	}
	
	@Override
	public Optional<Negotiate> characteristic() {
		negotiateControl = negotiateControlCharacteristic.characteristic().get();
		negotiateNodeMethod = negotiateNodeMethodCharacteristic.characteristic().get();
		negotiateLeaderMethod = negotiateLeaderMethodCharacteristic.characteristic().get();
		negotiateRegisteredWatcher = negotiateRegisteredWatcherCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public void start() {
		negotiateControl.start();
	}

	@Override
	public void close() {
		negotiateControl.close();
	}

	@Override
	public void creatPersistentNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		negotiateNodeMethod.creatPersistentNode(nodeName, nodePath, nodeData);
	}
	
	@Override
	public void updateNodeData(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		negotiateNodeMethod.updateNodeData(nodeName, nodePath, nodeData);
	}

	@Override
	public void creatEphemeralNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		negotiateNodeMethod.creatEphemeralNode(nodeName, nodePath, nodeData);
	}

	@Override
	public void deleteNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		negotiateNodeMethod.deleteNode(nodeName, nodePath);
	}

	@Override
	public List<String> getPathNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiateNodeMethod.getPathNode(nodeName, nodePath);
	}

	@Override
	public byte[] getNodeData(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiateNodeMethod.getNodeData(nodeName, nodePath);
	}

	@Override
	public boolean checkNodeExists(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiateNodeMethod.checkNodeExists(nodeName, nodePath);
	}
	
	@Override
	public <T> void registeredWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<String> watchName, Optional<T> watch) throws Exception {
		negotiateRegisteredWatcher.registeredWatcher(nodeName, nodePath, watchName, watch);
	}
	
	@Override
	public <T> void removeWatcher(Optional<String> watchName) throws Exception {
		negotiateRegisteredWatcher.removeWatcher(watchName);
	}

	@Override
	public <T> void startSelectLeader(Optional<String> nodeName, Optional<String> nodePath, Optional<T> listener) throws Exception {
		negotiateLeaderMethod.startSelectLeader(nodeName, nodePath, listener);
	}

	@Override
	public void giveUpLeader(Optional<String> nodeName, Optional<String> nodePath) throws IOException {
		negotiateLeaderMethod.giveUpLeader(nodeName, nodePath);
	}

	@Override
	public String getSelectLeader(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiateLeaderMethod.getSelectLeader(nodeName, nodePath);
	}
	
	@Override
	public boolean creatLock(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiateNodeMethod.creatLock(nodeName, nodePath);
	}
	
	@Override
	public void releaseLock(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		negotiateNodeMethod.releaseLock(nodeName, nodePath);
	}

}
