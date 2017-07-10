package com.stargazerproject.zookeeper.model.factory;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;

import com.stargazerproject.zookeeper.model.SingleWatcher;

public final class ZookeeepeSingleConfigurationFactory {
	private static SingleWatcher singleWatcher;
	private static ZookeeepeSingleConfigurationFactory zookeeeperConfigurationFactory;
	private static CuratorListener curatorListener;
	
	public static SingleWatcher getSingleWatcherInstance(String nodeName){
		if(null == singleWatcher){
			if(null == zookeeeperConfigurationFactory){
				zookeeeperConfigurationFactory = new ZookeeepeSingleConfigurationFactory();
			}
			zookeeeperConfigurationFactory.SingleWatcherInitialize();
	        singleWatcher = new SingleWatcher(curatorListener, nodeName);
		}
		return singleWatcher;
	}
	
	private ZookeeepeSingleConfigurationFactory() {
	}
	
	private void SingleWatcherInitialize(){
		curatorListener = new CuratorListener() {  
            @Override  
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {  
				switch (event.getType()) {
				case CREATE:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case DELETE:
					System.out.println("已经触发了" + event.getType() + "事件！");
					break;
				case EXISTS:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case GET_DATA:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case SET_DATA:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case CHILDREN:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case SYNC:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case GET_ACL:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case SET_ACL:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				case WATCHED:
					System.out.println("已经触发了" + event.getType() + "事件！");
					break;
				case CLOSING:
					System.out.println("已经触发了" + event.getType() + "事件！"); 
					break;
				default:
					break;
				}
            }  
        };
	}
}
