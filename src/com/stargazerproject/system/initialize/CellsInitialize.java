package com.stargazerproject.system.initialize;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.stargazerproject.cellsmethod.CellsMethod;
import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.order.impl.Parameter;
import com.stargazerproject.order.impl.ResultVoid;
import com.stargazerproject.parameter.impl.SystemParameter;

public class CellsInitialize {
	private static CellsInitialize cellsInitialize;
	private static String Operation_Level_Instruction_Model_Loading;

	public static final CellsInitialize getInstance() {
		if (cellsInitialize == null) {
			Operation_Level_Instruction_Model_Loading = SystemParameterCahce.getInstance().getString("Operation_Level_Instruction_Model_Loading");
			cellsInitialize = new CellsInitialize();
			cellsInitialize.initialize();
		}
		return cellsInitialize;
	}
	
	public void initialize() {
		LocalityLog.getInstance().INFO(CacheInitialize.class, "CellsInitialize Initialize");
		CellsMethod.getInstance().loadingCellsMethod(Operation_Level_Instruction_Model_Loading);
	}
	
	private CellsInitialize() {
	}
	
	public static void main(String[] args) {
		CellsInitialize.getInstance();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			Class<?> clazz=classLoader.loadClass("com.stargazerproject.model.bigcg.test.TestMethod");
			Method taskMethod = clazz.getMethod("testMethod", Parameter.class, ResultVoid.class);
			taskMethod.invoke(clazz.newInstance(),
					new Parameter().setParameters("Test", "StargazerModelTest")
				   ,new ResultVoid());  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		
	}
}
