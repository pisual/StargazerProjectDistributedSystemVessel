package com.stargazerproject.zookeeper.model.factory;

import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.zookeeper.client.ConnectionServer;
import com.stargazerproject.zookeeper.model.InfiniteWatcher;
import com.stargazerproject.zookeeper.model.ZookeeperPropertiesNodeData;

public final class ClientInitializeZookeeepeInfiniteConfigurationFactory {
	
	private static InfiniteWatcher infiniteWatcher;
	private static ClientInitializeZookeeepeInfiniteConfigurationFactory zookeeeperConfigurationFactory;
	private static PathChildrenCacheListener pathChildrenCacheListener;
	private static final Schema<ZookeeperPropertiesNodeData> zookeeperPropertiesNodeData_SCHEMA = RuntimeSchema.getSchema(ZookeeperPropertiesNodeData.class);

	public static InfiniteWatcher getInfiniteWatcherInstance(String nodeName) {
		if (null == infiniteWatcher) {
			if (null == zookeeeperConfigurationFactory) {
				zookeeeperConfigurationFactory = new ClientInitializeZookeeepeInfiniteConfigurationFactory();
			}
			zookeeeperConfigurationFactory.zookeeeperConfigurationInitialize();
			infiniteWatcher = new InfiniteWatcher(pathChildrenCacheListener, nodeName);
		}
		return infiniteWatcher;
	}

	private ClientInitializeZookeeepeInfiniteConfigurationFactory() {
	}

	private void zookeeeperConfigurationInitialize() {
		pathChildrenCacheListener = new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				switch (event.getType()) {
				case CHILD_ADDED:
					ChildData data = event.getData();
					System.out.println("激活事件 CHILD_ADDED : ");
					ZookeeperPropertiesNodeData zookeeperPropertiesNodeData = ZookeeperPropertiesNodeDataDeserialize(data.getData());
					if(data.getPath().indexOf(SystemParameterCahce.getInstance().getString("CellsID")+"_properties")!=-1){
						System.out.println("获取到服务器分配的配置文件: " + zookeeperPropertiesNodeData.toString());
						System.out.println("开始注入配置文件");
						SystemParameterCahce.getInstance().initializationParametersFromHashMap(zookeeperPropertiesNodeData.getPropertiesMap());
						System.out.println("注入配置文件完毕");
						SystemParameterCahce.getInstance().set("CluStar_Cache_Initialize", "true");

						ConnectionServer.getInstance().deleteNode(data.getPath());
						System.out.println("计算节点任务完成 删除任务 "+data.getPath());

					}
					else{
						System.out.println(data.getPath());
						System.out.println();
						System.out.println("其他服务器上线 " + data.getPath());
					}
					break; 
				case CHILD_REMOVED:
					System.out.println("激活事件 CHILD_REMOVED : ");
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED : ");
					break;
				case CONNECTION_SUSPENDED:
					System.out.println("CHILD_CONNECTION_SUSPENDED : ");
					break;
				case CONNECTION_RECONNECTED:
					System.out.println("CHILD_CONNECTION_RECONNECTED : ");
					break;
				case CONNECTION_LOST:
					System.out.println("CHILD_CONNECTION_LOST : ");
					break;
				case INITIALIZED:
					System.out.println("CHILD_INITIALIZED : ");
					break;
				default:
					break;
				}
			}
		};
	}
	
	private ZookeeperPropertiesNodeData ZookeeperPropertiesNodeDataDeserialize(byte[] bytes) {
		ZookeeperPropertiesNodeData zookeeperPropertiesNodeData = zookeeperPropertiesNodeData_SCHEMA.newMessage();
		ProtobufIOUtil.mergeFrom(bytes, zookeeperPropertiesNodeData, zookeeperPropertiesNodeData_SCHEMA);
		return zookeeperPropertiesNodeData;
	}
	
}
