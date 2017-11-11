package com.stargazerproject.sequence.model;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.spring.container.impl.BeanContainer;

/** 
 *  @name Cell生成ID序列组
 *  @illustrate Cells生成序列的第一步，生成UUID组，UUID组的格式为  XXX:XXX,使用：来进行组分割
 *  @author Felixerio
 *  **/
@Component(value="registerSequenceBeanModel")
@Qualifier("registerSequenceBeanModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RegisterSequenceBeanModel implements CellsTransaction<String, String>{

	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameter;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;
	
	public RegisterSequenceBeanModel() { super(); }
	
	@Override
	@HystrixCommand(fallbackMethod = "fallBack", groupKey="TestMethod", commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
	public boolean method(Optional<Cache<String, String>> cache) {
		
		//**模拟远程注入 Start**//
		Object initializationCellsGroupModel = new InitializationCellsGroupModel();
		//**模拟远程注入 End**//å
		
		BeanContainer.instance().setBean(Optional.of(initializationCellsGroupModel.getClass()));
		return Boolean.TRUE;
	}
	
	public boolean fallBack(Optional<Cache<String, String>> cache, Throwable throwable){
		System.out.println("RegisterSequenceBeanModel 事务包裹fallBack");
		return Boolean.TRUE;
    }
	
}
