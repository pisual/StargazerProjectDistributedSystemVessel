package com.stargazerproject.queue.server.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.impl.LogQueue;
import com.stargazerproject.service.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Qualifier("logQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogQueueServer extends LogQueue implements StanderServiceShell{

	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
     	ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener");
		Optional<Queue<LogData>> queueArg = BeanContainer.instance().getBean(Optional.of("logDisruptorQueueCharacteristic"), Optional.class);
		initialize(queueArg);
	}

	@Override
	public void shutDown() {
		
	}

}
