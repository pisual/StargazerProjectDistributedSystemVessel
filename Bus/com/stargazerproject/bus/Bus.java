package com.stargazerproject.bus;

/** 
 *  @name 总线接口
 *  @illustrate 包含总线的所有功能
 *  @param <T> 总线类型
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface Bus<T> extends BusBlockMethod<T>, BusControl, BusNoBlockMethod<T>{

}
