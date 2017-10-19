package com.stargazerproject.microkernel.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import com.stargazerproject.microkernel.KernelSequence;
import com.stargazerproject.microkernel.KernelSequenceModel;
import com.stargazerproject.microkernel.KernelSequenceTransaction;

@Component(value="kernelSequenceTransactionImpl")
@Qualifier("kernelSequenceTransactionImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KernelSequenceTransactionImpl implements KernelSequenceTransaction {

	private String groupName;
	private KernelSequence kernelSequence;
	private Multimap<String, KernelSequenceModel> scoreMultimap;
	 
	public KernelSequenceTransactionImpl(Optional<String> groupNameArg, Optional<KernelSequence> kernelSequenceArg, Optional<Multimap<String, KernelSequenceModel>> scoreMultimapArg) {
		groupName = groupNameArg.get();
		scoreMultimap = scoreMultimapArg.get();
		kernelSequence = kernelSequenceArg.get();
	}

	@Override
	public KernelSequenceTransaction addModel(Optional<KernelSequenceModel> kernelModel) {
		scoreMultimap.put(groupName, kernelModel.get());
		return this;
	}

	@Override
	public KernelSequence next() {
		return kernelSequence;
	}

}
