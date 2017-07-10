package com.stargazerproject.cache.impl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.tools.FileObject;

import org.junit.Test;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.configuration.GroupCacheConfiguration;
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.queue.configuration.GroupQueueConfiguration;
import com.stargazerproject.resources.configuration.GroupResourcesConfiguration;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;
import com.stargazerproject.test.pattern.WorkInTest;

public class ByteArrayCahceImplRunLevelTest implements WorkInTest{
	
	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(GroupServiceConfiguration.class,GroupResourcesConfiguration.class,GroupLogConfiguration.class,GroupCacheConfiguration.class,GroupQueueConfiguration.class);
	}
	
	@Test
	@Override
	public void interfaceInitialize() {

	}
	
	@Test
	public void serverNotStartPutObject() throws FileNotFoundException{
//		ServiceControl serviceControl = (ServiceControl)BeanContainer.instance().getBean(Optional.of("moduleService"));
//		serviceControl.startAllservice();
//		BigCache<String,byte[]> bigCache = (BigCache<String,byte[]>)BeanContainer.instance().getBean(Optional.of("byteArrayCache"));
//		try {
//			FileInputStream f = new FileInputStream(new File("/Users/Felixerio/Desktop/Shelter the Animation [eng][BDRip x264 1920x1080 FLAC AAC].mkv"));
//			
//			byte[] byteTemp = new byte[1024];
//			
//			int i=0;
//			
//			while(f.read(byteTemp, i, 1024)>0)
//			{
//			
//				if(i == 0){
//					bigCache.put(Optional.of("Shelter the Animation [eng][BDRip x264 1920x1080 FLAC AAC]"), byteTemp);
//				}
//				else{
//					bigCache.add(Optional.of("Shelter the Animation [eng][BDRip x264 1920x1080 FLAC AAC]"), byteTemp);
//				}
//				
//				i=i+byteTemp.length;
//				
//				byteTemp = new byte[1024];
//				
//				System.out.println("读入第 "+i+" 轮");
//				
//				TimeUnit.SECONDS.sleep(5);
//				
//			}
//			
//		} catch (IOException | InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		ServiceControl serviceControl = (ServiceControl)BeanContainer.instance().getBean(Optional.of("moduleService"));
//		serviceControl.startAllservice();
//		BigCache<String,byte[]> bigCache = (BigCache<String,byte[]>)BeanContainer.instance().getBean(Optional.of("byteArrayCache"));
//		try {
//			FileInputStream f = new FileInputStream(new File("/Users/Felixerio/Desktop/20170412_114835.flv"));
//			
//			byte[] byteTemp = new byte[1024*1024*10];
//			
//			int i=0;
//			
//			System.out.println("Start");
//			
////			while(f.read(byteTemp)>0)
////			{
////			
////				//if(i == 0){
////					bigCache.put(Optional.of("Shelter the Animation [eng][BDRip x264 1920x1080 FLAC AAC]"+"---"+i), byteTemp);
////				//}
//////				else{
//////					bigCache.add(Optional.of("Shelter the Animation [eng][BDRip x264 1920x1080 FLAC AAC]"), byteTemp);
//////				}
////				
////			//	i=i+byteTemp.length;
////					
////		//		byte[] out = bigCache.get(Optional.of("Shelter the Animation [eng][BDRip x264 1920x1080 FLAC AAC]"+"---"+i));
////				
////		//		FileOutputStream o = new FileOutputStream(new File("/Users/Felixerio/Desktop/aa"+i+".mkv"));
////		//		o.write(byteTemp);
////		//		o.flush();
////				
////				i++;
////				byteTemp = new byte[1024*1024*20];
////				
////				System.out.println("读入第 "+i+" 轮");
////				
////		
////			}
////			
////			
////			
////			System.out.println("Over");
//			TimeUnit.SECONDS.sleep(1000);
//		} catch (IOException | InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
