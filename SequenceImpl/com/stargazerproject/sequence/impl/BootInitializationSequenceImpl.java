package com.stargazerproject.sequence.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.base.impl.BaseSequence;
import com.stargazerproject.transaction.Transaction;

@Component(value="bootInitializationSequence")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("bootInitializationSequence")
public class BootInitializationSequenceImpl extends BaseSequence<Transaction> implements StanderCharacteristicShell<Sequence<Transaction>>{

	@Override
	public void initialize(Optional<Sequence<Transaction>> sequenceArg) {
		sequence = sequenceArg.get();
	}

}
