package com.stargazerproject.microkernel.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.microkernel.base.impl.BaseKernelModel;
import com.stargazerproject.negotiate.Negotiate;

@Component(value="injectParameterModel")
@Qualifier("injectParameterModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InjectParameterModel extends BaseKernelModel{
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Optional<Negotiate> nodeNegotiate;

	public InjectParameterModel() {}

	@Override
	public Boolean KernelModelMethod() {
		
		initialization(Optional.of("InjectParameter"));
		
		try {
			nodeNegotiate.get().creatEphemeralNode(systemParameter.get(Optional.of("Cells_UUID")), Optional.of("/System/EdenCells/"), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Boolean result = waitResult(Optional.of("InjectParameter"), Optional.of(10), Optional.of(1));
		
		return result;
	}
	
}
