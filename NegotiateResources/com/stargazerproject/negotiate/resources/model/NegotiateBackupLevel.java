package com.stargazerproject.negotiate.resources.model;

/**
 * 集群事务备份级别，用于配置Leader及Follow及布局方式
 * 
 * **/
public enum NegotiateBackupLevel {
	
	/**
	 * 全备份，每台计算节点包含(n-1)台计算节点的指令备份，同时进行跟踪及同步
	 * 性能：提供n台计算节点性能
	 * 衰减：(单台性能)*n－(n-1)*(单台衰减)*n
	 * Leader(合计)：n
	 * Follow(单台)：n
	 * 可提供在(n-1)台计算节点下线的情况下的正常运行保障
	 * **/
	FullBsackup,
	
	/**
	 * 每两台计算节点组合成一个Zone互为备份，同时进行跟踪及同步，需要计算节点为整偶数，否者将抛掉无法配置的计算节点
	 * 性能：提供n台计算节点性能
	 * 衰减：(单台性能)*n-(单台衰减)*n
	 * Leader(合计)：n
	 * Follow(单台)：1
	 * 可提供在组内1台计算节点下线及组外合计(n／2)台计算节点下线的情况下正常运行的保障
	 * **/
	Zone2,
	
	/**
	 * 每三台计算节点组合成一个Zone互为备份，同时进行跟踪及同步，需要计算节点为3的倍数，否者将抛掉无法服务配置的计算节点
	 * 性能：提供n台计算节点性能
	 * 衰减：(单台性能)*n-2*(单台衰减)*n
	 * Leader(合计)：n
	 * Follow(单台)：2
	 * 可提供在组内2台计算节点下线及组外合计(n／3)*2台计算节点下线的情况下正常运行的保障
	 * **/
	Zone3
}
