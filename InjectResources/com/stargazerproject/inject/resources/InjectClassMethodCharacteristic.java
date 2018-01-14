package com.stargazerproject.inject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.inject.InjectClassMethod;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.spring.container.BeanControl;

@Component(value="injectClassMethodCharacteristic")
@Qualifier("injectClassMethodCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InjectClassMethodCharacteristic implements InjectClassMethod, BaseCharacteristic<InjectClassMethod>{

	public InjectClassMethodCharacteristic() {}
	
	@Override
	public Optional<InjectClassMethod> characteristic() {
		return Optional.of(this);
	}

	@Override
	public void injectBean(Optional<List<Class<?>>> classList, Optional<BeanControl> beanControl) {
		classList.get().stream().forEach(e -> beanControl.get().setBean(Optional.of(e)));
	}

	@Override
	public void removeInjectBean(Optional<List<String>> className, Optional<BeanControl> beanControl) {
		className.get().forEach(x -> beanControl.get().removeBean(Optional.of(x)));
	}

}
