package com.stargazerproject.information.resources;

import java.util.List;

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
import io.netty.handler.codec.ByteToMessageDecoder;

@Component(value="cellsInformationByteToMessageDecoderHandlerCharacteristic")
@Qualifier("cellsInformationByteToMessageDecoderHandlerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CellsInformationByteToMessageDecoderHandlerCharacteristic extends ByteToMessageDecoder implements BaseCharacteristic<ByteToMessageDecoder>{
	
	@Autowired
	@Qualifier("networkTransmissionSerializables")
	private Serializables serializables;
	
	public CellsInformationByteToMessageDecoderHandlerCharacteristic() {}
	
	@Override
	public Optional<ByteToMessageDecoder> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf receiveMessageByteBuf, List<Object> out) throws Exception {
		if (receiveMessageByteBuf.readableBytes() < 4) {
			return;
		}
		int dataLength = receiveMessageByteBuf.markReaderIndex().readInt();
		if (receiveMessageByteBuf.readableBytes() < dataLength) {
			receiveMessageByteBuf.resetReaderIndex();
			return;
		}
		
		byte[] body = new byte[dataLength];
		receiveMessageByteBuf.readBytes(body);
		out.add(serializables.deserialize(Optional.of(body)).get());
	}
	
}
