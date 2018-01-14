package com.stargazerproject.inject;

import java.util.List;

import com.google.common.base.Optional;
import com.stargazerproject.spring.container.BeanControl;

public interface InjectClassMethod {
	
	public void injectBean(Optional<List<Class<?>>> classList, Optional<BeanControl> beanControl);
	
	public void removeInjectBean(Optional<List<String>> className, Optional<BeanControl> beanControl);
}
