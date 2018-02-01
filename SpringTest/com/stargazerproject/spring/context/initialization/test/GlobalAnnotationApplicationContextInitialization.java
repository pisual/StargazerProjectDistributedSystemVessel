package com.stargazerproject.spring.context.initialization.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.stargazer.segmentation.impl.EventSegmentation;
import com.stargazerproject.analysis.impl.EventAnalysisImpl;
import com.stargazerproject.analysis.impl.EventBusAnalysisImpl;
import com.stargazerproject.analysis.impl.LogAnalysisImpl;
import com.stargazerproject.analysis.impl.TransmissionAnalysisImpl;
import com.stargazerproject.annotation.impl.AnnotationsImpl;
import com.stargazerproject.annotation.resources.AnnotationsScannerResourcesCharacteristic;
import com.stargazerproject.annotation.resources.shell.AnnotationsShell;
import com.stargazerproject.annotations.server.impl.AnnotationsServer;
import com.stargazerproject.annotations.server.listener.impl.AnnotationsServerListener;
import com.stargazerproject.annotations.server.manage.AnnotationsServerManage;
import com.stargazerproject.bus.impl.EventBus;
import com.stargazerproject.bus.resources.EventBusBlockMethodCharacteristic;
import com.stargazerproject.bus.resources.EventBusNoBlockMethodCharacteristic;
import com.stargazerproject.bus.resources.shell.EventBusResourcesShell;
import com.stargazerproject.bus.server.impl.EventBusServer;
import com.stargazerproject.bus.server.listener.impl.EventBusServerListener;
import com.stargazerproject.bus.server.manage.EventBusServerManage;
import com.stargazerproject.cache.aop.configuration.ParametersInjectAOPConfiguration;
import com.stargazerproject.cache.impl.BigCacheIndexCahce;
import com.stargazerproject.cache.impl.ByteArrayCache;
import com.stargazerproject.cache.impl.InterProcessSemaphoreMutexCache;
import com.stargazerproject.cache.impl.LeaderLatchParameterCache;
import com.stargazerproject.cache.impl.ObjectParameterCache;
import com.stargazerproject.cache.impl.OrderCache;
import com.stargazerproject.cache.impl.ServerCache;
import com.stargazerproject.cache.impl.ServerListCache;
import com.stargazerproject.cache.impl.SocketChannelCache;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.cache.impl.TreeCacheCache;
import com.stargazerproject.cache.impl.resources.BigCacheIndexCahceCharacteristic;
import com.stargazerproject.cache.impl.resources.ByteArrayCacheCacheConfigurationCharacteristic;
import com.stargazerproject.cache.impl.resources.ByteArrayCacheCacheManagerCharacteristic;
import com.stargazerproject.cache.impl.resources.ByteArrayCacheConfigurationCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheCacheLoaderCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheLoadingCacheCharacteristic;
import com.stargazerproject.cache.impl.resources.OrderCacheRemovalListenerCharacteristic;
import com.stargazerproject.cache.impl.resources.SystemParameterCahceCharacteristic;
import com.stargazerproject.cache.impl.resources.shell.BigCacheIndexCahceShell;
import com.stargazerproject.cache.impl.resources.shell.ByteArrayCacheShell;
import com.stargazerproject.cache.impl.resources.shell.OrderCahceShell;
import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.cache.server.impl.BigCacheIndexCacheServer;
import com.stargazerproject.cache.server.impl.ByteArrayCacheServer;
import com.stargazerproject.cache.server.impl.OrderCacheServer;
import com.stargazerproject.cache.server.impl.SystemParameterCacheServer;
import com.stargazerproject.cache.server.listener.impl.BigCacheIndexCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.ByteArrayCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.OrderCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.manage.BigCacheIndexCacheServerManage;
import com.stargazerproject.cache.server.manage.ByteArrayCacheServerManage;
import com.stargazerproject.cache.server.manage.OrderCacheServerManage;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.consumer.impl.EventBusConsumer;
import com.stargazerproject.consumer.impl.EventConsumer;
import com.stargazerproject.consumer.impl.TransmissionConsumer;
import com.stargazerproject.information.impl.CellsInformation;
import com.stargazerproject.information.resources.CellsInformationByteToMessageDecoderHandlerCharacteristic;
import com.stargazerproject.information.resources.CellsInformationClientRegisterHandlerCharacteristic;
import com.stargazerproject.information.resources.CellsInformationControlCharacteristic;
import com.stargazerproject.information.resources.CellsInformationEventHandlerCharacteristic;
import com.stargazerproject.information.resources.CellsInformationHandlerGuide;
import com.stargazerproject.information.resources.CellsInformationHeartbeatHandlerCharacteristic;
import com.stargazerproject.information.resources.CellsInformationMessageToByteEncoderHandlerCharacteristic;
import com.stargazerproject.information.resources.CellsInformationMethodCharacteristic;
import com.stargazerproject.information.resources.CellsInformationsOutScourHandler;
import com.stargazerproject.information.resources.shell.CellsInformationShell;
import com.stargazerproject.information.server.impl.CellsInformationServer;
import com.stargazerproject.information.server.listener.impl.CellsInformationServerListener;
import com.stargazerproject.information.server.manage.CellsInformationServerManage;
import com.stargazerproject.inject.impl.InjectImpl;
import com.stargazerproject.inject.resources.InjectClassMethodCharacteristic;
import com.stargazerproject.inject.resources.InjectSearchMethodCharacteristic;
import com.stargazerproject.inject.resources.shell.InjectShell;
import com.stargazerproject.inject.server.impl.InjectServer;
import com.stargazerproject.inject.server.listener.impl.InjectServerListener;
import com.stargazerproject.inject.server.manage.InjectServerManage;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.messagequeue.impl.OrderMessageQueue;
import com.stargazerproject.messagequeue.resources.OrderMessageQueueAcquireCharacteristic;
import com.stargazerproject.messagequeue.resources.OrderMessageQueueCallBackCharacteristic;
import com.stargazerproject.messagequeue.resources.OrderMessageQueueControlCharacteristic;
import com.stargazerproject.messagequeue.resources.OrderMessageQueuePushCharacteristic;
import com.stargazerproject.messagequeue.resources.shell.OrderMessageQueueShall;
import com.stargazerproject.messagequeue.server.impl.OrderMessageQueueServer;
import com.stargazerproject.messagequeue.server.listener.impl.OrderMessageQueueServerListener;
import com.stargazerproject.messagequeue.server.manage.OrderMessageQueueServerManage;
import com.stargazerproject.negotiate.impl.NodenNegotiateImpl;
import com.stargazerproject.negotiate.impl.ZoneNegotiateImpl;
import com.stargazerproject.negotiate.resources.NegotiateConnectionStateListenerCharacteristic;
import com.stargazerproject.negotiate.resources.NegotiateControlCharacteristic;
import com.stargazerproject.negotiate.resources.NegotiateCuratorFrameworkCharacteristic;
import com.stargazerproject.negotiate.resources.NegotiateLeaderLeaderLatchListenerCharacteristic;
import com.stargazerproject.negotiate.resources.NegotiateLeaderMethodCharacteristic;
import com.stargazerproject.negotiate.resources.NegotiateNodeCuratorListenerCharacteristic;
import com.stargazerproject.negotiate.resources.NegotiateNodeMethodCharacteristic;
import com.stargazerproject.negotiate.resources.NegotiateRegisteredWatcherCharacteristic;
import com.stargazerproject.negotiate.resources.NegotiateRetryPolicyCharacteristic;
import com.stargazerproject.negotiate.resources.impl.NegotiateInjectParameterMonitorListenerCharacteristic;
import com.stargazerproject.negotiate.resources.impl.NegotiateParametersInjectInitializationListenerCharacteristic;
import com.stargazerproject.negotiate.resources.shell.NodenNegotiateShell;
import com.stargazerproject.negotiate.server.impl.NodeNegotiateServer;
import com.stargazerproject.negotiate.server.listener.impl.NodeNegotiateListener;
import com.stargazerproject.negotiate.server.manage.NodeNegotiateServerManage;
import com.stargazerproject.queue.impl.EventBusQueue;
import com.stargazerproject.queue.impl.EventQueue;
import com.stargazerproject.queue.impl.LogQueue;
import com.stargazerproject.queue.impl.OrderExportQueue;
import com.stargazerproject.queue.impl.TransmissionQueue;
import com.stargazerproject.queue.resources.impl.CleanEventHandler;
import com.stargazerproject.queue.resources.impl.CleanLogHandler;
import com.stargazerproject.queue.resources.impl.CleanOrderExportHandler;
import com.stargazerproject.queue.resources.impl.CleanTransmissionEventHandler;
import com.stargazerproject.queue.resources.impl.EventBusHandler;
import com.stargazerproject.queue.resources.impl.EventFactory;
import com.stargazerproject.queue.resources.impl.EventHandler;
import com.stargazerproject.queue.resources.impl.EventQueueThreadFactory;
import com.stargazerproject.queue.resources.impl.EventResultMergeHandler;
import com.stargazerproject.queue.resources.impl.LogEventFactory;
import com.stargazerproject.queue.resources.impl.LogHandler;
import com.stargazerproject.queue.resources.impl.LogQueueThreadFactory;
import com.stargazerproject.queue.resources.impl.OrderExportEventFactory;
import com.stargazerproject.queue.resources.impl.OrderExportHandler;
import com.stargazerproject.queue.resources.impl.OrderExportThreadFactory;
import com.stargazerproject.queue.resources.impl.TransmissionEventFactory;
import com.stargazerproject.queue.resources.impl.TransmissionQueueHandler;
import com.stargazerproject.queue.resources.impl.TransmissionQueueThreadFactory;
import com.stargazerproject.queue.resources.shell.EventBusDisruptorShell;
import com.stargazerproject.queue.resources.shell.EventDisruptorShell;
import com.stargazerproject.queue.resources.shell.LogDisruptorShell;
import com.stargazerproject.queue.resources.shell.OrderExportDisruptorShell;
import com.stargazerproject.queue.resources.shell.TransmissionDisruptorShell;
import com.stargazerproject.queue.server.impl.EventBusQueueServer;
import com.stargazerproject.queue.server.impl.EventQueueServer;
import com.stargazerproject.queue.server.impl.LogQueueServer;
import com.stargazerproject.queue.server.impl.OrderExportQueueServer;
import com.stargazerproject.queue.server.impl.TransmissionQueueServer;
import com.stargazerproject.queue.server.listener.impl.EventBusQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.EventQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.LogQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.OrderExportQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.TransmissionQueueServerListener;
import com.stargazerproject.queue.server.manage.EventBusQueueServerManage;
import com.stargazerproject.queue.server.manage.EventQueueServerManage;
import com.stargazerproject.queue.server.manage.LogQueueServerManage;
import com.stargazerproject.queue.server.manage.OrderExportQueueServerManage;
import com.stargazerproject.queue.server.manage.TransmissionQueueServerManage;
import com.stargazerproject.resources.parameter.CacheParameters;
import com.stargazerproject.resources.parameter.InformationParameter;
import com.stargazerproject.resources.parameter.NegotiateParameters;
import com.stargazerproject.resources.parameter.QueueParameters;
import com.stargazerproject.resources.parameter.SequenceParameters;
import com.stargazerproject.resources.parameter.SystemParameters;
import com.stargazerproject.resources.parameter.UIParameters;
import com.stargazerproject.resources.service.ServiceParameterList;
import com.stargazerproject.serializable.impl.NetworkTransmissionSerializables;
import com.stargazerproject.serializable.server.impl.SerializableServer;
import com.stargazerproject.serializable.server.listener.impl.SerializableServerListener;
import com.stargazerproject.serializable.server.manage.SerializableServerManage;
import com.stargazerproject.serializable.shell.NetworkTransmissionSerializablesShell;
import com.stargazerproject.service.aop.configuration.ServerDependDetectionAOPConfiguration;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.service.resources.ServerDependCharacteristic;
import com.stargazerproject.service.resources.ServerInitializationCharacteristic;
import com.stargazerproject.service.resources.ServiceControlCharacteristic;
import com.stargazerproject.service.resources.shell.ServerShell;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;

