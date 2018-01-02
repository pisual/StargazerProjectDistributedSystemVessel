package com.stargazerproject.negotiate.resources;

import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="negotiateLeaderLeaderLatchListenerCharacteristic")
@Qualifier("negotiateLeaderLeaderLatchListenerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateLeaderLeaderLatchListenerCharacteristic implements LeaderLatchListener{

	/**
	* @name 常规初始化构造
	* @illustrate 基于外部参数进行注入
	* **/
	public NegotiateLeaderLeaderLatchListenerCharacteristic() {}
	
	 @Override
     public void isLeader() {
         System.out.println("I am Leader");
         
     }

     @Override
     public void notLeader() {
         System.out.println("I am not Leader");
     }
}
