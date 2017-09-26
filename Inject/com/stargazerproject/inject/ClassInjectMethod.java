package com.stargazerproject.inject;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.spring.container.BeanControl;

public interface ClassInjectMethod {
	public void SpringClassInject(Optional<List<Class<?>>> classList, Optional<BeanControl> beanControl);
}
