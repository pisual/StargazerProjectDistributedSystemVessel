package com.stargazerproject.information.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.information.model.Transmission;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.queue.Queue;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Component(value="cellsInformationEventHandlerCharacteristic")
@Qualifier("cellsInformationEventHandlerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Sharable
public class CellsInformationEventHandlerCharacteristic extends SimpleChannelInboundHandler<Object> implements BaseCharacteristic<SimpleChannelInboundHandler<Object>> {
	
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	@Autowired
	@Qualifier("transmissionQueue")
	public Queue<Transmission> queue;
	
	public CellsInformationEventHandlerCharacteristic() {}
	
	@Override
	public Optional<SimpleChannelInboundHandler<Object>> characteristic() {
		return Optional.of(this);
	}

	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
		if(object instanceof Transmission){
			Transmission transmission = (Transmission) object;
			queue.producer(Optional.of((transmission.clone())));
			log.INFO(this, "Transmission 已经入队 : " + transmission.sequenceID());
		}
		else{
			channelHandlerContext.fireChannelRead(object);
		}

	}
	
	
}
