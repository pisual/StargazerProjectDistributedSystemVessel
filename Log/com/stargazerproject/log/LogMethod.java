package com.stargazerproject.log;


/** 
 *  @name 核心日志托管接口
 *  @illustrate 针对于日志进行统一的收集，然后根据系统预先设定的日志级别进行处理
 *  @author Felixerio
 *  **/
public interface LogMethod{
	
	/** @illustrate 根据系统现在预定的DEBUG级别的处理程序进行日志处理**/
	public void DEBUG(Object object, String message);
	
	/** @illustrate 根据系统现在预定的INFO级别的处理程序进行日志处理**/
	public void INFO(Object object, String message);
	
	/** @illustrate 根据系统现在预定的WARN级别的处理程序进行日志处理**/
	public void WARN(Object object, String message);
	
	/** @illustrate 根据系统现在预定的ERROR级别的处理程序进行日志处理**/
	public void ERROR(Object object, String message);
	
	/** @illustrate 根据系统现在预定的FATAL级别的处理程序进行日志处理**/
	public void FATAL(Object object, String message);
}