@SpringBootApplication
@PropertySource(value="Spring.properties")
public class GlobalAnnotationApplicationContextInitialization {

	public static void ApplicationContextInitialize(String args[]){
		System.setProperty("java.awt.headless", "false");
		setProfiles();
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(
	    args,
	    
		/**Depend SystemParameter **/
		SystemParameterCahce.class,
		SystemParameterCahceCharacteristic.class,
		SystemParameterCahceShell.class,
		SystemParameterCacheServer.class,
		SystemParameterCacheServerListener.class,
		SystemParameterCacheServerManage.class,
		NegotiateParameters.class,
		SystemParameters.class,
		SequenceParameters.class,
		InformationParameter.class,
		ParametersInjectAOPConfiguration.class,
		
		/**Depend ObjectParameterCache **/
		ObjectParameterCache.class,
		
		/**Depend SocketChannelCache **/
		SocketChannelCache.class,
		
		/**Depend InterProcessSemaphoreMutexCache **/
		InterProcessSemaphoreMutexCache.class,
		
		/**Depend LeaderLatchParameterCache **/
		LeaderLatchParameterCache.class,
		
		/**Depend TreeCacheCache **/
		TreeCacheCache.class,
		
		/**Depend ServerCache **/
		ServerCache.class,
		
		/**Depend ServerListCache **/
		ServerListCache.class,
		
		/**Depend OrderQueueMessage**/
		OrderMessageQueue.class,
		OrderMessageQueueServer.class,
		OrderMessageQueueServerListener.class,
		OrderMessageQueueServerManage.class,
		OrderMessageQueueAcquireCharacteristic.class,
		OrderMessageQueueControlCharacteristic.class,
		OrderMessageQueuePushCharacteristic.class,
		OrderMessageQueueShall.class,
		OrderMessageQueueCallBackCharacteristic.class,
		
		/**Depend Server*/
		ServerDependCharacteristic.class,
		ServerInitializationCharacteristic.class,
		ServiceControlCharacteristic.class,
		ServerShell.class,
		ServerDependDetectionAOPConfiguration.class,
		
		/**Depend nodenNegotiate*/
		NodenNegotiateImpl.class,
		ZoneNegotiateImpl.class,
		NegotiateConnectionStateListenerCharacteristic.class,
		NegotiateControlCharacteristic.class,
		NegotiateCuratorFrameworkCharacteristic.class,
		NegotiateLeaderLeaderLatchListenerCharacteristic.class,
		NegotiateLeaderMethodCharacteristic.class,
		NegotiateNodeCuratorListenerCharacteristic.class,
		NegotiateNodeMethodCharacteristic.class,
		NegotiateRegisteredWatcherCharacteristic.class,
		NegotiateRetryPolicyCharacteristic.class,
		NodenNegotiateShell.class,
		NodeNegotiateServer.class,
		NodeNegotiateListener.class,
		NodeNegotiateServerManage.class,
		NegotiateInjectParameterMonitorListenerCharacteristic.class,
		NegotiateParametersInjectInitializationListenerCharacteristic.class,

		/**Depend BigCacheIndexCahce**/
		BigCacheIndexCahce.class,
		BigCacheIndexCahceCharacteristic.class,
		BigCacheIndexCahceShell.class,
		BigCacheIndexCacheServer.class,
		BigCacheIndexCacheServerListener.class,
		BigCacheIndexCacheServerManage.class,
		
		/**Depend ByteArrayCache**/
		ByteArrayCache.class,
		ByteArrayCacheCacheConfigurationCharacteristic.class,
		ByteArrayCacheCacheManagerCharacteristic.class,
		ByteArrayCacheConfigurationCharacteristic.class,
		ByteArrayCacheShell.class,
		ByteArrayCacheServer.class,
		ByteArrayCacheServerListener.class,
		ByteArrayCacheServerManage.class,
		
		/**Depend EventQueue**/
		EventQueue.class,
		EventDisruptorShell.class,
		EventFactory.class,
		EventHandler.class,
		EventQueueThreadFactory.class,
		EventQueueServer.class,
		EventQueueServerListener.class,
		EventQueueServerManage.class,
		EventConsumer.class,
		EventResultMergeHandler.class,
		CleanEventHandler.class,
		
		/**Depend EventBusQueue**/
		EventBusQueue.class,
		EventBusDisruptorShell.class,
		EventBusQueueServer.class,
		EventBusQueueServerListener.class,
		EventBusQueueServerManage.class,
		EventBusConsumer.class,
		EventBusHandler.class,
		
		/**Depend LogCache**/
		LogQueue.class,
		LogDisruptorShell.class,
		LogEventFactory.class,
		LogHandler.class,
		LogQueueThreadFactory.class,
		LogQueueServer.class,
		LogQueueServerListener.class,
		LogQueueServerManage.class,
		CleanLogHandler.class,
		
		/**Depend LogCache**/
		OrderExportQueue.class,
		OrderExportDisruptorShell.class,
		OrderExportEventFactory.class,
		OrderExportHandler.class,
		OrderExportThreadFactory.class,
		OrderExportQueueServer.class,
		OrderExportQueueServerListener.class,
		OrderExportQueueServerManage.class,
		CleanOrderExportHandler.class,
		
		/**Depend OrderCache**/
		OrderCache.class,
		OrderCacheCacheLoaderCharacteristic.class,
		OrderCacheLoadingCacheCharacteristic.class,
		OrderCacheRemovalListenerCharacteristic.class,
		OrderCahceShell.class,
		OrderCacheServer.class,
		OrderCacheServerListener.class,
		OrderCacheServerManage.class,
		
		/**Depend Resources**/
		CacheParameters.class,
		QueueParameters.class,
		UIParameters.class,
		ServiceParameterList.class,
		
		/**Depend Log**/
		GroupLogConfiguration.class,
		
		/**Depend Service**/
		GroupServiceConfiguration.class,
		
		EventSegmentation.class,
		EventAnalysisImpl.class,
		EventBusAnalysisImpl.class,
		
//		StandardCellsTransactionImpl.class,
//		HystrixConfigurationS.class,
		
//		/**User Interface Service**/
//		MainFrameBackgroundJlabelCharacteristic.class,
//		MainFrameConsoleTextPaneCharacteristic.class,
//		MainFrameJFrameCharacteristic.class,
//		MainFrameJScrollPaneCharacteristic.class,
//		MainFrameLayoutCharacteristic.class,
//		MainFrameLogoJlabelCharacteristic.class,
//		MainFrameRightConsoleTextPaneCharacteristic.class,
//		MainFrameRightJScrollPaneCharacteristic.class,
//		MainFrameStructureTopologyJlabelCharacteristic.class,
//		MainFrameShall.class,
//		LogoClickListenerCharacteristic.class,
//		MainFrameMouseAdapterListenerCharacteristic.class,
//		MainFrameMouseMotionAdapterListenerCharacteristic.class,
//		MainFramePointCharacteristic.class,
//		LoadingBaseFrameJDialogCharacteristic.class,
//		LoadingJProgressBarCharacteristic.class,
//		LoadingProgressInfoCharacteristic.class,
//		LoadingFrameBackgroundJlabelCharacteristic.class,
//		LoadingFrameLayoutCharacteristic.class,
//		LoadingFrameShall.class,
//		AssaultLilysUserInterfaceImpl.class,
//		AssaultLilysUserInterfaceShall.class,
//		FrameUserInterfaceServer.class,
//		FrameUserInterfaceListener.class,
//		FrameUserInterfaceManage.class,
		
//		/**Depend Sequence*/
//		BootInitializationSequenceImpl.class,
//		BootInitializationSequenceServer.class,
//		BootInitializationServerListener.class,
//		BootInitializationServerManage.class,
//		StandardSequenceImpl.class,
//		StandardSequenceServer.class,
//		StandardServerListener.class,
//		StandardServerManage.class,
//		BaseSequenceAOPConfiguration.class,
//		RegisterSequenceBeanModel.class,
//		SequenceResourcesShell.class,
		
		/**Depend AnnotationImpl*/
		AnnotationsImpl.class,
		AnnotationsScannerResourcesCharacteristic.class,
		AnnotationsShell.class,
		AnnotationsServer.class,
		AnnotationsServerListener.class,
		AnnotationsServerManage.class,
		
		/**Depend Bus**/
		EventBus.class,
		EventBusBlockMethodCharacteristic.class,
		EventBusNoBlockMethodCharacteristic.class,
		EventBusResourcesShell.class,
		EventBusServer.class,
		EventBusServerListener.class,
		EventBusServerManage.class,
		
		/**Depend Transmission Queue**/
		TransmissionConsumer.class,
		TransmissionQueue.class,
		CleanTransmissionEventHandler.class,
		TransmissionEventFactory.class,
		TransmissionQueueHandler.class,
		TransmissionQueueThreadFactory.class,
		TransmissionDisruptorShell.class,
		TransmissionQueueServer.class,
		TransmissionQueueServerListener.class,
		TransmissionQueueServerManage.class,
		
		/**Depend Inject**/
		InjectImpl.class,
		InjectClassMethodCharacteristic.class,
		InjectSearchMethodCharacteristic.class,
		InjectShell.class,
		InjectServer.class,
		InjectServerListener.class,
		InjectServerManage.class,
		
		/**Depend Analysis**/
		EventAnalysisImpl.class,
		EventBusAnalysisImpl.class,
		LogAnalysisImpl.class,
		TransmissionAnalysisImpl.class,
		
		/**Depend CellsInformation**/
		CellsInformation.class,
		CellsInformationByteToMessageDecoderHandlerCharacteristic.class,
		CellsInformationClientRegisterHandlerCharacteristic.class,
		CellsInformationControlCharacteristic.class,
		CellsInformationEventHandlerCharacteristic.class,
		CellsInformationHandlerGuide.class,
		CellsInformationHeartbeatHandlerCharacteristic.class,
		CellsInformationMessageToByteEncoderHandlerCharacteristic.class,
		CellsInformationMethodCharacteristic.class,
		CellsInformationsOutScourHandler.class,
		CellsInformationShell.class,
		CellsInformationServer.class,
		CellsInformationServerListener.class,
		CellsInformationServerManage.class,
		
		/**Depend Serializables**/
		NetworkTransmissionSerializables.class,
		NetworkTransmissionSerializablesShell.class,
		SerializableServer.class,
		SerializableServerListener.class,
		SerializableServerManage.class
		
		);
	} 
	
	private static void setProfiles(){
		System.setProperty("spring.profiles.active", "Run");
		System.setProperty("spring.profiles.default", "Run");
	}
	
}
