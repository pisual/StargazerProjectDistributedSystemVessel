package com.stargazerproject.information;

import com.google.common.base.Optional;
import com.stargazerproject.information.model.Transmission;

public interface InformationMethod {

	public void push(Optional<Transmission> transmission);
	
}
