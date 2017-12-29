package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.negotiate.NegotiateControl;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="negotiateControl")
@Qualifier("negotiateControl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateControlCharacteristic implements NegotiateControl, BaseCharacteristic<NegotiateControl>{

	private Optional<CuratorFramework> curatorFramework;
	
	@Override
	@Bean(name="negotiateControlCharacteristic")
	@Lazy(true)
	public Optional<NegotiateControl> characteristic() {
		curatorFramework = BeanContainer.instance().getBean(Optional.of("negotiateCuratorFrameworkCharacteristic"), Optional.class);
		return Optional.of(this);
	}

	@Override
	public void start() {
		curatorFramework.get().start();
	}

	@Override
	public void close() {
		curatorFramework.get().close();
	}

}
