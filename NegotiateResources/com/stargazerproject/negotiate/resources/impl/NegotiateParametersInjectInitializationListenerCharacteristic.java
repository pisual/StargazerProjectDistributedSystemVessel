package com.stargazerproject.negotiate.resources;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.log.LogMethod;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.resources.parameter.InjectParameters;
import com.stargazerproject.util.SerializableUtil;

/** 
 *  @name 参数服务监听器（模拟模块）
 *  @illustrate 监听指定目录下节点的注册，一旦注册，根据相应规则注入参数
 *  @author Felixerio
 *  **/
@Component(value="negotiateParametersInjectMonitoringNodePathChildrenCacheListener")
@Qualifier("negotiateParametersInjectMonitoringNodePathChildrenCacheListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateParametersInjectMonitoringNodePathChildrenCacheListenerCharacteristic implements BaseCharacteristic<PathChildrenCacheListener>{
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;
	
	private PathChildrenCacheListener pathChildrenCacheListener;
	
	public NegotiateParametersInjectMonitoringNodePathChildrenCacheListenerCharacteristic() {}
	
	@Autowired
	@Qualifier("logRecord")
	protected LogMethod log;
	
	@Override
	@Bean(name="negotiateParametersInjectMonitoringNodePathChildrenCacheListenerCharacteristic")
	@Lazy(true)
	public Optional<PathChildrenCacheListener> characteristic() {
		zookeeeperConfigurationInitialize();
		return Optional.of(pathChildrenCacheListener);
	}
	
	private void zookeeeperConfigurationInitialize() {
		pathChildrenCacheListener = new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				switch (event.getType()) {
				case CHILD_ADDED:
					log.INFO(this, "CHILD_ADDED : " + event.getData().getData());
					TimeUnit.SECONDS.sleep(1);
					byte[] byteArray = SerializableUtil.serialize(new InjectParameters());
					nodeNegotiate.updateNodeData(Optional.of(event.getData().getPath()), Optional.of(""),Optional.of(byteArray));
					log.INFO(this, "初始化节点参数 " + event.getData().getPath());
					break;
				case CHILD_REMOVED:
					nodeNegotiate.deleteNode(Optional.of(event.getData().getPath().substring(event.getData().getPath().lastIndexOf("/"))),Optional.of("/Master_Cells/List"));
					log.INFO(this, event.getData().getPath()+" Has Remove, Deletes the primary node operation");
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED : ");
					break;
				case CONNECTION_SUSPENDED:
					System.out.println("CONNECTION_SUSPENDED: ");
					break;
				case CONNECTION_RECONNECTED:
					System.out.println("CONNECTION_RECONNECTED : ");
					break;
				case CONNECTION_LOST:
					System.out.println("CONNECTION_LOST : ");
					break;
				case INITIALIZED:
					System.out.println("INITIALIZED : ");
					break;
				default:
					break;
				}
			}
		};
	}

}
