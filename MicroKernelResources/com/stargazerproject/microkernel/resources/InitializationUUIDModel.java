package com.stargazerproject.microkernel.resources;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.microkernel.base.impl.BaseKernelModel;
import com.stargazerproject.util.Sequence;

@Component(value="injectParameterModel")
@Qualifier("injectParameterModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InitializationUUIDModel extends BaseKernelModel{

	public InitializationUUIDModel() {}

	@Override
	public Boolean KernelModelMethod() {
		systemParameter.put(Optional.of("Cells_UUID"), Optional.of(Sequence.getUUID()));
		return Boolean.TRUE;
	}
	
}
