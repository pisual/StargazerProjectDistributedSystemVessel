package com.stargazerproject.information.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.serializable.Serializables;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

@Component(value="cellsInformationMessageToByteEncoderHandlerCharacteristic")
@Qualifier("cellsInformationMessageToByteEncoderHandlerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CellsInformationMessageToByteEncoderHandlerCharacteristic extends MessageToByteEncoder<Object> implements BaseCharacteristic<MessageToByteEncoder<Object>>{
	
	@Autowired
	@Qualifier("networkTransmissionSerializables")
	private Serializables serializables;
	
	public CellsInformationMessageToByteEncoderHandlerCharacteristic() {}
	
	@Override
	public Optional<MessageToByteEncoder<Object>> characteristic() {
		return Optional.of(this);
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Object object, ByteBuf out) throws Exception {
		byte[] bytes = serializables.serialize(Optional.of(object)).get();
		out.writeInt(bytes.length);
		out.writeBytes(bytes);
	}
}
