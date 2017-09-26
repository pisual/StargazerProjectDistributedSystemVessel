package com.stargazerproject.inject.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.google.common.base.Optional;
import com.stargazerproject.inject.ClassSearchMethod;

public class ClassSearchMethodImpl implements ClassSearchMethod{
	
	private URLClassLoader urlClassLoader;
	private List<Class<?>> classList = new ArrayList<Class<?>>();
	
	public ClassSearchMethodImpl() {}
	
	@Override
	public Optional<List<Class<?>>> searchFromJar(Optional<String> absolutePath) throws IOException, ClassNotFoundException {
		injectClassList(jarEnumeration(readJarFile(absolutePath)), classLoader(fileUrl(absolutePath)));
		return Optional.of(classList);
	}
	
	private Optional<URL> fileUrl(Optional<String> absolutePath) throws MalformedURLException{
        return Optional.of(new URL("file:" + absolutePath.get()));
	}
	
	private Optional<JarFile> readJarFile(Optional<String> absolutePath) throws IOException{
		return Optional.of(new JarFile(absolutePath.get()));
	}
	
	private Optional<Enumeration<?>> jarEnumeration(Optional<JarFile> jarFile){
		return Optional.of(jarFile.get().entries());
	}
	
	private boolean classFileRecognize(Optional<String> classFilePath){
		return classFilePath.get().endsWith(".class");
	}
	
	private Optional<URLClassLoader> classLoader(Optional<URL> url){
		urlClassLoader = new URLClassLoader(new URL[] { url.get() }, Thread.currentThread().getContextClassLoader());
		return Optional.of(urlClassLoader);
	}
	
	private Optional<String> classLoadPathChange(Optional<String> classPath){
		return Optional.of(classPath.get().replace('/', '.').substring(0, classPath.get().length() - 6));
	}
	
	private Optional<Class<?>> loadClass(Optional<String> loadClassPath, Optional<URLClassLoader> classLoader) throws ClassNotFoundException{
		return Optional.of(classLoader.get().loadClass(loadClassPath.get()));
	}
	
	private void injectClassList(Optional<Enumeration<?>> jarEnumeration, Optional<URLClassLoader> classLoader) throws IOException{
		try {
			while(jarEnumeration.get().hasMoreElements()){
				JarEntry jarEntry = (JarEntry) jarEnumeration.get().nextElement();
				if(classFileRecognize(Optional.of(jarEntry.getName()))){
					Class<?> classCell = loadClass(classLoadPathChange(Optional.of(jarEntry.getName())), classLoader).get();
					classList.add(classCell);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			closeClassLoader();
		}
	}
	
	private void closeClassLoader() throws IOException{
		urlClassLoader.close();
	}

}
