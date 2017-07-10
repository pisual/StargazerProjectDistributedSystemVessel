package com.stargazerproject.log.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;

/** 
 *  @name Log日志
 *  @illustrate 日志的纪录模型
 *  @author Felixerio
 *  **/
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogData {
	
	/** @construction 日志的名称 **/
	private String name;
	
	/** @construction 日志的内容 **/
	private String content;
	
	/** @construction 日志的级别**/
	private LogLevel logLevel;
	
	public LogData() {}
	
	public LogData(String nameArg, String contentArg, LogLevel logLevelArg) {
		name = nameArg;
		content = contentArg;
		logLevel = logLevelArg;
	}
	
	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add(" # name", name)
                          .add(" # content", content)
                          .add(" # logLevel", logLevel.name()).toString();
	}
}