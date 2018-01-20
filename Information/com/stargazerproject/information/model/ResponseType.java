package com.stargazerproject.information.model;

public enum ResponseType {
	
	/**请求应答
	 * 来自请求方请求
	 * **/
    PING,
    
    /**应答回复
     * 来自应答方应答
     * **/
    REPING,
    
    /**注册端口
     * 来自请求方
     * **/
    REGISTERED,
    
    /**注册成功
     * 来自应答方
     * **/
    REGISTERED_SUCCESS,

}
