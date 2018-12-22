package com.stargazerproject.spring.context.initialization.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.staragzerproject.characteristics.impl.ComponentsCharacteristic;
import com.staragzerproject.characteristics.shell.ComponentsCharacteristicShell;
import com.stargazer.segmentation.impl.EventSegmentation;
import com.stargazerproject.analysis.impl.EventExecuteAnalysisImpl;
import com.stargazerproject.analysis.impl.EventResultAnalysisImpl;
import com.stargazerproject.analysis.impl.LogAnalysisImpl;
import com.stargazerproject.annotation.impl.AnnotationsImpl;
import com.stargazerproject.annotation.resources.AnnotationsScannerResourcesCharacteristic;
import com.stargazerproject.annotation.resources.shell.AnnotationsShell;
import com.stargazerproject.annotations.server.impl.AnnotationsServer;
import com.stargazerproject.annotations.server.listener.impl.AnnotationsServerListener;
import com.stargazerproject.annotations.server.manage.AnnotationsServerManage;
import com.stargazerproject.cache.aop.configuration.ParametersInjectAOPConfiguration;
import com.stargazerproject.cache.datastructure.impl.BigCacheIndexCahce;
import com.stargazerproject.cache.datastructure.impl.InterProcessSemaphoreMutexCache;
import com.stargazerproject.cache.datastructure.impl.LeaderLatchParameterCache;
import com.stargazerproject.cache.datastructure.impl.ObjectParameterCache;
import com.stargazerproject.cache.datastructure.impl.ServerCache;
import com.stargazerproject.cache.datastructure.impl.ServerListCache;
import com.stargazerproject.cache.datastructure.impl.SocketChannelCache;
import com.stargazerproject.cache.datastructure.impl.TreeCacheCache;
import com.stargazerproject.cache.impl.ByteArrayCache;
import com.stargazerproject.cache.impl.SystemParameterCahce;
import com.stargazerproject.cache.impl.TransactionCache;
import com.stargazerproject.cache.impl.resources.ByteArrayCacheCacheConfigurationCharacteristic;
import com.stargazerproject.cache.impl.resources.ByteArrayCacheCacheManagerCharacteristic;
import com.stargazerproject.cache.impl.resources.ByteArrayCacheConfigurationCharacteristic;
import com.stargazerproject.cache.impl.resources.SystemParameterCahceCharacteristic;
import com.stargazerproject.cache.impl.resources.TransactionCacheCacheLoaderCharacteristic;
import com.stargazerproject.cache.impl.resources.TransactionCacheLoadingCacheCharacteristic;
import com.stargazerproject.cache.impl.resources.TransactionCacheRemovalListenerCharacteristic;
import com.stargazerproject.cache.impl.resources.shell.ByteArrayCacheShell;
import com.stargazerproject.cache.impl.resources.shell.SystemParameterCahceShell;
import com.stargazerproject.cache.impl.resources.shell.TransactionCahceShell;
import com.stargazerproject.cache.server.impl.ByteArrayCacheServer;
import com.stargazerproject.cache.server.impl.SystemParameterCacheServer;
import com.stargazerproject.cache.server.impl.TransactionCacheServer;
import com.stargazerproject.cache.server.listener.impl.ByteArrayCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.TransactionCacheServerListener;
import com.stargazerproject.cache.server.manage.ByteArrayCacheServerManage;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.cache.server.manage.TransactionCacheServerManage;
import com.stargazerproject.characteristics.server.impl.ComponentsCharacteristicServer;
import com.stargazerproject.characteristics.server.listener.impl.ComponentsCharacteristicServerListener;
import com.stargazerproject.characteristics.server.manage.ComponentsCharacteristicServerManage;
import com.stargazerproject.consumer.impl.EventBusConsumer;
import com.stargazerproject.consumer.impl.EventConsumer;
import com.stargazerproject.inject.impl.InjectImpl;
import com.stargazerproject.inject.resources.InjectClassMethodCharacteristic;
import com.stargazerproject.inject.resources.InjectSearchMethodCharacteristic;
import com.stargazerproject.inject.resources.shell.InjectShell;
import com.stargazerproject.inject.server.impl.InjectServer;
import com.stargazerproject.inject.server.listener.impl.InjectServerListener;
import com.stargazerproject.inject.server.manage.InjectServerManage;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.messagequeue.impl.TransactionMessageQueue;
import com.stargazerproject.messagequeue.resources.TransactionMessageQueueAcquireCharacteristic;
import com.stargazerproject.messagequeue.resources.TransactionMessageQueueCallBackCharacteristic;
import com.stargazerproject.messagequeue.resources.TransactionMessageQueueControlCharacteristic;
import com.stargazerproject.messagequeue.resources.TransactionMessageQueuePushCharacteristic;
import com.stargazerproject.messagequeue.resources.shell.TransactionMessageQueueShall;
import com.stargazerproject.messagequeue.server.impl.TransactionMessageQueueServer;
import com.stargazerproject.messagequeue.server.listener.impl.TransactionMessageQueueServerListener;
import com.stargazerproject.messagequeue.server.manage.TransactionMessageQueueServerManage;
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
import com.stargazerproject.queue.impl.TransactionExportQueue;
import com.stargazerproject.queue.resources.impl.CleanEventHandler;
import com.stargazerproject.queue.resources.impl.CleanLogHandler;
import com.stargazerproject.queue.resources.impl.CleanTransactionExportEventHandler;
import com.stargazerproject.queue.resources.impl.EventBusHandler;
import com.stargazerproject.queue.resources.impl.EventFactory;
import com.stargazerproject.queue.resources.impl.EventHandler;
import com.stargazerproject.queue.resources.impl.EventQueueThreadFactory;
import com.stargazerproject.queue.resources.impl.EventResultMergeHandler;
import com.stargazerproject.queue.resources.impl.LogEventFactory;
import com.stargazerproject.queue.resources.impl.LogHandler;
import com.stargazerproject.queue.resources.impl.LogQueueThreadFactory;
import com.stargazerproject.queue.resources.impl.TransactionExportEventFactory;
import com.stargazerproject.queue.resources.impl.TransactionExportEventHandler;
import com.stargazerproject.queue.resources.impl.TransactionExportEventThreadFactory;
import com.stargazerproject.queue.resources.shell.EventBusDisruptorShell;
import com.stargazerproject.queue.resources.shell.EventDisruptorShell;
import com.stargazerproject.queue.resources.shell.LogDisruptorShell;
import com.stargazerproject.queue.resources.shell.TransactionExportEventDisruptorShell;
import com.stargazerproject.queue.server.impl.EventBusQueueServer;
import com.stargazerproject.queue.server.impl.EventQueueServer;
import com.stargazerproject.queue.server.impl.LogQueueServer;
import com.stargazerproject.queue.server.impl.TransactionExportEventQueueServer;
import com.stargazerproject.queue.server.listener.impl.EventBusQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.EventQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.LogQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.TransactionExportEventQueueServerListener;
import com.stargazerproject.queue.server.manage.EventBusQueueServerManage;
import com.stargazerproject.queue.server.manage.EventQueueServerManage;
import com.stargazerproject.queue.server.manage.LogQueueServerManage;
import com.stargazerproject.queue.server.manage.TransactionExportEventQueueServerManage;
import com.stargazerproject.resources.parameter.CacheParameters;
import com.stargazerproject.resources.parameter.InformationParameter;
import com.stargazerproject.resources.parameter.InjectParameters;
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
import com.stargazerproject.userinterface.impl.UserInterfaceImpl;
import com.stargazerproject.userinterface.resources.LoadingBaseFrameJDialogCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingFrameBackgroundJlabelCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingFrameLayoutCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingJProgressBarCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingJProgressBarUI;
import com.stargazerproject.userinterface.resources.LoadingProgressInfoCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameBackgroundJlabelCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameConsoleTextPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameJFrameCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameJScrollPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameLayoutCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameLogoClickListenerCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameLogoJlabelCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameMouseAdapterListenerCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameMouseMotionAdapterListenerCharacteristic;
import com.stargazerproject.userinterface.resources.MainFramePointCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameRightConsoleTextPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameRightJScrollPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameStructureTopologyJlabelCharacteristic;
import com.stargazerproject.userinterface.resources.shall.AssaultLilysUserInterfaceShall;
import com.stargazerproject.userinterface.resources.shall.LoadingFrameShall;
import com.stargazerproject.userinterface.resources.shall.MainFrameShall;
import com.stargazerproject.userinterface.server.impl.FrameUserInterfaceServer;
import com.stargazerproject.userinterface.server.listener.impl.FrameUserInterfaceListener;
import com.stargazerproject.userinterface.server.manage.FrameUserInterfaceServerManage;

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
		TransactionMessageQueue.class,
		TransactionMessageQueueServer.class,
		TransactionMessageQueueServerListener.class,
		TransactionMessageQueueServerManage.class,
		TransactionMessageQueueAcquireCharacteristic.class,
		TransactionMessageQueueControlCharacteristic.class,
		TransactionMessageQueuePushCharacteristic.class,
		TransactionMessageQueueShall.class,
		TransactionMessageQueueCallBackCharacteristic.class,
		
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
		TransactionExportQueue.class,
		TransactionExportEventDisruptorShell.class,
		TransactionExportEventFactory.class,
		TransactionExportEventHandler.class,
		TransactionExportEventThreadFactory.class,
		TransactionExportEventQueueServer.class,
		TransactionExportEventQueueServerListener.class,
		TransactionExportEventQueueServerManage.class,
		CleanTransactionExportEventHandler.class,
		
		/**Depend TransactionCache**/
		TransactionCache.class,
		TransactionCacheCacheLoaderCharacteristic.class,
		TransactionCacheLoadingCacheCharacteristic.class,
		TransactionCacheRemovalListenerCharacteristic.class,
		TransactionCahceShell.class,
		TransactionCacheServer.class,
		TransactionCacheServerListener.class,
		TransactionCacheServerManage.class,
		
		/**Depend Resources**/
		CacheParameters.class,
		QueueParameters.class,
		UIParameters.class,
		ServiceParameterList.class,
		InformationParameter.class,
		InjectParameters.class,
		NegotiateParameters.class,
		QueueParameters.class,
		SequenceParameters.class,
		SystemParameters.class,
		
		/**Depend Log**/
		GroupLogConfiguration.class,
		
		/**Depend Service**/
		GroupServiceConfiguration.class,
		
		EventSegmentation.class,
		EventExecuteAnalysisImpl.class,
		EventResultAnalysisImpl.class,
		
