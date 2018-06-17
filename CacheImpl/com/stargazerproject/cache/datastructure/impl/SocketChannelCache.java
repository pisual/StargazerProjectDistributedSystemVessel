package com.stargazerproject.cache.datastructure.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.annotation.description.NoSpringDepend;
import com.stargazerproject.cache.datastructure.BaseDataStructureCache;

import io.netty.channel.socket.SocketChannel;

@Component(value="socketChannelCache")
@Qualifier("socketChannelCache")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@NoSpringDepend
public final class SocketChannelCache extends BaseDataStructureCache<String, SocketChannel>{

	private static final long serialVersionUID = -2525546831675623606L;

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public SocketChannelCache() {}
	
}
