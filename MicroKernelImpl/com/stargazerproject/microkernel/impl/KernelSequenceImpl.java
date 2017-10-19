package com.stargazerproject.microkernel.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.microkernel.KernelSequenceModel;
import com.stargazerproject.microkernel.KernelSequence;

@Component(value="kernelSequenceImpl")
@Qualifier("kernelSequenceImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KernelSequenceImpl implements KernelSequence{
	
	private Multimap<String, KernelSequenceModel> scoreMultimap = LinkedHashMultimap.create(); 
	
	public KernelSequenceImpl() {}
	
	@Override
	public KernelSequenceTransactionImpl addGroup(Optional<String> groupName) {
		return new KernelSequenceTransactionImpl(groupName, Optional.of(this), Optional.of(scoreMultimap));
	}

	@Override
	public void startSequence() {
		System.out.println(scoreMultimap.toString());
	}
	
}