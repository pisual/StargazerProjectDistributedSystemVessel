package com.stargazerproject.serializable.shell;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.serializable.Serializables;

@Component(value = "networkTransmissionSerializablesShell")
@Qualifier("networkTransmissionSerializablesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NetworkTransmissionSerializablesShell implements Serializables, BaseCharacteristic<Serializables> {

	@Override
	public Optional<Serializables> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<byte[]> serialize(Optional<Object> object) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(object.get());
		objectOutputStream.flush();
		byte[] bytes = byteArrayOutputStream.toByteArray();
		byteArrayOutputStream.close();
		objectOutputStream.close();
		return Optional.of(bytes);
	}

	@Override
	public Optional<Object> deserialize(Optional<byte[]> byteArray) throws ClassNotFoundException, IOException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray.get());
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		Object object = objectInputStream.readObject();
		byteArrayInputStream.close();
		objectInputStream.close();
		return Optional.of(object);
	}

}
