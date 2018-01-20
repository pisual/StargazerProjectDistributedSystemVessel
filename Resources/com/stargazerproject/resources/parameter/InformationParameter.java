package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.resources.annotation.Parameters;

/** 
 *  @name informationParameter 核心参数列表
 *  @illustrate 系统所需的informationParameter 参数
 *  @author Felixerio
 *  **/
@Component(value="informationParameter")
@Qualifier("informationParameter")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="informationParameter")
@SuppressWarnings("unused")
public class InformationParameter {
	
	  //Information配置 Start
    /** @illustrate 连接Cells主机的套接字 IP**/
    private static final String Kernel_Information_Cells_Connection_Host = "127.0.0.1";
    /** @illustrate 连接Cells主机的套接字 Port**/
    private static final String Kernel_Information_Cells_Connection_Port = "2181";
    /** @illustrate 连接是否使用SSL安全链路 **/
    private static final String Kernel_Information_Cells_Connection_SSL = "true";
    /** @illustrate  处理IO的线程数**/
	private static final String Kernel_Information_Cells_Thread_IOEventNumber = "2";
    /** @illustrate 读超时时间,A一定时间内未接受到B端消息 **/
	private static final String Kernel_Information_Cells_Heartbeat_ReaderIdleTime = "20";
	/** @illustrate 写超时时间，A端一定时间未内向被B端发送消息 **/
    private static final String Kernel_Information_Cells_Heartbeat_WriterIdleTime = "20";
    /** @illustrate 所有类型的超时时间 **/
    private static final String Kernel_Information_Cells_Heartbeat_AllIdleTime = "30";
    /** @illustrate  SocketChannel命名**/
    private static final String Kernel_Information_Cells_SocketChannel = "CellsInformationServerSocketChannel";
	//Information配置 End
	
}
