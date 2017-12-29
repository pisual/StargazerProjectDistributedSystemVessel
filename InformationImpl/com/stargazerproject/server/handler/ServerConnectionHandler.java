package com.stargazerproject.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import com.stargazerproject.model.server.NettyChannelMap;
import com.stargazerproject.model.server.NetworkConnectionMessage;
import com.stargazerproject.model.server.NetworkConnectionMessageType;

public class ServerConnectionHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NettyChannelMap.remove((SocketChannel) ctx.channel());
		System.out.println("卸载与客户端的连接");
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NetworkConnectionMessage networkConnectionMessage = new NetworkConnectionMessage(NetworkConnectionMessageType.REGISTERED);
		NettyChannelMap.add(networkConnectionMessage.IDSequence(),(SocketChannel) ctx.channel());
		ctx.writeAndFlush(networkConnectionMessage);
		System.out.println("注册Success");
		super.channelActive(ctx);
	}
	
	@Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("READER_IDLE 读超时");
                ctx.disconnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("WRITER_IDLE 写超时");
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("ALL_IDLE 总超时");
            }
        }
    }

	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object message) throws Exception {
		if(message instanceof NetworkConnectionMessage){
			NetworkConnectionMessage networkConnectionMessage = (NetworkConnectionMessage)message;
			switch (networkConnectionMessage.getNetworkConnectionMessageType()) {
			case PING:
				networkConnectionMessage.setNetworkConnectionMessageType(NetworkConnectionMessageType.REPING);
				NettyChannelMap.get(networkConnectionMessage.IDSequence()).writeAndFlush(networkConnectionMessage);
				System.out.println("收到服务器的检测 已经回应");
				break;
			default:
				break;
				}
			}
		
		else{
			channelHandlerContext.fireChannelRead(message);
		}
	}
}
