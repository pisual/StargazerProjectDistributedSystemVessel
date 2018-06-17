package com.stargazerproject.transaction.impl.resources.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.analysis.EventExecuteAnalysis;
import com.stargazerproject.analysis.EventResultAnalysis;
import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.EventState;
import com.stargazerproject.transaction.Result;
import com.stargazerproject.transaction.base.impl.ID;
import com.stargazerproject.util.CloneUtil;

/** 
 *  @name 事件（BaseEvent）实现
 *  @illustrate 事件（BaseEvent）是事务的原子单位，一个事件包含了一个事务，并包含了这个事务所需的全部参数
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
@Component(value="baseEventShell")
@Qualifier("baseEventShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NoSpringDepend
public class BaseEventShell extends ID implements Event, BaseCharacteristic<Event>{

	private static final long serialVersionUID = 8581224354660031049L;

	/**
	* @name 事件结果接口
	* @illustrate 事件结果接口
	* **/
	private Result result;
	
	@Autowired
	@Qualifier("baseEventResultShell")
	private BaseCharacteristic<Result> baseEventResultShell;
	
	/**
	* @name 交互缓存接口
	* @illustrate 交互缓存接口
	* **/
	@Autowired
	@Qualifier("eventInteractionCache")
	public Cache<String, String> interactionCache;
	
	/**
	* @name 日志接口
	* @illustrate 交互缓存接口
	* **/
	@Autowired
	@Qualifier("logRecord")
	public LogMethod logMethod;
	
	/** @illustrate 事件状态, 初始状态为初始态**/
	private EventState eventState = EventState.INIT;
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public BaseEventShell(Optional<Result> resultArg, Optional<Cache<String, String>> interactionCacheArg, Optional<LogMethod> logMethodArg){
		result = resultArg.get();
		logMethod = logMethodArg.get();
		interactionCache = interactionCacheArg.get();
	}

	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private BaseEventShell() {}
	
	@Override
	public Optional<Event> characteristic() {
		result = baseEventResultShell.characteristic().get();
		return Optional.of(this);
	}
	
	/** @illustrate 事件生产，生产者调用
	 *  @param      Optional<EventAssembleAnalysis> eventAssembleAnalysis : 事件生产分析器接口
	 * **/
	@Override
	public void eventAssemble(Optional<EventAssembleAnalysis> eventAssembleAnalysis){
		if(eventState != EventState.INIT){
			logMethod.ERROR(this, "Evenr无法构建，因为Event状态不为Init（初始状态），现在Event的状态为：" + eventState);
			throw new IllegalStateException("Evenr无法构建，因为Event状态不为Init（初始状态），现在Event的状态为：" + eventState);
		}
		else{
			eventAssembleAnalysis.get().analysis(Optional.of(interactionCache));
			eventState = EventState.WAIT;
		}
	}

	/** @illustrate 开始执行事件, 执行者调用 执行者只有在Event处于EventState.WAIT的状态下才会启动事件运行分析接口，<P>
	 *              如果Event处于EventState.PASS，将快速失败此事务
	 * 	@param      Optional<EventExecuteAnalysis> eventAnalysis : 事件运行器接口
	 * **/
	@Override
	public void eventExecute(Optional<EventExecuteAnalysis> eventAnalysis) {
		if(EventState.WAIT == eventState){
			eventState = EventState.RUN;
			eventAnalysis.get().analysis(Optional.of(interactionCache), Optional.of(result));
			eventState = EventState.COMPLETE;
		}
		else if(EventState.PASS == eventState){
			logMethod.INFO(this, "事件处于PASS状态，将快速失败此事务");
			result.errorMessage(Optional.of("事件处于PASS状态，将快速失败此事务"), null);
		}
		else{
			logMethod.ERROR(this, "Evenr无法启动，因为Event状态不为Wait（等待执行状态），现在Event的状态为：" + eventState);
			throw new IllegalStateException("Evenr无法启动，因为Event状态不为Wait（等待执行状态），现在Event的状态为：" + eventState);
		}
	}
	
	/** @illustrate 分析事件结果，分析者调用
	 *              分析者不受EventState状态的约束
	 *  @param      Optional<EventResultAnalysis> eventResultAnalysis : 事件结果分析器接口
	 * **/
	@Override
	public void eventResult(Optional<EventResultAnalysis> eventResultAnalysis){
		result.resultResult(eventResultAnalysis.get());
	}
	
	/** @illustrate  跳过此事件
	 *  @exception 如果Event状态不为Wait（等待执行状态），将抛出IllegalStateException异常，并报告现在的Event状态  
	 * **/
	@Override
	public void skipEvent(){
		if(eventState != EventState.WAIT){
			logMethod.ERROR(this, "Evenr无法跳过，因为Event状态不为Wait（等待执行状态），现在Event的状态为：" + eventState);
			throw new IllegalStateException("Evenr无法跳过，因为Event状态不为Wait（等待执行状态），现在Event的状态为：" + eventState);
		}
		else{
			eventState = EventState.PASS;
		}
	}
	
	/** @illustrate 获取事件状态 ,返回一个经过深度拷贝的EventState对象
	 *  @return     Optional<EventState> : 结果状态 EventState
	 * **/
	@Override
	public Optional<EventState> eventState(){
		EventState copyEventState = (EventState)CloneUtil.deepClone(Optional.of(eventState));
		return Optional.of(copyEventState);
	}
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("EventState", eventState).toString();
	}

}
