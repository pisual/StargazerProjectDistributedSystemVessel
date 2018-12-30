package com.stargazerproject.service.aop.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name 针对被NeededInject标注的注解进行参数注入
 *  @illustrate 当BaseCharacteristic的characteristic方法被调用时，对NeededInject标注的注解进行参数注入
 *  @author Felixerio
 *  **/
@Component(value="serverDependDetectionAOPConfiguration")
@EnableAspectJAutoProxy
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Aspect
@Qualifier("serverDependDetectionAOPConfiguration")
public class ServerDependDetectionAOPConfiguration {
	
//	@Autowired
//	@Qualifier("kernelService")
//	private Service server;
//	
//	@Autowired
//	@Qualifier("serverCache")
//	private Cache<String,Boolean> cache;
//	
//	@Autowired
//	@Qualifier("serverListCache")
//	private Cache<String, List<String>> serverListCache;
//	
//	
//	private Map<Optional<String>, Optional<Integer>> serverLayerList = new HashMap<Optional<String>, Optional<Integer>>();
//	
//	@Autowired
//	@Qualifier("serviceParameterList")
//	private Multimap<Optional<Integer>, Optional<String>> systemServiceterList;
//	
//	private Multimap<Optional<Integer>, Optional<String>> aggregationServiceterList;//塌陷后连续的服务列表
//	
//	
//	@Autowired
//	@Qualifier("serverDependCharacteristic")
//	private BaseCharacteristic<ServiceDepend> serverDependCharacteristic;
//	
//	
//	/** @construction 初始化构造 **/
//	private ServerDependDetectionAOPConfiguration() {}
//
//	public void initializationServerSequenceListMap(){
//		List<String> serverList = serverListCache.get(Optional.of("serverList")).get();
//		for(int i=1; i<=serverList.size(); i++){
//			String servername = serverList.get(i-1).replace("Manage", "");
//			servername = firstChartoLowerCase(servername);
//			serverSequenceMap.put(i, servername);
//		}
//		serverSequenceMapInverse = serverSequenceMap.inverse();
//	}
//	
//	//列表塌陷聚合
//	public void aggregation(){
//		Iterator<Optional<Integer>> serverKeyIterator = systemServiceterList.keySet().iterator();
//		int aggregationServiceNum = 1;
//		while(serverKeyIterator.hasNext()){
//			Collection<Optional<String>> serverValue = systemServiceterList.get(serverKeyIterator.next());
//			aggregationServiceterList.putAll(Optional.of(aggregationServiceNum), serverValue);
//			List<Optional<String>> serverGroupList = serverValue.stream().collect(Collectors.toList());
//			for(Optional<String> serverGroup : serverGroupList){
//				serverLayerList.put(serverGroup, Optional.of(aggregationServiceNum));
//				cache.put(serverGroup, Optional.of(Boolean.FALSE));
//			}
//			aggregationServiceNum++;
//		}
//	}
//	
//	
//	/** @illustrate StanderServiceShell 中的Set方法的AOP切点**/
//	@Pointcut ("execution(* com.stargazerproject.service.baseinterface.StanderServiceShell.startUp())")
//	public void startUpMethod(){}
//	
//	/** @illustrate StanderServiceShell 中的Set的AOP切点的具体方法**/
//	@Around("startUpMethod()")
//	public void setMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//		String servername = proceedingJoinPoint.getTarget().getClass().getSimpleName();
//		servername = firstChartoLowerCase(servername);
//		while(dependOnDelay(Optional.of(servername)).get() == Boolean.FALSE){}
//		
//		proceedingJoinPoint.proceed();
//		cache.put(Optional.of(servername), Optional.of(Boolean.TRUE));
//		}
//	
//	private Optional<Boolean> dependOnDelay(Optional<String> serverName){
//		Integer index = serverLayerList.get(serverName).get();
//		if(index == 1){
//			return Optional.of(Boolean.TRUE);
//		}
//		else{
//			int beforeIndex = index -1;
//			return checkGroupServer(beforeIndex);
//		}
//	}
//	
//	public Optional<Boolean> checkGroupServer(int sercverIndex){
//		Collection<Optional<String>> beforeServer = aggregationServiceterList.get(Optional.of(sercverIndex));
//		Iterator<Optional<String>> serverStateIterator = beforeServer.iterator();
//		Boolean groupServerState = Boolean.TRUE;
//		while(serverStateIterator.hasNext()){
//			groupServerState = cache.get(serverStateIterator.next()).get()||groupServerState;
//		}
//		return Optional.of(groupServerState);
//	}
//	
//	private String firstChartoLowerCase(String servername){
//		return servername.substring(0, 1).toLowerCase() + servername.substring(1, servername.length());
//	}

}
	