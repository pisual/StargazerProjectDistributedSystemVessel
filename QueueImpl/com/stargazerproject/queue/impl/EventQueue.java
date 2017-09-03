package com.stargazerproject.queue.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.queue.base.impl.StandQueue;

/** 
 *  @name Event队列
 *  @illustrate Log队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
@Component(value="eventQueue")
@Qualifier("eventQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventQueue extends StandQueue<Event> implements StanderCharacteristicShell<Queue<Event>>{

	/** @construction 初始化构造 **/
	protected EventQueue() {}

	@Override
	public void initialize(Optional<Queue<Event>> queueArg) {
		queue = queueArg.get();
	}

}