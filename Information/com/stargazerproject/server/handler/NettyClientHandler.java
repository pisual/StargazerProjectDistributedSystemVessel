package com.stargazerproject.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import com.stargazerproject.model.server.NetworkConnectionMessage;
import com.stargazerproject.model.server.NetworkConnectionMessageID;
import com.stargazerproject.model.server.NetworkConnectionMessageType;


public class NettyClientHandler extends SimpleChannelInboundHandler<Object> {
	
	private String id;
	
	@Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
            	NetworkConnectionMessage networkConnectionMessage = new NetworkConnectionMessage(NetworkConnectionMessageType.PING, new NetworkConnectionMessageID(id));
            	ctx.writeAndFlush(networkConnectionMessage);
            	System.out.println("READER_IDLE 读超时");
            } else if (event.state() == IdleState.WRITER_IDLE) {
            	System.out.println("WRITER_IDLE 写超时");
            } else if (event.state() == IdleState.ALL_IDLE) {
            	 ctx.disconnect();
            	System.out.println("ALL_IDLE 总超时");
            }
        }
    }
	
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object message) throws Exception {
		if(message instanceof NetworkConnectionMessage){
			NetworkConnectionMessage networkConnectionMessage = (NetworkConnectionMessage)message;
			switch (networkConnectionMessage.getNetworkConnectionMessageType()) {
			case REPING:
				System.out.println("收到服务器Ping回应");
				break;
			case REGISTERED:
				id = networkConnectionMessage.IDSequence();
				System.out.println("获取服务器分配ID: " + id);
				break;
			default:
				break;
			}
			ReferenceCountUtil.release(networkConnectionMessage);
	}
    }
}
