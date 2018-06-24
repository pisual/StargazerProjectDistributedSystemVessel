package com.stargazerproject.information.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.timeout.IdleStateHandler;

@Component(value="cellsInformationHandlerGuide")
@Qualifier("cellsInformationHandlerGuide")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CellsInformationHandlerGuide extends ChannelInitializer<SocketChannel> implements BaseCharacteristic<ChannelInitializer<SocketChannel>> {
	
    /** @illustrate 读超时时间,A一定时间内未接受到B端消息 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Information_Cells_Heartbeat_ReaderIdleTime;
	
	/** @illustrate 写超时时间，A端一定时间未内向被B端发送消息 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Information_Cells_Heartbeat_WriterIdleTime;
	
    /** @illustrate 所有类型的超时时间 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_Information_Cells_Heartbeat_AllIdleTime;
	
	@Autowired
	@Qualifier("cellsInformationByteToMessageDecoderHandlerCharacteristic")
    /** @illustrate  解码 **/
	private BaseCharacteristic<ByteToMessageDecoder> cellsInformationByteToMessageDecoderHandlerCharacteristic;

	@Autowired
	@Qualifier("cellsInformationMessageToByteEncoderHandlerCharacteristic")
    /** @illustrate  编码 **/
	private BaseCharacteristic<MessageToByteEncoder<Object>> cellsInformationMessageToByteEncoderHandlerCharacteristic;
	
	@Autowired
	@Qualifier("cellsInformationClientRegisterHandlerCharacteristic")
    /** @illustrate  客户端注册回复的响应 **/
	private BaseCharacteristic<SimpleChannelInboundHandler<Object>> cellsInformationClientRegisterHandlerCharacteristic;
	
	@Autowired
	@Qualifier("cellsInformationEventHandlerCharacteristic")
    /** @illustrate  客户端传输事件 **/
	private BaseCharacteristic<SimpleChannelInboundHandler<Object>> cellsInformationEventHandlerCharacteristic;
	
	@Autowired
	@Qualifier("cellsInformationHeartbeatHandlerCharacteristic")
    /** @illustrate  心跳组件 **/
	private BaseCharacteristic<SimpleChannelInboundHandler<Object>> cellsInformationHeartbeatHandlerCharacteristic;
	
	@Autowired
	@Qualifier("cellsInformationsOutScourHandler")
    /** @illustrate  结果冲刷组件 **/
	private BaseCharacteristic<ChannelHandlerAdapter> cellsInformationsOutScourHandler;
	
	public CellsInformationHandlerGuide() {}
	
	@Override
	public Optional<ChannelInitializer<SocketChannel>> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		ChannelPipeline channelPipeline = socketChannel.pipeline();
		
	    /** @illustrate  入站组件线路 **/
		
		channelPipeline.addLast(new IdleStateHandler(Integer.parseInt(Kernel_Information_Cells_Heartbeat_ReaderIdleTime), 
				                          			Integer.parseInt(Kernel_Information_Cells_Heartbeat_WriterIdleTime), 
				                          			Integer.parseInt(Kernel_Information_Cells_Heartbeat_AllIdleTime)));
		
		channelPipeline.addLast(cellsInformationByteToMessageDecoderHandlerCharacteristic.characteristic().get());
		channelPipeline.addLast(cellsInformationHeartbeatHandlerCharacteristic.characteristic().get());
		channelPipeline.addLast(cellsInformationClientRegisterHandlerCharacteristic.characteristic().get());
		channelPipeline.addLast(cellsInformationEventHandlerCharacteristic.characteristic().get());
		
	    /** @illustrate  出站组件线路 **/
		channelPipeline.addLast(cellsInformationMessageToByteEncoderHandlerCharacteristic.characteristic().get());
		channelPipeline.addLast(cellsInformationsOutScourHandler.characteristic().get());
	}
	
}
