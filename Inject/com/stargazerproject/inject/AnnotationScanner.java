package com.stargazerproject.inject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;

public interface AnnotationScanner {

	public Multimap<Class<?>, Map.Entry<String, List<Object>>> getClassAnnotationContent(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws IOException, ClassNotFoundException ;
	
}
