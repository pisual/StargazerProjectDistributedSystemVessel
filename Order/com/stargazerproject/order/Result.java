package com.stargazerproject.order;

import com.google.common.base.Optional;

/** 
 *  @name 结果接口
 *  @illustrate 结果通用功能
 *  @author Felixerio
 *  @version 1.0.0
 *  **/
public interface Result {
	
	/** @illustrate 设置结果为成功状态 **/
	public void complete();
	
	/** @illustrate 获取结果状态 **/
	public Optional<ResultState> resultState();
}
