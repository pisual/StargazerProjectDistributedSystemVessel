package com.stargazerproject.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayOutputStream;

import com.stargazerproject.model.order.impl.Order;
import com.stargazerproject.model.server.MessageType;
import com.stargazerproject.model.server.NetworkConnectionMessage;

public class sufEncoder extends MessageToByteEncoder<Object>{
	 private static final Schema<Order> ORDER_SCHEMA = RuntimeSchema.getSchema(Order.class);
	 private static final Schema<NetworkConnectionMessage> NetworkConnectionMessage_SCHEMA = RuntimeSchema.getSchema(NetworkConnectionMessage.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, Object object, ByteBuf out) throws Exception {
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		LinkedBuffer BUFFER = LinkedBuffer.allocate();
		byte[] body = null;
		
		if(object instanceof Order){
			ProtobufIOUtil.writeTo(temp, (Order)object, ORDER_SCHEMA, BUFFER);
			body = temp.toByteArray();
			int dataLength = body.length;
			out.writeInt(dataLength + 4);
			out.writeInt(MessageType.OrderType);
		}
		
		else if(object instanceof NetworkConnectionMessage){
			ProtobufIOUtil.writeTo(temp, (NetworkConnectionMessage)object, NetworkConnectionMessage_SCHEMA, BUFFER);
			body = temp.toByteArray();
			int dataLength = body.length;
			out.writeInt(dataLength + 4);
			out.writeInt(MessageType.NetworkConnectionMessageType);
		}
		
		out.writeBytes(body);
	}
}
