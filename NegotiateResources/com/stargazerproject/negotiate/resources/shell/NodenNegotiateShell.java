package com.stargazerproject.negotiate.resources.shell;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.negotiate.NegotiateControl;
import com.stargazerproject.negotiate.NegotiateLeaderMethod;
import com.stargazerproject.negotiate.NegotiateNodeMethod;
import com.stargazerproject.negotiate.NegotiateRegisteredWatcher;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="nodenNegotiateShellCharacteristic")
@Qualifier("nodenNegotiateShellCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NodenNegotiateShell implements Negotiate, BaseCharacteristic<Negotiate>{

	private Optional<NegotiateControl> negotiateControl;
	private Optional<NegotiateLeaderMethod> negotiateLeaderMethod;
	private Optional<NegotiateNodeMethod> negotiateNodeMethod;
	private Optional<NegotiateRegisteredWatcher> negotiateRegisteredWatcher;
	
	@Override
	@Bean(name="nodenNegotiateShell")
	@Lazy(true)
	public Optional<Negotiate> characteristic() {
		negotiateControl = BeanContainer.instance().getBean(Optional.of("negotiateControlCharacteristic"), Optional.class);
		negotiateLeaderMethod = BeanContainer.instance().getBean(Optional.of("negotiateLeaderMethodCharacteristic"), Optional.class);
		negotiateNodeMethod = BeanContainer.instance().getBean(Optional.of("negotiateNodeMethodCharacteristic"), Optional.class);
		negotiateRegisteredWatcher = BeanContainer.instance().getBean(Optional.of("negotiateRegisteredWatcherCharacteristic"), Optional.class);
		return Optional.of(this);
	}

	@Override
	public void start() {
		negotiateControl.get().start();
	}

	@Override
	public void close() {
		negotiateControl.get().close();
	}

	@Override
	public void creatPersistentNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		negotiateNodeMethod.get().creatPersistentNode(nodeName, nodePath, nodeData);
	}
	
	@Override
	public void updateNodeData(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		negotiateNodeMethod.get().updateNodeData(nodeName, nodePath, nodeData);
	}

	@Override
	public void creatEphemeralNode(Optional<String> nodeName, Optional<String> nodePath, Optional<byte[]> nodeData) throws Exception {
		negotiateNodeMethod.get().creatEphemeralNode(nodeName, nodePath, nodeData);
	}

	@Override
	public void deleteNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		negotiateNodeMethod.get().deleteNode(nodeName, nodePath);
	}

	@Override
	public List<String> getPathNode(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiateNodeMethod.get().getPathNode(nodeName, nodePath);
	}

	@Override
	public byte[] getNodeData(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiateNodeMethod.get().getNodeData(nodeName, nodePath);
	}

	@Override
	public boolean checkNodeExists(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiateNodeMethod.get().checkNodeExists(nodeName, nodePath);
	}

	@Override
	public <T> void registeredCirculationWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<T> watch) throws Exception {
		negotiateRegisteredWatcher.get().registeredCirculationWatcher(nodeName, nodePath, watch);
	}

	@Override
	public <T> void registeredSingleWatcher(Optional<String> nodeName, Optional<String> nodePath, Optional<T> watch) throws Exception{
		negotiateRegisteredWatcher.get().registeredSingleWatcher(nodeName, nodePath, watch);
	}

	@Override
	public <T> void startSelectLeader(Optional<String> nodeName, Optional<String> nodePath, Optional<T> listener) throws Exception {
		negotiateLeaderMethod.get().startSelectLeader(nodeName, nodePath, listener);
	}

	@Override
	public void giveUpLeader(Optional<String> nodeName, Optional<String> nodePath) throws IOException {
		negotiateLeaderMethod.get().giveUpLeader(nodeName, nodePath);
	}

	@Override
	public String getSelectLeader(Optional<String> nodeName, Optional<String> nodePath) throws Exception {
		return negotiateLeaderMethod.get().getSelectLeader(nodeName, nodePath);
	}

}
