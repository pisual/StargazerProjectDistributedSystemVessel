package com.stargazerproject.serializable.base.impl;

import java.io.IOException;

import com.google.common.base.Optional;
import com.stargazerproject.serializable.Serializables;

public class BaseSerializablesImpl implements Serializables{

	protected Serializables serializables;
	
	@Override
	public Optional<byte[]> serialize(Optional<Object> object) throws IOException {
		return serializables.serialize(object);
	}

	@Override
	public Optional<Object> deserialize(Optional<byte[]> byteArray) throws ClassNotFoundException, IOException{
		return serializables.deserialize(byteArray);
	}

}
