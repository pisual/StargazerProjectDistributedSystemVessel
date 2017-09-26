package com.stargazerproject.inject.impl;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.inject.ClassInjectMethod;
import com.stargazerproject.spring.container.BeanControl;
import com.stargazerproject.spring.container.model.Scope;

public class ClassInjectMethodImpl implements ClassInjectMethod {

	@Override
	public void SpringClassInject(Optional<List<Class<?>>> classList, Optional<BeanControl> beanControl) {
		classList.get().stream().forEach(e -> beanControl.get().setBean(Optional.of(e.getName()), Optional.of(Scope.SCOPE_SINGLETON), e));
	}
	

}
