package com.stargazerproject.annotation.resources.shell;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import com.stargazerproject.annotation.Annotations;
import com.stargazerproject.annotation.AnnotationsScanner;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

@Component(value="annotationsShell")
@Qualifier("annotationsShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AnnotationsShell implements Annotations, BaseCharacteristic<Annotations>{

	@Autowired
	@Qualifier("annotationsScannerResourcesCharacteristic")
	private BaseCharacteristic<AnnotationsScanner> annotationsScannerResourcesCharacteristic;
	
	@Override
	public Optional<Annotations> characteristic() {
		return Optional.of(this);
	}

	@Override
	public Optional<Multimap<Class<?>, Entry<String, List<Object>>>> scannerAnnotation(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws IOException, ClassNotFoundException {
		return annotationsScannerResourcesCharacteristic.characteristic().get().scannerAnnotation(packagesArg, annotationArg);
	}

	@Override
	public Optional<List<String>> acquireAppointAnnotationAttributeValue(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg, Optional<String> value) throws IOException, ClassNotFoundException {
		return annotationsScannerResourcesCharacteristic.characteristic().get().acquireAppointAnnotationAttributeValue(packagesArg, annotationArg, value);
	}

}
