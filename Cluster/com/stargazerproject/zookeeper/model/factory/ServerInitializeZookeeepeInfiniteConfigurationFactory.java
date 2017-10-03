package com.stargazerproject.zookeeper.model.factory;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import com.stargazerproject.zookeeper.model.InfiniteWatcher;
import com.stargazerproject.zookeeper.model.ZookeeperNodeData;

import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public final class ServerInitializeZookeeepeInfiniteConfigurationFactory {
	
	private static InfiniteWatcher infiniteWatcher;
	private static ServerInitializeZookeeepeInfiniteConfigurationFactory zookeeeperConfigurationFactory;
	private static PathChildrenCacheListener pathChildrenCacheListener;
	private static final Schema<ZookeeperNodeData> ZookeeperNodeData_SCHEMA = RuntimeSchema.getSchema(ZookeeperNodeData.class);

	public static InfiniteWatcher getInfiniteWatcherInstance(String nodeName) {
		if (null == infiniteWatcher) {
			if (null == zookeeeperConfigurationFactory) {
				zookeeeperConfigurationFactory = new ServerInitializeZookeeepeInfiniteConfigurationFactory();
			}
			zookeeeperConfigurationFactory.zookeeeperConfigurationInitialize();
			infiniteWatcher = new InfiniteWatcher(pathChildrenCacheListener, nodeName);
		}
		return infiniteWatcher;
	}

	private ServerInitializeZookeeepeInfiniteConfigurationFactory() {
	}

	private void zookeeeperConfigurationInitialize() {
		pathChildrenCacheListener = new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				ChildData data = event.getData();
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("激活事件 CHILD_ADDED : ");
					ZookeeperNodeData zookeeperNodeData = zookeeperNodeDataDeserialize(data.getData());
					System.out.println("获取的数据: " + zookeeperNodeData.toString());
					System.out.println("开始注入参数数据");
//					try {
//						HashMap<String, String> valueField = ParamentUtil.getParamentByReflectFromPackageInfo("com.stargazerproject.parameter.util.StargazerProjectParameter");
//						ZookeeperPropertiesNodeData zookeeperPropertiesNodeData = new ZookeeperPropertiesNodeData(valueField);
//						System.out.println(zookeeperPropertiesNodeData.toString());
//						ServerZookeeperControl.getInstance().creatEphemeralNodes("StargazerSystem/cells/tasks/" + zookeeperNodeData.getCellsID() + "_properties", zookeeperPropertiesNodeData);
//					} catch (IllegalArgumentException e1) {
//						e1.printStackTrace();
//					} catch (IllegalAccessException e1) {
//						e1.printStackTrace();
//					}
					break;
				case CHILD_REMOVED:
					System.out.println("激活事件 CHILD_REMOVED : ");
					System.out.println("节点数据: #"+ String.valueOf(data) +"#");
					System.out.println("计算节点已经上线 "+data.getPath());
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
	
	private ZookeeperNodeData zookeeperNodeDataDeserialize(byte[] bytes) {
		ZookeeperNodeData zookeeperNodeData = ZookeeperNodeData_SCHEMA.newMessage();
		ProtobufIOUtil.mergeFrom(bytes, zookeeperNodeData, ZookeeperNodeData_SCHEMA);
		return zookeeperNodeData;
	}
	
}
