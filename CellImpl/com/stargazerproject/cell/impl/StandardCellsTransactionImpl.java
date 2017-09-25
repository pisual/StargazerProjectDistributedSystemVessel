package com.stargazerproject.cell.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cell.base.impl.BaseCellsTransaction;

@Component(value="standardCellsTransactionImpl")
@Qualifier("standardCellsTransactionImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StandardCellsTransactionImpl extends BaseCellsTransaction<String, String>{

	@Override
	@HystrixCommand(fallbackMethod = "fallBack", groupKey="TestMethod", commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
	public boolean method(Optional<Cache<String, String>> cache) {
		System.out.println("局外加载成功");
		return Boolean.TRUE;
	}
	
	public boolean fallBack(Optional<Cache<String, String>> cache, Throwable throwable){
		System.out.println("事务包裹fallBack");
		return Boolean.TRUE;
    }

}
