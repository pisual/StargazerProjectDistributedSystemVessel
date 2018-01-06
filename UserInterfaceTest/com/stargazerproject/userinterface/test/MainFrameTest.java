package com.stargazerproject.userinterface.test;

import java.util.concurrent.TimeUnit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.stargazer.segmentation.impl.EventExecuteImpl;
import com.stargazer.segmentation.impl.EventSegmentation;
import com.stargazerproject.cache.aop.configuration.OrderCacheAOPConfiguration;
import com.stargazerproject.cache.aop.configuration.SystemParameterAOPConfiguration;
import com.stargazerproject.cache.impl.BigCacheIndexCahce;
import com.stargazerproject.cache.impl.ByteArrayCache;
import com.stargazerproject.cache.impl.OrderCache;
import com.stargazerproject.cache.impl.SystemParameterCahce;
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
import com.stargazerproject.cache.server.impl.BigCacheIndexCacheBuiltInCacheServer;
import com.stargazerproject.cache.server.impl.ByteArrayCacheServer;
import com.stargazerproject.cache.server.impl.OrderCacheServer;
import com.stargazerproject.cache.server.impl.SystemParameterBuiltInCacheServer;
import com.stargazerproject.cache.server.listener.impl.BigCacheIndexCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.ByteArrayCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.OrderCacheServerListener;
import com.stargazerproject.cache.server.listener.impl.SystemParameterCacheServerListener;
import com.stargazerproject.cache.server.manage.BigCacheIndexCacheServerManage;
import com.stargazerproject.cache.server.manage.ByteArrayCacheServerManage;
import com.stargazerproject.cache.server.manage.OrderCacheServerManage;
import com.stargazerproject.cache.server.manage.SystemParameterCacheServerManage;
import com.stargazerproject.cell.aopconfiguration.HystrixConfigurationS;
import com.stargazerproject.cell.impl.StandardCellsTransactionImpl;
import com.stargazerproject.consumer.impl.EventConsumer;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.queue.impl.EventQueue;
import com.stargazerproject.queue.impl.LogQueue;
import com.stargazerproject.queue.impl.OrderExportQueue;
import com.stargazerproject.queue.resources.impl.CleanEventHandler;
import com.stargazerproject.queue.resources.impl.CleanLogHandler;
import com.stargazerproject.queue.resources.impl.CleanOrderExportHandler;
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
import com.stargazerproject.queue.resources.shell.EventDisruptorShell;
import com.stargazerproject.queue.resources.shell.LogDisruptorShell;
import com.stargazerproject.queue.resources.shell.OrderExportDisruptorShell;
import com.stargazerproject.queue.server.impl.EventQueueServer;
import com.stargazerproject.queue.server.impl.LogQueueServer;
import com.stargazerproject.queue.server.impl.OrderExportQueueServer;
import com.stargazerproject.queue.server.listener.impl.EventQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.LogQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.OrderExportQueueServerListener;
import com.stargazerproject.queue.server.manage.EventQueueServerManage;
import com.stargazerproject.queue.server.manage.LogQueueServerManage;
import com.stargazerproject.queue.server.manage.OrderExportQueueServerManage;
import com.stargazerproject.resources.parameter.CacheParameters;
import com.stargazerproject.resources.parameter.QueueParameters;
import com.stargazerproject.resources.parameter.UIParameters;
import com.stargazerproject.resources.service.ServiceParameterList;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;
import com.stargazerproject.userinterface.LoadingUserInterface;
import com.stargazerproject.userinterface.extend.AssaultLilysUserInterface;
import com.stargazerproject.userinterface.extend.MainFrameAssaultLilysUserInterface;
import com.stargazerproject.userinterface.impl.AssaultLilysUserInterfaceImpl;
import com.stargazerproject.userinterface.resources.LoadingBaseFrameJDialogCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingFrameBackgroundJlabelCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingFrameLayoutCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingJProgressBarCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingProgressInfoCharacteristic;
import com.stargazerproject.userinterface.resources.LogoClickListenerCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameBackgroundJlabelCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameConsoleTextPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameJFrameCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameJScrollPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameLayoutCharacteristic;
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
import com.stargazerproject.userinterface.server.manage.FrameUserInterfaceManage;

@FixMethodOrder(MethodSorters.JVM)
public class MainFrameTest {
	
	public static void ApplicationContextInitialize(String args[]){
		System.setProperty("java.awt.headless", "false");
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(
	    args,
		/**Itself Configuration Class**/
		SystemParameterCahce.class,
		SystemParameterCahceCharacteristic.class,
		SystemParameterCahceShell.class,
		SystemParameterBuiltInCacheServer.class,
		SystemParameterCacheServerListener.class,
		SystemParameterCacheServerManage.class,

     /******Depend Configuration Class******/
		
		/**Depend BigCacheIndexCahce**/
		BigCacheIndexCahce.class,
		BigCacheIndexCahceCharacteristic.class,
		BigCacheIndexCahceShell.class,
		BigCacheIndexCacheBuiltInCacheServer.class,
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
		
		/**Depend AOP**/
		OrderCacheAOPConfiguration.class,
		SystemParameterAOPConfiguration.class,
		
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
		EventExecuteImpl.class,
		
		StandardCellsTransactionImpl.class,
		HystrixConfigurationS.class,
		
		MainFrameBackgroundJlabelCharacteristic.class,
		MainFrameConsoleTextPaneCharacteristic.class,
		MainFrameJFrameCharacteristic.class,
		MainFrameJScrollPaneCharacteristic.class,
		MainFrameLayoutCharacteristic.class,
		MainFrameLogoJlabelCharacteristic.class,
		MainFrameRightConsoleTextPaneCharacteristic.class,
		MainFrameRightJScrollPaneCharacteristic.class,
		MainFrameStructureTopologyJlabelCharacteristic.class,
		MainFrameShall.class,
		LogoClickListenerCharacteristic.class,
		MainFrameMouseAdapterListenerCharacteristic.class,
		MainFrameMouseMotionAdapterListenerCharacteristic.class,
		MainFramePointCharacteristic.class,
		LoadingBaseFrameJDialogCharacteristic.class,
		LoadingJProgressBarCharacteristic.class,
		LoadingProgressInfoCharacteristic.class,
		LoadingFrameBackgroundJlabelCharacteristic.class,
		LoadingFrameLayoutCharacteristic.class,
		LoadingFrameShall.class,
		AssaultLilysUserInterfaceImpl.class,
		AssaultLilysUserInterfaceShall.class,
		FrameUserInterfaceServer.class,
		FrameUserInterfaceListener.class,
		FrameUserInterfaceManage.class
		);
	}
	
	@Test
	public void SpringInit(){
		System.setProperty("java.awt.headless", "false");
		String args[] = {"debug"};
	//	ApplicationContextInitialize(args);
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
	}

	@Test
	public void serviceStartTest(){
		System.setProperty("java.awt.headless", "false");
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}
	
	@Test
	public void startUserInterfaceInit(){
		System.setProperty("java.awt.headless", "false");
		Optional<AssaultLilysUserInterface> mainFrameShall = BeanContainer.instance().getBean(Optional.of("mainFrameShallCharacteristic"), Optional.class);
		mainFrameShall.get().startMain();
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("java.awt.headless", "false");
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}
}
