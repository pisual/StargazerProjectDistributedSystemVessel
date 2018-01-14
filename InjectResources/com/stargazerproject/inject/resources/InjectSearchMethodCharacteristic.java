package com.stargazerproject.inject.resources;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import com.stargazerproject.annotation.AnnotationsScanner;
import com.stargazerproject.inject.InjectSearchMethod;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

@Component(value="injectSearchMethodCharacteristic")
@Qualifier("injectSearchMethodCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InjectSearchMethodCharacteristic implements InjectSearchMethod, BaseCharacteristic<InjectSearchMethod>{
	
	@Autowired
	@Qualifier("annotationsImpl")
	private AnnotationsScanner annotationsScanner;
	
	public InjectSearchMethodCharacteristic() {}
	
	@Override
	public Optional<InjectSearchMethod> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<List<Class<?>>> searchFromJar(Optional<String> absolutePath) throws IOException, ClassNotFoundException {
		List<Class<?>> classList = new ArrayList<Class<?>>();
		Optional<URLClassLoader> urlClassLoader = classLoader(fileUrl(absolutePath));
		injectClassList(jarEnumeration(readJarFile(absolutePath)), urlClassLoader, Optional.of(classList));
		closeClassLoader(urlClassLoader);
		return Optional.of(classList);
	}

	@Override
	public Optional<List<Class<?>>> searchAppointAnnotation(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws ClassNotFoundException, IOException {
		Multimap<Class<?>, Map.Entry<String, List<Object>>> classMap =  annotationsScanner.scannerAnnotation(packagesArg, annotationArg).get();
		List<Class<?>> classList = classMap.keys().stream().collect(Collectors.toList());
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
		URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { url.get() }, Thread.currentThread().getContextClassLoader());
		return Optional.of(urlClassLoader);
	}
	
	private Optional<String> classLoadPathChange(Optional<String> classPath){
		return Optional.of(classPath.get().replace('/', '.').substring(0, classPath.get().length() - 6));
	}
	
	private Optional<Class<?>> loadClass(Optional<String> loadClassPath, Optional<URLClassLoader> classLoader) throws ClassNotFoundException{
		return Optional.of(classLoader.get().loadClass(loadClassPath.get()));
	}
	
	private void injectClassList(Optional<Enumeration<?>> jarEnumeration, Optional<URLClassLoader> classLoader, Optional<List<Class<?>>> classList) throws IOException{
		try {
			while(jarEnumeration.get().hasMoreElements()){
				JarEntry jarEntry = (JarEntry) jarEnumeration.get().nextElement();
				if(classFileRecognize(Optional.of(jarEntry.getName()))){
					Class<?> classCell = loadClass(classLoadPathChange(Optional.of(jarEntry.getName())), classLoader).get();
					classList.get().add(classCell);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void closeClassLoader(Optional<URLClassLoader> urlClassLoader) throws IOException{
		urlClassLoader.get().close();
	}

}
