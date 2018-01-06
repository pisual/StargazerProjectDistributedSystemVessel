package com.stargazerproject.log.resources.shall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.Log;
import com.stargazerproject.log.model.LogData;
import com.stargazerproject.log.model.LogLevel;
import com.stargazerproject.queue.Queue;

@Component(value="onlineLogShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("onlineLogShell")
public class OnlineLogShell implements Log, BaseCharacteristic<Log>{
	
	@Autowired
	@Qualifier("logQueue")
	public Queue<LogData> queue;
	
	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private OnlineLogShell() {}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public OnlineLogShell(Optional<Queue<LogData>> queueArg) {
		queue = queueArg.get();
	}
	
	@Override
	public Optional<Log> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public void DEBUG(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.DEBUG))));
	}

	@Override
	public void INFO(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.INFO))));
	}

	@Override
	public void WARN(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.WARN))));
	}

	@Override
	public void ERROR(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.ERROR))));
	}

	@Override
	public void FATAL(Object object, String message) {
		queue.producer(Optional.of(new LogData(Optional.of(object.toString()), Optional.of(message), Optional.of(LogLevel.FATAL))));
	}

}