//		StandardCellsTransactionImpl.class,
//		HystrixConfigurationS.class,
		
		/**User Interface Service**/
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
//		MainFrameLogoClickListenerCharacteristic.class,
//		MainFrameMouseAdapterListenerCharacteristic.class,
//		MainFrameMouseMotionAdapterListenerCharacteristic.class,
//		MainFramePointCharacteristic.class,
		LoadingBaseFrameJDialogCharacteristic.class,
		LoadingJProgressBarCharacteristic.class,
		LoadingProgressInfoCharacteristic.class,
		LoadingFrameBackgroundJlabelCharacteristic.class,
		LoadingFrameLayoutCharacteristic.class,
		LoadingFrameShall.class,
		UserInterfaceImpl.class,
		AssaultLilysUserInterfaceShall.class,
		FrameUserInterfaceServer.class,
		FrameUserInterfaceListener.class,
		FrameUserInterfaceServerManage.class,
		LoadingJProgressBarUI.class,
		
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
		
//		/**Depend Bus**/
//		EventBus.class,
//		EventBusBlockMethodCharacteristic.class,
//		EventBusNoBlockMethodCharacteristic.class,
//		EventBusResourcesShell.class,
//		EventBusServer.class,
//		EventBusServerListener.class,
//		EventBusServerManage.class,
//		
//		/**Depend Transmission Queue**/
//		TransmissionConsumer.class,
//		TransmissionQueue.class,
//		CleanTransmissionEventHandler.class,
//		TransmissionEventFactory.class,
//		TransmissionQueueHandler.class,
//		TransmissionQueueThreadFactory.class,
//		TransmissionDisruptorShell.class,
//		TransmissionQueueServer.class,
//		TransmissionQueueServerListener.class,
//		TransmissionQueueServerManage.class,
		
		/**Depend Inject**/
		InjectImpl.class,
		InjectClassMethodCharacteristic.class,
		InjectSearchMethodCharacteristic.class,
		InjectShell.class,
		InjectServer.class,
		InjectServerListener.class,
		InjectServerManage.class,
		
