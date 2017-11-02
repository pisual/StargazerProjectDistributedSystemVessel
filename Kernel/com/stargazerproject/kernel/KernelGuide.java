package com.stargazerproject.kernel;

/** 
 *  @name 内核引导
 *  @illustrate 内核新到相关功能
 *  @author Felixerio
 *  **/
public interface KernelGuide {

	/**
	* @name 分化
	* @illustrate 根据注入的参数进行特异化的分化，
	*             Cells_Master : 每一个节点都拥有的控制节点，用于引导子Cells的诞生及消亡及特殊事务
	*             Cells_Child  : 具体进行工作的节点Cells
	*             
	* **/
	public KernelGuide differentiation();
	
	/**
	* @name 启动容器
	* @illustrate 启动Spring容器，加载指定包下的所有Bean，完成容器初始化  
	* **/
	public KernelGuide startContainer(String[] args);
	
	/**
	* @name 启动核心服务
	* @illustrate 启动核心服务
	* **/
	public KernelGuide loadKernelServer();
	
	/**
	* @name 启动引导序列
	* @illustrate 完成系统核心引导启动功能
	* **/
	public KernelGuide loadBootSequence();
	
	/**
	* @name 启动模块服务
	* @illustrate 启动其他模块级别服务
	* **/
	public KernelGuide loadModelServer();
	
	/**
	* @name 启动基础序列
	* @illustrate 完成系统启动的最后的工作
	* **/
	public KernelGuide startKernelGuide();
	
}
