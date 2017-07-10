package com.stargazerproject.zookeeper.listener;

import org.apache.curator.framework.state.ConnectionStateListener;

public class StrategyCellsConnectionStateListener implements ConnectionStateListener{
	
	private int retryNumber;
	
	public StrategyCellsConnectionStateListener(int retryNumber) {
		this.retryNumber = retryNumber;
	}
	public void stateChanged(org.apache.curator.framework.CuratorFramework client, org.apache.curator.framework.state.ConnectionState newState) {
		
		switch (newState) {
		case LOST:
			  for(int i=0; i<retryNumber; i++){
			  try {
				  Boolean status = client.getZookeeperClient().blockUntilConnectedOrTimedOut();
				  if(status){
						break;
					}
					else{

						continue;
					}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			  }
		case SUSPENDED:
			  for(int i=0; i<retryNumber; i++){
			  try {
				Boolean status = client.getZookeeperClient().blockUntilConnectedOrTimedOut();
				if(status){
					break;
				}
				else{
					System.out.println("连接没有成功，将重试");
					continue;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			  }
			break;
		default:
			break;
		}
	};
}
