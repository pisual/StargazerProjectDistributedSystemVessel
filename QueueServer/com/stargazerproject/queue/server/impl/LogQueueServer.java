package com.stargazerproject.queue.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.QueueControl;
import com.stargazerproject.service.baseinterface.StanderServiceShell;
import com.stargazerproject.service.util.ServiceUtil;

@Component(value="logQueueServer")
@Qualifier("logQueueServer")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogQueueServer implements StanderServiceShell{

	@Autowired
	@Qualifier("logQueueShell")
	private BaseCharacteristic<Queue<LogData>> logQueueShell;
	
	@Autowired
	@Qualifier("logQueue")
	private StanderCharacteristicShell<Queue<LogData>> logQueue;
	
	@Autowired
	@Qualifier("logQueue")
	private QueueControl<LogData> logQueueControl;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private LogQueueServer() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public LogQueueServer(Optional<BaseCharacteristic<Queue<LogData>>> logQueueShellArg, Optional<StanderCharacteristicShell<Queue<LogData>>> logQueueArg, Optional<QueueControl<LogData>> logQueueControlArg) {
		logQueueControl = logQueueControlArg.get();
		logQueueShell = logQueueShellArg.get();
		logQueue = logQueueArg.get();
	}
	
	@Override
	public void startUp() {
     	ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener");
		logQueue.initialize(logQueueShell.characteristic());
		logQueueControl.start();
	}

	@Override
	public void shutDown() {
		logQueueControl.shutdown();
	}

}
