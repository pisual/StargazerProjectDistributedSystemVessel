package com.stargazerproject.queue.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component
@Qualifier("logQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogQueueServer implements StanderServiceShell{

	@Autowired
	@Qualifier("logQueue")
	private StanderCharacteristicShell<Queue<LogData>> logQueue;
	
	@Autowired
	@Qualifier("logQueue")
	private QueueControl<LogData> logQueueControl;
	
	@Override
	@SuppressWarnings("unchecked")
	public void startUp() {
     	ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener");
		Optional<Queue<LogData>> queueArg = BeanContainer.instance().getBean(Optional.of("logQueueCharacteristicInitialize"), Optional.class);
		logQueue.initialize(queueArg);
		logQueueControl.start();
	}

	@Override
	public void shutDown() {
		logQueueControl.shutdown();
	}

}
