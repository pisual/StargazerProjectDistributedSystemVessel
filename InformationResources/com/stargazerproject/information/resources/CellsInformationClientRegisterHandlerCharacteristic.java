package com.stargazerproject.information.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.information.model.Response;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Component(value="cellsInformationClientRegisterHandlerCharacteristic")
@Qualifier("cellsInformationClientRegisterHandlerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Sharable
public class CellsInformationClientRegisterHandlerCharacteristic extends SimpleChannelInboundHandler<Object> implements BaseCharacteristic<SimpleChannelInboundHandler<Object>> {
	
	@Autowired
	@Qualifier("logRecord")
	private LogMethod log;
	
	public CellsInformationClientRegisterHandlerCharacteristic() {}
	
	@Override
	public Optional<SimpleChannelInboundHandler<Object>> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
		if(object instanceof Response){
			Response response = (Response) object;
			switch (response.responseType().get()) {
			case REGISTERED_SUCCESS:
				log.INFO(object, "CellsInformation : Upon receipt of the registration Success request : 收到注册成功回应 ");
				channelHandlerContext.fireChannelReadComplete();
				break;
			default:
				channelHandlerContext.fireChannelRead(object);
				break;
				}
			}
		
		else{
			channelHandlerContext.fireChannelRead(object);
		}
	}
	
}
