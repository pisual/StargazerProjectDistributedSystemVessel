package com.stargazerproject.information.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.information.Information;
import com.stargazerproject.information.base.impl.InformationImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

@Component(value="cellsInformation")
@Qualifier("cellsInformation")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CellsInformation extends InformationImpl implements StanderCharacteristicShell<Information>{

	public CellsInformation() {}
	
	@Override
	public void initialize(Optional<Information> informationArg) {
		information = informationArg.get();
	}

}
