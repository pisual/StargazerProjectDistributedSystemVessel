package com.stargazerproject.negotiate.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.negotiate.base.impl.BaseNegotiateImpl;

@Component(value="nodenNegotiateImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("nodenNegotiateImpl")
/** 
 *  @name 节点级别协商
 *  @illustrate 节点级别协商位于Cells节点上线时进行的协商工作
 *              工作流程：
 *                          状态                                                       -> 静默成员模式中如果被邀请成功 -> 组成员模式 -> 组模式()
 *                              启动状态 -> 等待状态（静默成员模式，允许被邀请，不能邀请别人）-> 
 *                                        											                                   -> 如果邀请第一位组成员成功(成员ZoneLock加锁) -> 组Leader模式 -> 按照BK级别寻找下一位成员 -... -> 组模式
 *                                                                                     -> 组Leader模式(自身ZoneLock加锁) ->
 *                                                                                                                      ->如果邀请第一位组成员失败(自身ZoneLock解锁) -> 等待状态（静默成员模式，允许被邀请，不能邀请别人）
 *                      
 *                          锁
 *                             协议包含一个锁，ZoneLock
 *
 *                      
 *                      
 *                                                          Node negotiation protocol
 *                                                                 节点协商协议
 *                                                                 
 *                                                                   第一协议
 *                                                              去中心化阶段聚合自身弱化协议
 *                      S1，在集群主Cells列表（Master_Cells_List）注册自己的信息（Cells(ID, BackupLevel,Zone), /Cells_Lock(ZoneLock), /Cells_Zone(Group_Name))
 *                      
 *                             序列：
 *                                                            注册节点
 *                                                 {
 *                             1.                   ／Master_Cells_List/UUID_1
 *                                                  ／Master_Cells_List/UUID_1/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_1/Zone
 *                             
 *                                                  ／Master_Cells_List/UUID_2
 *                                                  ／Master_Cells_List/UUID_2/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_2/Zone
 *                                                  
 *                                                  ／Master_Cells_List/UUID_3
 *                                                  ／Master_Cells_List/UUID_3/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_3/Zone
 *                                                  
 *                                                  }
 *                                                  
 *                                                          自由组建
 *                                                  {
 *                                                  ／Master_Cells_List/UUID_1
 *                                                  ／Master_Cells_List/UUID_1/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_1/Zone
 *                                                  ／Master_Cells_List/UUID_1/Cells_Lock
 *                                                  ／Master_Cells_List/UUID_1/Cells_Zone/UUID_1_Group
 *                                                  
 *                                                  ／Master_Cells_List/UUID_2
 *                                                  ／Master_Cells_List/UUID_2/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_2/Zone
 *                                                  ／Master_Cells_List/UUID_2/Cells_Lock
 *                                                  ／Master_Cells_List/UUID_2/Cells_Zone/UUID_1_Group
 *                                                  
 *                                                  ／Master_Cells_List/UUID_3
 *                                                  ／Master_Cells_List/UUID_3/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_3/Zone
 *                                                  ／Master_Cells_List/UUID_3/Cells_Lock
 *                                                  ／Master_Cells_List/UUID_3/Cells_Zone/UUID_1_Group
 *                                                  }
 *                                                  
 *                                                           注册第一组
 *                                                  {
 *                                                  ／Master_Cells_List/UUID_1
 *                                                  ／Master_Cells_List/UUID_1/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_1/Zone
 *                                                  ／Master_Cells_List/UUID_1/Cells_Lock
 *                                                  ／Master_Cells_List/UUID_1/Cells_Zone/UUID_1_Group/UUID_1／LeaderA
 *                                                                                                    /UUID_2
 *                                                                                                    /UUID_3
 *                                                  
 *                                                  ／Master_Cells_List/UUID_2
 *                                                  ／Master_Cells_List/UUID_2/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_2/Zone
 *                                                  ／Master_Cells_List/UUID_2/Cells_Lock
 *                                                  ／Master_Cells_List/UUID_2/Cells_Zone/UUID_1_Group/UUID_1／LeaderA
 *                                                                                                    /UUID_2
 *                                                                                                    /UUID_3
 *                                                  
 *                                                  
 *                                                  ／Master_Cells_List/UUID_3
 *                                                  ／Master_Cells_List/UUID_3/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_3/Zone
 *                                                  ／Master_Cells_List/UUID_3/Cells_Lock
 *                                                  ／Master_Cells_List/UUID_3/Cells_Zone/UUID_1_Group/UUID_1／LeaderA
 *                                                                                                    /UUID_2
 *                                                                                                    /UUID_3
 *                                                  
 *                                                  }
 *                                                  
 *                                                   注册第二组
 *                                                  {
 *                                                  ／Master_Cells_List/UUID_1
 *                                                  ／Master_Cells_List/UUID_1/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_1/Zone
 *                                                  ／Master_Cells_List/UUID_1/Cells_Lock
 *                                                  ／Master_Cells_List/UUID_1/Cells_Zone/UUID_1_Group/UUID_1／LeaderA
 *                                                                                                    /UUID_2
 *                                                                                                    /UUID_3
 *                                                  ／Master_Cells_List/UUID_1/Cells_Zone/UUID_2_Group/UUID_1／LeaderA（GiveUP）
 *                                                                                                    /UUID_2/LeaderB
 *                                                                                                    /UUID_3
 *                                                  
 *                                                  ／Master_Cells_List/UUID_2
 *                                                  ／Master_Cells_List/UUID_2/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_2/Zone
 *                                                  ／Master_Cells_List/UUID_2/Cells_Lock
 *                                                  ／Master_Cells_List/UUID_2/Cells_Zone/UUID_1_Group/UUID_1／LeaderA
 *                                                                                                    /UUID_2
 *                                                                                                    /UUID_3
 *                                                  ／Master_Cells_List/UUID_1/Cells_Zone/UUID_2_Group/UUID_1／LeaderA（GiveUP）
 *                                                                                                    /UUID_2/LeaderB
 *                                                                                                    /UUID_3
 *                                                  
 *                                                  
 *                                                  ／Master_Cells_List/UUID_3
 *                                                  ／Master_Cells_List/UUID_3/BackupLevel/Zone3
 *                                                  ／Master_Cells_List/UUID_3/Zone
 *                                                  ／Master_Cells_List/UUID_3/Cells_Lock
 *                                                  ／Master_Cells_List/UUID_3/Cells_Zone/UUID_1_Group/UUID_1／LeaderA
 *                                                                                                    /UUID_2
 *                                                                                                    /UUID_3
 *                                                   ／Master_Cells_List/UUID_1/Cells_Zone/UUID_2_Group/UUID_1／LeaderA（GiveUP）
 *                                                                                                    /UUID_2/LeaderB
 *                                                                                                    /UUID_3
 *                                                  
 *                                                  }
 *                                                  
 *                                                 
 *                      
 *                      S2, 等待（等待之非常重要的一环，也是构建整个去中心化集群的重要的一步，等待的时间是随机的，这也为自由分区提供的一定余地），等待期间自身不允许寻找组成员，但允许被邀请加入组
 *                                                          
 *                                                          根据BackupLevel自由的构建集群
 *                                                          
 *                          完全的自由构架将涉及到大量的锁并且会耗费大量的时间，所以将根据一定程度的规则进行分组构建
 *                          这里的规则就是Stargazer Distributed Cells Grouping Algorithm（SDCGA）观星者分布式分组算法
 *                         
 *                         G1, 每一个Cells节点都保存有全部的Cells信息，这是个高可用性的分区表，表内信息有可能已经失效
 *                             Cells节点将根据自己的Cells_ID计算将要与自己分区的节点，在Cells节点表中查找到符合的节点,如果寻找到进行下一步，如果寻找不到继续G1，直到达到最大寻索时间后返回S2
 *                         G2, 自身加ZoneLock锁，禁止被加入到其他组，成为组Leader
 *                              1,如果自身已经被加ZoneLock，本次寻找失败，自身已经成为组成员，将等待后续分组，如果等待过程中锁消失并且没有成功分组，返回G1步骤
 *                              2,如果对象已经加锁，本次寻找失败，返回G1
 *                              3,如果对象加锁成功，如果满足BK建立组成功，退出建组，如果没有满足BK，进入G3，如果失败，解除ZoneLock锁返回S2
 *                         G3, 在自身保持ZoneLock锁的状态下，返回G1寻找下一位
 *                              Z1, 如果对象已经加锁，本次寻找失败，返回G1
 *                              Z2, 如果对象加锁成功，如果满足BK建立组成功，退出建组，如果没有满足BK，进入G3，如果失败，根据最长寻组时间，再次寻找成员或者超时解除自身ZoneLock锁及其组成员的ZoneLock锁返回S2
 *                               
 *                         
 *                         
 *                                               Stargazer Distributed Cells Grouping Algorithm
 *                                                                  (SDCGA)
 *                                                             观星者分布式分组算法
 *                                                                  第一算法
 *                                                                尾端寻索算法
 *                                                             
 *                         
 *                         
 *  @param  Optional<Negotiate> negotiateArg
 *  @author Felixerio
 *  **/
public class NodenNegotiateImpl extends BaseNegotiateImpl implements StanderCharacteristicShell<Negotiate>{
	
	@Override
	public void initialize(Optional<Negotiate> negotiateArg) {
		this.negotiate = negotiateArg.get();
	}
}
