package com.stargazerproject.sequence.resources.shell;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceMethod;
import com.stargazerproject.sequence.SequenceTransaction;

@Component(value="sequenceResourcesShell")
@Qualifier("sequenceResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SequenceResourcesShell implements Sequence, BaseCharacteristic<Sequence>{
	
	private Multimap<String, SequenceMethod> scoreMultimap = LinkedHashMultimap.create(); 
	
	public SequenceResourcesShell() {}
	
	@Override
	@Bean(name="sequenceResourcesCharacteristic")
	@Lazy(true)
	public Optional<Sequence> characteristic() {
		return Optional.of(this);
	}

	@Override
	public SequenceTransaction addModel(Optional<SequenceMethod> sequenceMethod) {
		scoreMultimap.put("sequenceMethod.get().getClass().getSimpleName()", sequenceMethod.get());
		return this;
	}

	@Override
	public void startSequence() {
		scoreMultimap.entries().stream().forEach(e -> e.getValue().method());
	}
	
}