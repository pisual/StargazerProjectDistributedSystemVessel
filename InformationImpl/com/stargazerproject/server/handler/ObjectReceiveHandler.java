package com.stargazerproject.server.handler;

import com.stargazerproject.order.impl.Order;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ObjectReceiveHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
    	if(object instanceof Order){
//			Order order = (Order) object;
//			OrderCache.getInstance().putOrder(order);
//			order.orderSegmentation();
    		}
    }
}
