package com.stargazerproject.sequence;

import com.google.common.base.Optional;

public interface SequenceMethod {
	public Boolean method();
	public void waitMethod();
	public Optional<Boolean> getWaitMethod();
}
