package com.stargazerproject.cell.impl.test;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.context.ApplicationContext;

import com.google.common.base.Optional;
import com.netflix.hystrix.HystrixCommand;
import com.stargazerproject.cell.base.impl.CellsMethod;
import com.stargazerproject.spring.container.impl.BeanCellsContainer;
import com.stargazerproject.spring.context.impl.GlobalCellsAnnotationApplicationContext;

public class JarTest {

	static ApplicationContext applicationContexttask;

	private static final Schema<Class> CLASSS_SCHEMA = RuntimeSchema.getSchema(Class.class);

	public static void main(String[] args) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, IOException {

		ClassLoader classLoader;
		classLoader = new URLClassLoader(
				new URL[] { new File(
						"/Users/Felixerio/Desktop/StargazerProjectntelligenceSystemProtostuffBigcgModel.jar")
						.toURI().toURL() });

		Class<?> clazz = classLoader.loadClass("com.stargazerproject.cell.impl.QuickPassCellTest");

		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		LinkedBuffer BUFFER = LinkedBuffer.allocate();
		byte[] body = null;
		
		ProtobufIOUtil.writeTo(temp, clazz, CLASSS_SCHEMA, BUFFER);
		body = temp.toByteArray();
		
		
		byte[] result = null;
		
		Class<?> clazzResult = CLASSS_SCHEMA.newMessage();
		ProtobufIOUtil.mergeFrom(result, clazzResult, CLASSS_SCHEMA);
		
		
		CellsMethod hystrixCommandO = (CellsMethod) clazzResult.newInstance();

		GlobalCellsAnnotationApplicationContext.ApplicationContextInitialize(hystrixCommandO.getClass());

	    HystrixCommand hystrixCommand = BeanCellsContainer.instance().getBean(Optional.of("quickPassCellTest"),HystrixCommand.class);
		
	    hystrixCommand.execute();

	}

}
