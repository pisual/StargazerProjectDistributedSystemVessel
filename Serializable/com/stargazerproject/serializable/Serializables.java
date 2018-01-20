package com.stargazerproject.serializable;

import java.io.IOException;

import com.google.common.base.Optional;

public interface Serializables {

	public Optional<byte[]> serialize(Optional<Object> object) throws IOException;
	
	public Optional<Object> deserialize(Optional<byte[]> byteArray);
}
