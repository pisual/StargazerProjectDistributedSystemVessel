package com.stargazerproject.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.List;

import com.stargazerproject.model.server.MessageType;
import com.stargazerproject.model.server.NetworkConnectionMessage;
import com.stargazerproject.order.impl.Order;

public class SufDecode extends ByteToMessageDecoder {
	
	private static final Schema<Order> ORDER_SCHEMA = RuntimeSchema.getSchema(Order.class);
	private static final Schema<NetworkConnectionMessage> NetworkConnectionMessage_SCHEMA = RuntimeSchema.getSchema(NetworkConnectionMessage.class);
	
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
		int mesasgeType = receiveMessageByteBuf.markReaderIndex().readInt();
		byte[] body = new byte[dataLength - 4];
		receiveMessageByteBuf.readBytes(body);
		determineType(mesasgeType, out, body);
	}
	
	private void determineType(int mesasgeType, List<Object> out, byte[] body){
		switch (mesasgeType) {
		case MessageType.OrderType:
			out.add(orderDeserialize(body));
			break;
			
		case MessageType.NetworkConnectionMessageType:
			out.add(networkConnectionMessageDeserialize(body));
			break;

		default:
			break;
		}
		
	}

	private Order orderDeserialize(byte[] bytes) {
		Order order = ORDER_SCHEMA.newMessage();
		ProtobufIOUtil.mergeFrom(bytes, order, ORDER_SCHEMA);
		return order;
	}
	
	private NetworkConnectionMessage networkConnectionMessageDeserialize(byte[] bytes) {
		NetworkConnectionMessage networkConnectionMessage = NetworkConnectionMessage_SCHEMA.newMessage();
		ProtobufIOUtil.mergeFrom(bytes, networkConnectionMessage, NetworkConnectionMessage_SCHEMA);
		return networkConnectionMessage;
	}
}
