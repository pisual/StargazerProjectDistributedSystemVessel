package com.stargazerproject.microkernel;

import com.google.common.base.Optional;

public interface KernelSequenceTransaction{
	public KernelSequenceTransaction addModel(Optional<KernelSequenceModel> kernelModel);
	public KernelSequence next();
}
