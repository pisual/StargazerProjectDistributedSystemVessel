package com.stargazerproject.information.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazerproject.order.Entity;
import com.stargazerproject.order.impl.ID;
import com.stargazerproject.util.Clone;

public class Response extends ID implements Entity<Response>{

	private ResponseType responseType;
	
	public Response(Optional<ResponseType> responseTypeArg) {
		responseType = responseTypeArg.get();
	}
	 
	public Optional<ResponseType> responseType(){
		return Optional.of(responseType);
	}
	
	@Override
	public Response clone() throws CloneNotSupportedException {
		return (Response) Clone.deepClone(Optional.of(this));
	}

	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("ID", super.IDSequence().get()).toString();
	}
	
}
