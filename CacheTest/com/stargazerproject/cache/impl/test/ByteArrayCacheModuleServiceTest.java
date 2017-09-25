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
import com.stargazerproject.service.ServiceControl;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.spring.context.initialization.test.GlobalAnnotationApplicationContextInitialization;

@FixMethodOrder(MethodSorters.JVM)
public class ByteArrayCacheModuleServiceTest{
	
	public static BigCache<String, byte[]> cache;
	
	public static String fileMD5;
	
	public static int fileBufferSize = 1024*8*1024*64;
	
	@Rule  
	public ExpectedException expectedException = ExpectedException.none();  

	@Test
	public void SpringInit(){
		String args[] = {"debug"};
		GlobalAnnotationApplicationContextInitialization.ApplicationContextInitialize(args);
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
	        throw new NullPointerException("Md5 is Null");
	    }
	}
	
	public String byteArrayMd5(byte[] byteArray){
		return DigestUtils.md5Hex(byteArray);
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
	
	@Test
	public void cacheBitchPutTest(){
		ArrayList<byte[]> list = getByteAll("/Users/Felixerio/Desktop/testFile.mkv");
		int listSize = list.size();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100000; i++) {
					for (int j = 0; j < listSize; j++) {
						cache.add(Optional.of("TestKeyBitch"+i), list.get(0));
					}
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1001; i < 2000; i++) {
					for (int j = 0; j < listSize; j++) {
						cache.add(Optional.of("TestKeyBitch"+i), list.get(0));
					}
				}
			}
		});
		
		Thread thread3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 3001; i < 3000; i++) {
					for (int j = 0; j < listSize; j++) {
						cache.add(Optional.of("TestKeyBitch"+i), list.get(0));
					}
				}
			}
		});
		
		Thread thread4 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 4001; i < 5000; i++) {
					for (int j = 0; j < listSize; j++) {
						cache.add(Optional.of("TestKeyBitch"+i), list.get(0));
					}
				}
			}
		});
		
		Thread thread5 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 5001; i < 6000; i++) {
					for (int j = 0; j < listSize; j++) {
						cache.add(Optional.of("TestKeyBitch"+i), list.get(0));
					}
				}
			}
		});
		
		try {
			
			thread.start();
			thread2.start();
			thread3.start();
			thread4.start();
			thread5.start();
			
			thread.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("5000级测试Put完毕");
	}
	
	@Test
	public void cacheBitchGetTest(){
		for (int i = 0; i < 100; i++) {
			cache.get(Optional.of("TestKeyBitch" + i));
		}
		System.out.println("百级测试Get完毕");
	}
	
	@Test
	public void serviceStopTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.stopAllService();
	}
	
	@Test
	public void serviceStopLaterGetTest(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Server Not Start");  
		cache.get(Optional.of("TestKey"));
	}
	
	@Test
	public void serviceStopLaterRemoveTest(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Server Not Start");  
		cache.remove(Optional.of("TestKey"));
	}
	
//	@Test
//	public void serviceStopLaterPutTest(){
//		expectedException.equals(IllegalStateException.class);
//		expectedException.expectMessage("Server Not Start");  
//		cache.put(Optional.of("TestKey"), Optional.of(1));
//	}

	@Test
	public void serviceStartAgainTest(){
		expectedException.equals(IllegalStateException.class);
		expectedException.expectMessage("Service LocalLogServerManage [TERMINATED] is TERMINATED, cannot start it.");  
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.startAllservice();
	}
	
	@Test
	public void serviceStopAgainTest(){
		ServiceControl serviceControl = BeanContainer.instance().getBean(Optional.of("moduleService"),ServiceControl.class);
		serviceControl.stopAllService();
	}
	
}
