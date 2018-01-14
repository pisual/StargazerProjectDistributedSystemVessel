package com.stargazerproject.inject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import com.google.common.base.Optional;

public interface InjectSearchMethod {
	
	public Optional<List<Class<?>>> searchFromJar(Optional<String> absolutePath) throws IOException, ClassNotFoundException;
	
	public Optional<List<Class<?>>> searchAppointAnnotation(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg)  throws ClassNotFoundException, IOException ;
	
}
