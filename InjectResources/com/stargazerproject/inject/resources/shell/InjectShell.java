package com.stargazerproject.inject.resources.shell;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.inject.Inject;
import com.stargazerproject.inject.InjectClassMethod;
import com.stargazerproject.inject.InjectSearchMethod;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.spring.container.BeanControl;

@Component(value="injectShell")
@Qualifier("injectShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InjectShell implements Inject, BaseCharacteristic<Inject>{

	@Autowired
	@Qualifier("injectClassMethodCharacteristic")
	private BaseCharacteristic<InjectClassMethod> injectClassMethodCharacteristic;
	
	@Autowired
	@Qualifier("injectSearchMethodCharacteristic")
	private BaseCharacteristic<InjectSearchMethod> injectSearchMethodCharacteristic;
	
	private InjectShell() {}
	
	@Override
	public Optional<Inject> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public void injectBean(Optional<List<Class<?>>> classList, Optional<BeanControl> beanControl) {
		injectClassMethodCharacteristic.characteristic().get().injectBean(classList, beanControl);
	}

	@Override
	public void removeInjectBean(Optional<List<String>> className, Optional<BeanControl> beanControl) {
		injectClassMethodCharacteristic.characteristic().get().removeInjectBean(className, beanControl);
	}

	@Override
	public Optional<List<Class<?>>> searchFromJar(Optional<String> absolutePath) throws IOException, ClassNotFoundException {
		return injectSearchMethodCharacteristic.characteristic().get().searchFromJar(absolutePath);
	}

	@Override
	public Optional<List<Class<?>>> searchAppointAnnotation(Optional<String> packagesArg, Optional<Class<? extends Annotation>> annotationArg) throws ClassNotFoundException, IOException {
		return injectSearchMethodCharacteristic.characteristic().get().searchAppointAnnotation(packagesArg, annotationArg);
	}

}
