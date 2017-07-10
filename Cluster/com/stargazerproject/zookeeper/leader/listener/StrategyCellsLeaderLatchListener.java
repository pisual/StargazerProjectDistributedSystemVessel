package com.stargazerproject.zookeeper.leader.listener;

import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

public class StrategyCellsLeaderLatchListener implements LeaderLatchListener{

	 @Override
     public void isLeader() {
         System.out.println("I am Leader");
         
     }

     @Override
     public void notLeader() {
         System.out.println("I am not Leader");
     }
}
