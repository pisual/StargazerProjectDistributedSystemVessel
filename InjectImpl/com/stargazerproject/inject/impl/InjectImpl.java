package com.stargazerproject.inject.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.inject.Inject;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.spring.container.BeanControl;

@Component(value="injectImpl")
@Qualifier("injectImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InjectImpl implements Inject, StanderCharacteristicShell<Inject>{

	private Inject inject;
	
	@Override
	public void injectBean(Optional<List<Class<?>>> classList, Optional<BeanControl> beanControl) {
		inject.injectBean(classList, beanControl);
	}

	@Override
	public void removeInjectBean(Optional<List<String>> className, Optional<BeanControl> beanControl) {
		inject.removeInjectBean(className, beanControl);
	}

	@Override
	public Optional<List<Class<?>>> searchFromJar(Optional<String> absolutePath) throws IOException, ClassNotFoundException {
		return inject.searchFromJar(absolutePath);
	}

	@Override
	public Optional<List<Class<?>>> searchAppointAnnotation(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg)  throws ClassNotFoundException, IOException {
		return inject.searchAppointAnnotation(packagesArg, annotationArg);
	}

	@Override
	public void initialize(Optional<Inject> injectArg) {
		inject = injectArg.get();
	}

}
