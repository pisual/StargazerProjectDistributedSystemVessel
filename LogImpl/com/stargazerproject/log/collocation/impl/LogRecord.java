package com.stargazerproject.log.collocation.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.log.Log;
import com.stargazerproject.log.impl.BaseLog;

/** 
 *  @name 开发级别日志记录模式
 *  @illustrate 以开发级别进行日志的处理，全量处理
 *  @author Felixerio
 *  **/
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("logRecord")
public class LogRecord extends BaseLog implements StanderCharacteristicShell<Log>{

	@Override
	public void initialize(Optional<Log> logArg) {
		log = logArg.get();
	}
	
	@Override
	public void DEBUG(Object object, String message) {
		new DEBUGCommand(object, message).execute();
	}

	private class DEBUGCommand extends HystrixCommand<String>{
		
		private Object object;
		private String message;

		protected DEBUGCommand(Object objectArg, String messageArg) {
			super(HystrixCommandGroupKey.Factory.asKey("DEBUGCommand"));
			object = objectArg;
			message = messageArg;
		}

		@Override
		protected String run() throws Exception {
			log.DEBUG(object, message);
			return null;
		}
		
	}
	
}