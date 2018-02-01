package com.stargazerproject.information.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.information.InformationControl;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

@Component(value="cellsInformationControlCharacteristic")
@Qualifier("cellsInformationControlCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CellsInformationControlCharacteristic implements InformationControl, BaseCharacteristic<InformationControl> {

    /** @illustrate 连接Cells主机的套接字 IP**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Information_Cells_Connection_Host;
	
    /** @illustrate 连接Cells主机的套接字 Port**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Information_Cells_Connection_Port;
	
    /** @illustrate  处理IO的线程数**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Information_Cells_Thread_IOEventNumber;
	
    /** @illustrate  SocketChannel命名**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Information_Cells_SocketChannel;
	
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	@Autowired
	@Qualifier("socketChannelCache")
	private Cache<String, SocketChannel> socketChannelCache;
	
	@Autowired
	@Qualifier("cellsInformationHandlerGuide")
	private BaseCharacteristic<ChannelInitializer<SocketChannel>> cellsInformationHandlerGuide;
	
    /** @illustrate  Bootstrap**/
	private static Bootstrap bootstrap;
	
    /** @illustrate  SocketChannel**/
	private static SocketChannel socketChannel;
	
    /** @illustrate  ChannelFuture**/
	private static ChannelFuture channelFuture;
	
    /** @illustrate  EventLoopGroup**/
	private static EventLoopGroup eventLoopGroup;

	public CellsInformationControlCharacteristic() {}
	
	@Override
	public Optional<InformationControl> characteristic() {
		bootstrap = new Bootstrap();
		eventLoopGroup = new NioEventLoopGroup(Integer.parseInt(Kernel_Information_Cells_Thread_IOEventNumber));
		serverPropertyInitialize();
		serverHandlerInitialize();
		return Optional.of(this);
	}

	@Override
	public void start() {
		startServer startServer = new startServer();
		startServer.start();
	}

	@Override
	public void stop() {
		channelFuture.awaitUninterruptibly();
		eventLoopGroup.shutdownGracefully();
	}
	
	private void serverPropertyInitialize(){
		bootstrap.group(eventLoopGroup);
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.remoteAddress(Kernel_Information_Cells_Connection_Host, Integer.parseInt(Kernel_Information_Cells_Connection_Port));
	}
	
	private void serverHandlerInitialize(){
		bootstrap.handler(cellsInformationHandlerGuide.characteristic().get());
	}
	
	private class startServer extends Thread{
		@Override
		public void run() {
			try {
				channelFuture =bootstrap.connect().sync();
				if (channelFuture.isSuccess()) {
					log.INFO(this, "Cells InformationServer Start Success");
		            socketChannel = (SocketChannel)channelFuture.channel();
		            socketChannelCache.put(Optional.of(Kernel_Information_Cells_SocketChannel), Optional.of(socketChannel));
					channelFuture.channel().closeFuture().sync();
				}
				else{
					log.ERROR(this, "Cells InformationServer Start Faild");
				}
			} catch (InterruptedException e) {
				log.ERROR(this, e.getMessage());
			}
			finally {
				eventLoopGroup.shutdownGracefully();
	        }
		}
	}

}
