package com.stargazerproject.cell.method.sequence;

import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.annotation.NeededInject;
import com.stargazerproject.cell.CellsBlockMethod;
import com.stargazerproject.cell.CellsTransaction;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.negotiate.Negotiate;

@Component(value="acquireParameterBlockModel")
@Qualifier("acquireParameterBlockModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AcquireParameterBlockModel implements CellsTransaction<String, String>, CellsBlockMethod{
	
	/** @name 聚合根ID **/
	@NeededInject(type="OrderCache")
	private static String OrderID;
	
	/** @name 聚合根ID **/
	@NeededInject(type="SystemParametersCache")
	private static String Kernel_Negotiate_BasePath_EdenNodePath;
	
	/** @illustrate 获取Log(日志)接口 **/
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;
	
	@Autowired
	@Qualifier("byteArrayCache")
	protected BigCache<String, byte[]> byteArrayCache;
	
	@Autowired
	@Qualifier("negotiateInjectParameterMonitorListenerCharacteristic")
	private BaseCharacteristic<TreeCacheListener> negotiateInjectParameterMonitorListenerCharacteristic;

	/**
	* @name Springs使用的初始化构造
	* @illustrate 
	*             @Autowired    自动注入
	*             @NeededInject 基于AOP进行最终获取时候的参数注入
	* **/
	@SuppressWarnings("unused")
	private AcquireParameterBlockModel() {
		super();
		}
	
	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public AcquireParameterBlockModel(Optional<LogMethod> logArg, 
			                          Optional<Negotiate> nodeNegotiateArg, 
			                          Optional<BigCache<String, byte[]>> byteArrayCacheArg, 
			                          Optional<BaseCharacteristic<TreeCacheListener>> negotiateInjectParameterMonitorListenerCharacteristicArg, 
			                          Optional<String> OrderIDArg, 
			                          Optional<String> Kernel_Negotiate_BasePath_EdenNodePathArg) {
		super();
		log = logArg.get();
		OrderID = OrderIDArg.get();
		nodeNegotiate = nodeNegotiateArg.get();
		byteArrayCache = byteArrayCacheArg.get();
		Kernel_Negotiate_BasePath_EdenNodePath = Kernel_Negotiate_BasePath_EdenNodePathArg.get();
		negotiateInjectParameterMonitorListenerCharacteristic = negotiateInjectParameterMonitorListenerCharacteristicArg.get();
		}
	
	/**
	* @name 熔断器包裹的方法
	* @illustrate 熔断器包裹的方法
	* @param Optional<Cache<String, String>> cache
	* **/
	@Override
	@HystrixCommand(commandKey = "acquireParameterModel", 
	                fallbackMethod = "fallBack", 
	                groupKey="acquireParameterModel", 
	                threadPoolKey = "acquireParameterModel",
	                commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")})
	public boolean method(Optional<Cache<String, String>> cache){
	try {
		registeredNodeWatch();
		registeredNode();
		blockMethod();
		deleteNodeWatch();
		return Boolean.TRUE;
	} catch (Exception e) {
		log.ERROR(this, e.getMessage());
	}
		return Boolean.FALSE;
	}
	
	@Override
	public void blockMethod() {
		while(true){
			if(byteArrayCache.get(Optional.of("AcquireCellsParameter")).equals(Optional.absent())){
			}
			else{
				log.INFO(this, "acquireParameterModel Complete: " + Optional.of(OrderID).get());
				break;
			}
		}
	}
	
	/**
	* @name 熔断器包裹的方法的备用方法
	* @illustrate 熔断器包裹的方法
	* @param Optional<Cache<String, String>> cache
	* @param Throwable throwable
	* **/
	public boolean fallBack(Optional<Cache<String, String>> cache, Throwable throwable){
		if(null == throwable){
			log.WARN(this, "Event FallBack : TimeOut");
		}
		else{
			log.WARN(this, "Event FallBack : " + throwable.getMessage());
		}
		return Boolean.FALSE;
    }
	
	private void registeredNodeWatch() throws Exception{
		nodeNegotiate.registeredWatcher(Optional.of(OrderID), Optional.of(Kernel_Negotiate_BasePath_EdenNodePath), Optional.of("AcquireParameterModelListener"), negotiateInjectParameterMonitorListenerCharacteristic.characteristic());
		log.INFO(this, "acquireParameterModel creatEphemeralNode Complete: " + Optional.of(OrderID).get());
	}
	
	private void deleteNodeWatch() throws Exception{
		nodeNegotiate.removeWatcher(Optional.of("AcquireParameterModelListener"));
		log.INFO(this, "acquireParameterModel removeWatcher Complete: " + Optional.of(OrderID).get());
	}
	
	private void registeredNode() throws Exception{
		nodeNegotiate.creatEphemeralNode(Optional.of(OrderID), Optional.of(Kernel_Negotiate_BasePath_EdenNodePath), Optional.absent());
		log.INFO(this, "acquireParameterModel creatEphemeralNode Complete: " + Optional.of(OrderID).get() + " , Start Block Method");
	}
	
}
