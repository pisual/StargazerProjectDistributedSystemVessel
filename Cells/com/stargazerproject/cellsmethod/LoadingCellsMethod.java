package com.stargazerproject.cellsmethod;

import java.io.File;
import java.net.MalformedURLException;

import com.stargazerproject.classLoader.LoadingJarFile;

public class LoadingCellsMethod {
	private LoadingJarFile loadingJarFile;

	public LoadingCellsMethod() {
		loadingJarFile = new LoadingJarFile();
	}
	
	public ClassLoader loadingCellsMethodFromJar(String path) throws MalformedURLException{
		return (loadingJarFile.jarClassLoading(new File(path).toURI().toURL()));
	}
}
