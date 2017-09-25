package com.stargazerproject.queue.model;

import com.google.common.base.MoreObjects;
import com.stargazerproject.order.impl.Order;

/** 
 *  @name lmax disruptor 专用的OrderModel
 *  @illustrate OrderExport队列的实现
 *  @param <K> 队列的Entry值类型
 *  @author Felixerio
 *  **/
public class OrderExportEvent {
	
	private Order order;
	
    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void clear(){
		order = null;
    }
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("Order", order).toString();
	}

}