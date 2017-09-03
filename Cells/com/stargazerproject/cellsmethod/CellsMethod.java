package com.stargazerproject.cellsmethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import com.stargazerproject.order.impl.Parameter;
import com.stargazerproject.order.impl.ResultVoid;


public class CellsMethod {
	private static final CellsMethod cellsMethod;
	private static ClassLoader classLoader;
	private static LoadingCellsMethod loadingCellsMethod;

	static{
		cellsMethod = new CellsMethod();
		loadingCellsMethod = new LoadingCellsMethod();
	}
	
	private CellsMethod(){
	}
	
	public void loadingCellsMethod(String path){
		try {
			classLoader = loadingCellsMethod.loadingCellsMethodFromJar(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean startExecuteCellsMethod(Parameter parameter, ResultVoid result){
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
		return true;
	}
	
	public static final CellsMethod getInstance() {
		return cellsMethod;
	}
}