//		/**Depend Analysis**/
//		EventExecuteAnalysisImpl.class,
//		EventResultAnalysisImpl.class,
		LogAnalysisImpl.class,
		
//		/**Depend CellsInformation**/
//		CellsInformation.class,
//		CellsInformationByteToMessageDecoderHandlerCharacteristic.class,
//		CellsInformationClientRegisterHandlerCharacteristic.class,
//		CellsInformationControlCharacteristic.class,
//		CellsInformationEventHandlerCharacteristic.class,
//		CellsInformationHandlerGuide.class,
//		CellsInformationHeartbeatHandlerCharacteristic.class,
//		CellsInformationMessageToByteEncoderHandlerCharacteristic.class,
//		CellsInformationMethodCharacteristic.class,
//		CellsInformationsOutScourHandler.class,
//		CellsInformationShell.class,
//		CellsInformationServer.class,
//		CellsInformationServerListener.class,
//		CellsInformationServerManage.class,
		
		/**Depend Serializables**/
		NetworkTransmissionSerializables.class,
		NetworkTransmissionSerializablesShell.class,
		SerializableServer.class,
		SerializableServerListener.class,
		SerializableServerManage.class,
		
		/**Depend SystemParameter **/
		ComponentsCharacteristic.class,
		ComponentsCharacteristicShell.class,
		ComponentsCharacteristicServer.class,
		ComponentsCharacteristicServerListener.class,
		ComponentsCharacteristicServerManage.class
		
		);
	} 
	
	private static void setProfiles(){
		System.setProperty("spring.profiles.active", "Run");
		System.setProperty("spring.profiles.default", "Run");
	}
	
}
