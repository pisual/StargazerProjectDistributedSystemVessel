package com.stargazerproject.annotation.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import com.stargazerproject.annotation.Annotations;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="annotationsImpl")
@Qualifier("annotationsImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AnnotationsImpl implements Annotations, StanderCharacteristicShell<Annotations>{

	private Annotations annotations;

	@Override
	public Optional<Multimap<Class<?>, Entry<String, List<Object>>>> scannerAnnotation(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws IOException, ClassNotFoundException {
		return annotations.scannerAnnotation(packagesArg, annotationArg);
	}
	
	@Override
	public Optional<List<String>> acquireAppointAnnotationAttributeValue(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg, Optional<String> value) throws IOException, ClassNotFoundException {
		return annotations.acquireAppointAnnotationAttributeValue(packagesArg, annotationArg, value);
	}

	@Override
	public void initialize(Optional<Annotations> annotationsArg) {
		annotations = annotationsArg.get();
	}

}
