package com.stargazerproject.spring.container;

import com.google.common.base.Optional;
import com.stargazerproject.spring.container.model.Scope;

public interface BeanControl {
	public <T> T getBean(Optional<String> className, Class<T> t);
	public void  setBean(Optional<String> className, Optional<Scope> scope, Class<?> ClassArg);
	public void  removeBean(Optional<String> className);
}
