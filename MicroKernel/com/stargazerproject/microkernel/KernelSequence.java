package com.stargazerproject.microkernel;

import com.google.common.base.Optional;

public interface KernelSequence {
	
	public KernelSequenceTransaction addGroup(Optional<String> groupName);
	
	public void startSequence();

}
