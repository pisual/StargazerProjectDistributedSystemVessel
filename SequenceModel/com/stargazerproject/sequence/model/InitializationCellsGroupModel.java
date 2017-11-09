package com.stargazerproject.sequence.model;

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
import com.stargazerproject.util.Sequence;

/** 
 *  @name Cell生成ID序列组
 *  @illustrate Cells生成序列的第一步，生成UUID组，UUID组的格式为  XXX:XXX,使用：来进行组分割
 *  @author Felixerio
 *  **/
@Component(value="initializationCellsGroupModel")
@Qualifier("initializationCellsGroupModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InitializationCellsGroupModel implements CellsTransaction<String, String>{

	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameter;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;
	
	public InitializationCellsGroupModel() { super(); }
	
	@Override
	@HystrixCommand(fallbackMethod = "fallBack", groupKey="TestMethod", commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
	public boolean method(Optional<Cache<String, String>> cache) {
		String UUID = Sequence.getUUID();
		if(systemParameter.get(Optional.of("Cells_Group")).equals(Optional.absent())){
			systemParameter.put(Optional.of("Cells_Group"), Optional.of(UUID + ":"));
		}
		else{
			String originalParameter = systemParameter.get(Optional.of("Cells_UUID")).get();
			systemParameter.put(Optional.of("Cells_Group"), Optional.of(originalParameter + ":" + UUID));
		}
		cache.get().put(Optional.of("This_Cells_UUID"), Optional.of(UUID));
		log.INFO(this, "Cells_Group Initialization: " + systemParameter.get(Optional.of("Cells_Group")).get());
		return Boolean.TRUE;
	}
	
	public boolean fallBack(Optional<Cache<String, String>> cache, Throwable throwable){
		System.out.println("事务包裹fallBack");
		return Boolean.TRUE;
    }
	
}
