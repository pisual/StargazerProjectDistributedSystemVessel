package com.stargazerproject.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.LogAnalysis;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.log.model.LogLevel;
import com.stargazerproject.queue.QueueConsumer;

@Component(value="logConsumer")
@Qualifier("logConsumer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LogConsumer implements QueueConsumer<LogData>{
	
	@Qualifier("logAnalysisImpl")
	@Autowired
	private LogAnalysis logAnalysis;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public LogConsumer() {}
	
	@Override
	public void consumer(Optional<LogData> logData) {
		if(logData.get().logLevel().get().equals(LogLevel.ERROR) || (logData.get().logLevel().get().equals(LogLevel.FATAL))){
			System.err.println("Stargazer System Report : " + logData.get().toString());
		}
		else{
			System.out.println("Stargazer System Report : " + logData.get().toString());
		}
	}
	
}
