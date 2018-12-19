package com.stargazerproject.information.model;

import com.google.common.base.Optional;

public class TransmissionContent {
	
	private Object context;
	
	private String contextType;
	
	private TransmissionContent(Optional<Object> contextArg, Optional<String> contextTypeArg){
		context = contextArg.get();
		contextType = contextTypeArg.get();
	}

	/**
	 * @return the Optional.of(context)
	 */
	public Optional<Object> getContext() {
		return Optional.of(context);
	}

	/**
	 * @return the Optional.of(contextType)
	 */
	public Optional<String> getContextType() {
		return Optional.of(contextType);
	}
	
	

}
