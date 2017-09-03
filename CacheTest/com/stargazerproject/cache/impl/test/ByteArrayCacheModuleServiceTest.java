package com.stargazerproject.cache.impl.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.cache.aop.configuration.BigCacheIndexCacheAOPConfiguration;
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
import com.stargazerproject.log.configuration.GroupLogConfiguration;
import com.stargazerproject.queue.impl.EventQueue;
import com.stargazerproject.queue.impl.LogQueue;
import com.stargazerproject.queue.impl.resources.shell.EventDisruptorShell;
import com.stargazerproject.queue.impl.resources.shell.LogDisruptorShell;
import com.stargazerproject.queue.resources.impl.EventFactory;
import com.stargazerproject.queue.resources.impl.EventHandler;
import com.stargazerproject.queue.resources.impl.EventQueueThreadFactory;
import com.stargazerproject.queue.resources.impl.LogEventFactory;
import com.stargazerproject.queue.resources.impl.LogHandler;
import com.stargazerproject.queue.resources.impl.LogQueueThreadFactory;
import com.stargazerproject.queue.server.impl.EventQueueServer;
import com.stargazerproject.queue.server.impl.LogQueueServer;
import com.stargazerproject.queue.server.listener.impl.EventQueueServerListener;
import com.stargazerproject.queue.server.listener.impl.LogQueueServerListener;
import com.stargazerproject.queue.server.manage.EventQueueServerManage;
import com.stargazerproject.queue.server.manage.LogQueueServerManage;
import com.stargazerproject.resources.parameter.StargazerProjectParameterList;
import com.stargazerproject.resources.service.ServiceParameterList;
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.service.configuration.GroupServiceConfiguration;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.impl.GlobalAnnotationApplicationContext;

@FixMethodOrder(MethodSorters.JVM)
public class ByteArrayCacheModuleServiceTest{
	
	public static BigCache<String, byte[]> cache;
	
	public static String fileMD5;
	
	public static int fileBufferSize = 1024*8*1024*64;
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none();  

