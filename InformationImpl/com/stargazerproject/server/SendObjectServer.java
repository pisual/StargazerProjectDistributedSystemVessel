package com.stargazerproject.server;

import com.stargazerproject.model.server.ListenSocketConfiguration;
import com.stargazerproject.server.handler.NettyClientHandler;
import com.stargazerproject.server.handler.SufDecode;
import com.stargazerproject.server.handler.sufEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public final class SendObjectServer extends Thread{
	
	private static ListenSocketConfiguration listenSocketConfiguration;
	private static SendObjectServer sendObjectServer;
	private static EventLoopGroup boss;
	private static Bootstrap bootstrap;
	private static SocketChannel socketChannel;
	private static ChannelFuture channelFuture;

	public static SendObjectServer getInstance(ListenSocketConfiguration listenSocketConfiguration) {
		if (sendObjectServer == null) {
			sendObjectServer = new SendObjectServer();
			boss = new NioEventLoopGroup();
			bootstrap = new Bootstrap();
			SendObjectServer.listenSocketConfiguration = listenSocketConfiguration;
			serverPropertyInitialize();
			serverHandlerInitialize();
			sendObjectServer.start();
		}
		return sendObjectServer;
	}

	private SendObjectServer() {}
	
	@Override
	public void run(){
		startServer();
	}
	
	private static void serverPropertyInitialize(){
		bootstrap.group(boss);
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.remoteAddress(listenSocketConfiguration.getHost(),listenSocketConfiguration.getPort());
	}
	
	private static void serverHandlerInitialize(){
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				ChannelPipeline p = socketChannel.pipeline();
				p.addLast(new IdleStateHandler(30, 20, 0));
				p.addLast(new sufEncoder());
				p.addLast(new SufDecode());
				p.addLast(new NettyClientHandler());
			}
		});
	}

	private static void startServer(){
		try {
			channelFuture =bootstrap.connect(listenSocketConfiguration.getHost(),listenSocketConfiguration.getPort()).sync();
			if (channelFuture.isSuccess()) {
	//			LocalityLog.getInstance().INFO(SendObjectServer.class, "Receive Object Server Start Success");
	            socketChannel = (SocketChannel)channelFuture.channel();
				channelFuture.channel().closeFuture().sync();
			}
			else{
//				LocalityLog.getInstance().ERROR(SendObjectServer.class, "Receive Object Server Start Faild");
			}
		} catch (InterruptedException e) {
//			LocalityLog.getInstance().ERROR(SendObjectServer.class, e.getMessage());
		}
		finally {
			boss.shutdownGracefully();
        }
	}
	
	public void sendMessage(Object object){
		socketChannel.writeAndFlush(object);
	}
	
	public void stopServer(){
		channelFuture.awaitUninterruptibly();
		boss.shutdownGracefully();
	}
	
}
