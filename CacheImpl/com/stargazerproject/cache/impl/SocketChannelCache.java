package com.stargazerproject.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;

import io.netty.channel.socket.SocketChannel;

@Component(value="socketChannelCache")
@Qualifier("socketChannelCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public final class SocketChannelCache implements Cache<String, SocketChannel>{
	
	private Map<String, SocketChannel> cache = new HashMap<String, SocketChannel>();
	
	public SocketChannelCache() {}

	@Override
	public void put(Optional<String> key, Optional<SocketChannel> value) {
		cache.put(key.get(), value.get());
	}

	@Override
	public Optional<SocketChannel> get(Optional<String> key) {
		return Optional.of(cache.get(key.get()));
	}

	@Override
	public void remove(Optional<String> key) {
		cache.remove(key.get());
	}

}