	static{
		GlobalAnnotationApplicationContext.ApplicationContextInitialize(
				
				/**Itself Configuration Class**/
				ByteArrayCache.class,
				ByteArrayCacheCacheConfigurationCharacteristic.class,
				ByteArrayCacheCacheManagerCharacteristic.class,
				ByteArrayCacheConfigurationCharacteristic.class,
				ByteArrayCacheShell.class,
				ByteArrayCacheServer.class,
				ByteArrayCacheServerListener.class,
				ByteArrayCacheServerManage.class,
				
		     /******Depend Configuration Class******/
				/**Depend BigCacheIndexCahce**/
				BigCacheIndexCahce.class,
				BigCacheIndexCahceCharacteristic.class,
				BigCacheIndexCahceShell.class,
				BigCacheIndexCacheBuiltInCacheServer.class,
				BigCacheIndexCacheServerListener.class,
				BigCacheIndexCacheServerManage.class,
				
				/**Depend SystemParameterCahce**/
				SystemParameterCahce.class,
				SystemParameterCahceCharacteristic.class,
				SystemParameterCahceShell.class,
				SystemParameterBuiltInCacheServer.class,
				SystemParameterCacheServerListener.class,
				SystemParameterCacheServerManage.class,
				
				/**Depend EventQueue**/
				EventQueue.class,
				EventDisruptorShell.class,
				EventFactory.class,
				EventHandler.class,
				EventQueueThreadFactory.class,
				EventQueueServer.class,
				EventQueueServerListener.class,
				EventQueueServerManage.class,
				
				/**Depend LogCache**/
				LogQueue.class,
				LogDisruptorShell.class,
				LogEventFactory.class,
				LogHandler.class,
				LogQueueThreadFactory.class,
				LogQueueServer.class,
				LogQueueServerListener.class,
				LogQueueServerManage.class,
				
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
				BigCacheIndexCacheAOPConfiguration.class,
				
				/**Depend Resources**/
				StargazerProjectParameterList.class,
				ServiceParameterList.class,
				
				/**Depend Log**/
				GroupLogConfiguration.class,
				
				/**Depend Service**/
				GroupServiceConfiguration.class
				);
	}
	
	
	public ArrayList<byte[]> getByteAll(String file){
		FileInputStream fileInputStream  = null;
		ArrayList<byte[]> list = new ArrayList<byte[]>();
		try {
			fileInputStream = new FileInputStream(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] tempByte = new byte[fileBufferSize];
		try {
			int i=0;
			int readByteLenght = 0;
			while((readByteLenght = fileInputStream.read(tempByte))!=-1){
				if(readByteLenght <fileBufferSize){
					byte[] endFileArray = new byte[readByteLenght];
					System.arraycopy(tempByte, 0, endFileArray, 0, readByteLenght); //5个参数  原数组 起始index  数组2   起始index  拷贝长度
					list.add(endFileArray);
					break;
				}
				else{
					list.add(tempByte);
					tempByte = new byte[fileBufferSize];
					i++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public String fileToMD5(String path){
	    try {
	        FileInputStream fis = new FileInputStream(path);
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	        byte[] buffer = new byte[fileBufferSize];
	        int len;
	        while ((len = fis.read(buffer)) != -1) {
	            digest.update(buffer, 0, len);
	        }
	        fis.close();
	        BigInteger bigInt = new BigInteger(1, digest.digest());
	        return  bigInt.toString(16);
	    } catch (IOException | NoSuchAlgorithmException e){
	        e.printStackTrace();
	    }
	    return "";
	}
	
	public String byteArrayMd5(byte[] byteArray){
		String md5 = "md5";
		md5 = DigestUtils.md5Hex(byteArray); 
		return md5;
	}
	
	@Test
	public void serviceStartTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}

	@Test
	public void getCacheTest(){
		cache = BeanContainer.instance().getBean(Optional.of("byteArrayCache"), BigCache.class);
	}
	
	@Test(timeout=100)
	public void cachePutTest(){
		byte[] tempByte = new byte[1024*8*1024];
		tempByte = "test".getBytes();
		cache.add(Optional.of("TestKey"), tempByte);
	}
	
	@Test
	public void cacheGetTest(){
		byte[] getByteArray = cache.get(Optional.of("TestKey")).get();
		String getByteArrayMD5 = byteArrayMd5(getByteArray);
		String StringMD5 = byteArrayMd5("test".getBytes());
		System.out.println("Get测试 getByteArrayMD5 : " + getByteArrayMD5 + " StringMD5 : " + StringMD5);
		assertEquals(getByteArrayMD5, StringMD5);
	}
	
	@Test
	public void cacheRemoveTest(){
		cache.remove(Optional.of("TestKey"));
	}
	
	@Test
	public void cacheBigFilePutTest(){
		ArrayList<byte[]> list = getByteAll("/Users/Felixerio/Desktop/testFile.mkv");
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			cache.add(Optional.of("TestKey2"), list.get(i));
		}
		System.out.println("大文件 Put 完毕");
	}
	
	@Test
	public void getFileMD5(){
		fileMD5 = fileToMD5("/Users/Felixerio/Desktop/testFile.mkv");
	}
	
	@Test
	public void cacheBigFileGetTest() throws UnsupportedEncodingException{
		byte[] getByteArray = cache.get(Optional.of("TestKey2")).get();
		String getByteArrayMD5 = byteArrayMd5(getByteArray);
		System.out.println("Get测试 getByteArrayMD5 : " + getByteArrayMD5 + " fileMD5 : " + fileMD5);
		assertEquals(getByteArrayMD5, fileMD5);
		System.out.println("大文件 Get 完毕");
	}
	
	@Test(expected = NullPointerException.class)
	public void cacheRemoveLatersTest(){
		cache.get(Optional.of("TestKey"));
	}
	
	
	
//	@Test(timeout=10000)
//	public void cacheBitchPutTest(){
//		for (int i = 0; i < 1000000; i++) {
//			cache.put(Optional.of("TestKey" + i), Optional.of(i));
//		}
//		System.out.println("百万级测试Put完毕");
//	}
//	
//	@Test(timeout=10000)
//	public void cacheBitchGetTest(){
//		for (int i = 0; i < 1000000; i++) {
//			cache.get(Optional.of("TestKey" + i));
//		}
//		System.out.println("百万级测试Get完毕");
//	}
//	
//	@Test
//	public void serviceStopTest(){
//		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
//		serviceControl.stopAllService();
//	}
//	
//	@Test
//	public void serviceStopLaterGetTest(){
//		expectedException.equals(IllegalStateException.class);
//		expectedException.expectMessage("Server Not Start");  
//		cache.get(Optional.of("TestKey"));
//	}
//	
//	@Test
//	public void serviceStopLaterRemoveTest(){
//		expectedException.equals(IllegalStateException.class);
//		expectedException.expectMessage("Server Not Start");  
//		cache.remove(Optional.of("TestKey"));
//	}
//	
//	@Test
//	public void serviceStopLaterPutTest(){
//		expectedException.equals(IllegalStateException.class);
//		expectedException.expectMessage("Server Not Start");  
//		cache.put(Optional.of("TestKey"), Optional.of(1));
//	}
//
//	@Test
//	public void serviceStartAgainTest(){
//		expectedException.equals(IllegalStateException.class);
//		expectedException.expectMessage("Service LocalLogServerManage [TERMINATED] is TERMINATED, cannot start it.");  
//		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
//		serviceControl.startAllservice();
//	}
//	
//	@Test
//	public void serviceStopAgainTest(){
//		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
//		serviceControl.stopAllService();
//	}
	
}
