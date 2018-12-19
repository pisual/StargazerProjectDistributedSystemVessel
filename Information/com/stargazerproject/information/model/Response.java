package com.stargazerproject.information.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazerproject.transaction.base.impl.ID;
import com.stargazerproject.util.CloneUtil;

public class Response extends ID{

	private static final long serialVersionUID = 4188986274633558284L;
	
	private ResponseType responseType;
	
	public Response(Optional<ResponseType> responseTypeArg) {
		responseType = responseTypeArg.get();
	}
	 
	public Optional<ResponseType> responseType(){
		return Optional.of(responseType);
	}
	
	@Override
	public Response clone() throws CloneNotSupportedException {
		return (Response) CloneUtil.deepClone(Optional.of(this));
	}

	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("ID", super.sequenceID().get()).toString();
	}
	
}
