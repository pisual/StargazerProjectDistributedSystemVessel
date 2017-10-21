package com.stargazerproject.sequence.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.base.impl.BaseSequence;

@Component(value="cellsGenerateSequence")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("cellsGenerateSequence")
public class CellsGenerateSequenceImpl extends BaseSequence implements StanderCharacteristicShell<Sequence>{

	@Override
	public void initialize(Optional<Sequence> sequenceArg) {
		sequence = sequenceArg.get();
	}

}
