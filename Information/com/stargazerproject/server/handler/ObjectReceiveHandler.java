package com.stargazerproject.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.stargazerproject.cache.impl.impl.OrderCache;
import com.stargazerproject.order.impl.Order;

public class ObjectReceiveHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
    	if(object instanceof Order){
			Order order = (Order) object;
			OrderCache.getInstance().putOrder(order);
			order.orderSegmentation();
    		}
    }
}
