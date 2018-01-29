package com.stargazerproject.information.resources;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

@Component(value="cellsInformationsOutScourHandler")
@Qualifier("cellsInformationsOutScourHandler")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Sharable
public class CellsInformationsOutScourHandler extends ChannelHandlerAdapter implements BaseCharacteristic<ChannelHandlerAdapter> {

	public CellsInformationsOutScourHandler() {}
	
	@Override
	public Optional<ChannelHandlerAdapter> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
		ctx.flush();
	}
	
}
