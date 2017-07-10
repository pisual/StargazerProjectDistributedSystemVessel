package com.stargazerproject.cache.server.listener.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.service.WorkInServiceControl;
import com.stargazerproject.service.WorkInServiceState;
import com.stargazerproject.service.impl.StandardWorkInServiceState;
import com.stargazerproject.service.util.ServiceUtil;

@Component
@Qualifier("byteArrayCacheServerListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ByteArrayCacheServerListener extends StandardWorkInServiceState implements WorkInServiceState, WorkInServiceControl{
	
	@Override
	public void starting() {
		ServiceUtil.dependOnDelay("systemParameterCacheServerListener","localLogServerListener");
		baseLog.INFO(this, "byteArrayCache Server Starting");
	}
	
	@Override
	public void running() {
		super.running();
		baseLog.INFO(this, "byteArrayCache Server Run");
	}
	
}
