package com.stargazerproject.bus.resources;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.EventResultAnalysisExtend;
import com.stargazerproject.bus.BusBlockMethod;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.queue.Queue;
import com.stargazerproject.transaction.Event;
import com.stargazerproject.transaction.ResultState;

@Component(value="eventBusBlockMethodCharacteristic")
@Qualifier("eventBusBlockMethodCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventBusBlockMethodCharacteristic implements BusBlockMethod<Event>, BaseCharacteristic<BusBlockMethod<Event>>{

	@Autowired
	@Qualifier("logRecord")
	protected LogMethod l️og;
	
	@Autowired
	@Qualifier("eventBusQueue")
	private Queue<Event> event;
	
	@Autowired
	@Qualifier("eventResultAnalysisExtend")
	private EventResultAnalysisExtend eventResultAnalysisExtend;
	
	public EventBusBlockMethodCharacteristic() {}

	@Override
	public Optional<BusBlockMethod<Event>> characteristic() {
		return Optional.of(this);
	}

	/**
	* @name 置入事件
	* @illustrate 阻塞推入事件方法
	* @param Optional<TimeUnit> timeUnit
	* @param Optional<Integer> timeout
	* **/
	@Override
	public Optional<Event> push(Optional<Event> busEvent, Optional<TimeUnit> timeUnit, Optional<Integer> timeout) throws BusEventTimeoutException {
		event.producer(busEvent);
		busEvent.get().eventResult(Optional.of(eventResultAnalysisExtend));
		for(int i=0; i<timeout.get(); i++){
			if(eventResultAnalysisExtend.resultState().get() == ResultState.WAIT){
				sleep(timeUnit.get());
				continue;
			}
			else{
				return busEvent;
			}
		}
		l️og.WARN(busEvent.get(), "Event没有在指定时间内完成任务 : BaseEvent Not completed at the specified time");
		throw new BusEventTimeoutException("Event没有在指定时间内完成任务 : BaseEvent Not completed at the specified time : " + busEvent.toString());
	}
	
	private void sleep(TimeUnit timeUnit){
		try {
			switch (timeUnit) {
			case SECONDS:
				TimeUnit.SECONDS.sleep(1);
				break;
			case MICROSECONDS:
				TimeUnit.MICROSECONDS.sleep(1);
				break;
			case MILLISECONDS:
				TimeUnit.MILLISECONDS.sleep(1);
				break;
			case NANOSECONDS:
				TimeUnit.NANOSECONDS.sleep(1);
				break;
			default:
				l️og.WARN(timeUnit, "Other attributes are not supported, Will Use Default : SECONDS");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			l️og.ERROR(this, e.getMessage());
		}
	}
	
}
