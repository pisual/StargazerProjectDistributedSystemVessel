package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name queueParameters 核心参数列表
 *  @illustrate 系统所需的queueParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="queueParameters")
@Qualifier("queueParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class QueueParameters {
	
	public QueueParameters() {}
	
	//Event队列 Start
		/**order Export 队列消费者数目**/
		/** @illustrate 参数类 **/
		private static final String Order_Export_Number_of_consumers = "1";
		/**orderExport 队列缓存**/
		/** @illustrate 参数类 **/
		private static final String Order_Export_Size_of_bufferSize = "65536";
		//Event队列 End
		
		//Event队列 Start
		/**接收Event队列消费者数目**/
		/** @illustrate 参数类 **/
		public static final String Receive_Event_Number_of_consumers = "4";
		/**接收Event队列缓存**/
		/** @illustrate 参数类 **/
		private static final String Receive_Event_Size_of_bufferSize = "65536";
		//Event队列 End
		
		//系统核心日志队列配置 Start
		/**系统核心日志队列消费者数目**/
		/** @illustrate 参数类 **/
		private static final String Receive_Log_Number_of_consumers = "1";
		/**系统核心日志队列缓存**/
		/** @illustrate 参数类 **/
		private static final String Receive_Log_Size_of_bufferSize = "65536";
		//系统核心日志队列配置 End
		
		//Kafka消息队列配置 Start
		/**消息推送队列**/
		/** @illustrate 参数类 **/
		private static final String Message_Push_Queue_Topic = "OrderPushMessageQueue";
		/**消息推送队列组**/
		/** @illustrate 参数类 **/
		private static final String Message_Push_Queue_Topic_Group = "OrderPushMessageQueueGroup";
		/**消息接收队列**/
		/** @illustrate 参数类 **/
		private static final String Message_Acquire_Queue_Topic = "OrderAcquireMessageQueue";
		/**消息接收队列组**/
		/** @illustrate 参数类 **/
		private static final String Message_Acquire_Queue_Topic_Group = "OrderAcquireMessageQueueGroup";
		/**消息队列集群地址**/
		/** @illustrate 参数类 **/
		private static final String Kafka_Binder_Brokers = "127.0.0.1:9092";
		/**消息队列Zookeeper地址**/
		/** @illustrate 参数类 **/
		private static final String Kafka_Zookeeper_Brokers = "127.0.0.1:9092";
		//Kafka消息队配置 End
}
