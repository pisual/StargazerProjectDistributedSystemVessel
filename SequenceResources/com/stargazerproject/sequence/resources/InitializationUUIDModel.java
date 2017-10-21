package com.stargazerproject.sequence.resources;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.base.impl.BaseSequenceModel;
import com.stargazerproject.util.Sequence;

@Component(value="initializationUUIDModel")
@Qualifier("initializationUUIDModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InitializationUUIDModel extends BaseSequenceModel{

	public InitializationUUIDModel() {super();}
	
	@Override
	public Boolean method() {
		systemParameter.put(Optional.of("Cells_UUID"), Optional.of(Sequence.getUUID()));
		return Boolean.TRUE;
	}
	
}
