package com.stargazerproject.queue.model;

import com.google.common.base.MoreObjects;
import com.stargazerproject.order.impl.Event;

/** 
 *  @name lmax disruptor 专用的EventModel
 *  @illustrate Log队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public class EventQueueEvent {
	
	private Event event;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
    public void clear(){
    	    event = null;
    }
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("event", event).toString();
	}

}