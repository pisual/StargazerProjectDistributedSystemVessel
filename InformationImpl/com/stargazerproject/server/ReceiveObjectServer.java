package com.stargazerproject.server;

import com.stargazerproject.model.server.ListenSocketConfiguration;
import com.stargazerproject.server.handler.ObjectReceiveHandler;
import com.stargazerproject.server.handler.ServerConnectionHandler;
import com.stargazerproject.server.handler.SufDecode;
import com.stargazerproject.server.handler.sufEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public final class ReceiveObjectServer extends Thread{

	private static EventLoopGroup boss;
	private static EventLoopGroup worker;
	private static ServerBootstrap bootstrap;
	private static ChannelFuture channelFuture;
	private static ReceiveObjectServer receiveObjectServer;
	private static ListenSocketConfiguration listenSocketConfiguration;

	public static ReceiveObjectServer getInstance(ListenSocketConfiguration listenSocketConfiguration) {
		if (receiveObjectServer == null) {
			ReceiveObjectServer.listenSocketConfiguration = listenSocketConfiguration;
			receiveObjectServer = new ReceiveObjectServer();
			boss = new NioEventLoopGroup(1);
			worker = new NioEventLoopGroup(4);
			bootstrap = new ServerBootstrap();
			serverPropertyInitialize();
			serverHandlerInitialize();
			receiveObjectServer.start();
		}
		return receiveObjectServer;
	}

	private ReceiveObjectServer() {}
	
	@Override
	public void run(){
		startServer();
	}
	
	private static void serverPropertyInitialize(){
		bootstrap.group(boss, worker);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.option(ChannelOption.SO_BACKLOG, 128);
		bootstrap.option(ChannelOption.TCP_NODELAY, false);
		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
	}
	
	private static void serverHandlerInitialize(){
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel socketChannel) throws Exception {
				ChannelPipeline p = socketChannel.pipeline();
				p.addLast(new IdleStateHandler(30, 20, 0));
				p.addLast(new sufEncoder());
				p.addLast(new SufDecode());
				p.addLast(new ServerConnectionHandler());
				p.addLast(new ObjectReceiveHandler());
			}
		});
	}

	private static void startServer(){
		try {
			channelFuture = bootstrap.bind(listenSocketConfiguration.getPort()).sync();
			if (channelFuture.isSuccess()) {
	//			LocalityLog.getInstance().INFO(ReceiveObjectServer.class, "Receive Object Server Start Success");
				channelFuture.channel().closeFuture().sync();
			}
			else{
	//			LocalityLog.getInstance().ERROR(ReceiveObjectServer.class, "Receive Object Server Start Faild");
			}
		} catch (InterruptedException e) {
	//		LocalityLog.getInstance().ERROR(ReceiveObjectServer.class, e.getMessage());
		}
		finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
        }
	}
	
	public void stopServer(){
		channelFuture.awaitUninterruptibly();
		boss.shutdownGracefully();
		worker.shutdownGracefully();
	}
}
