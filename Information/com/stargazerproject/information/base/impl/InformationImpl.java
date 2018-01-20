package com.stargazerproject.information.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.information.Information;
import com.stargazerproject.information.model.Transmission;

public class InformationImpl implements Information{

	protected Information information;

	@Override
	public void start() {
		information.start();
	}

	@Override
	public void stop() {
		information.stop();
	}

	@Override
	public void push(Optional<Transmission> transmission) {
		information.push(transmission);
	}
}
