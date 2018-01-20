package com.stargazerproject.information.resources.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.information.Information;
import com.stargazerproject.information.InformationControl;
import com.stargazerproject.information.InformationMethod;
import com.stargazerproject.information.model.Transmission;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

@Component(value="cellsInformationShell")
@Qualifier("cellsInformationShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CellsInformationShell implements Information, BaseCharacteristic<Information>{

	@Autowired
	@Qualifier("informationControlCharacteristic")
	private BaseCharacteristic<InformationControl> informationControlCharacteristic;
	
	@Autowired
	@Qualifier("informationMethodCharacteristic")
	private BaseCharacteristic<InformationMethod> informationMethodCharacteristic;
	
	@Override
	public Optional<Information> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public void start() {
		informationControlCharacteristic.characteristic().get().start();
	}

	@Override
	public void stop() {
		informationControlCharacteristic.characteristic().get().stop();
	}

	@Override
	public void push(Optional<Transmission> transmission) {
		informationMethodCharacteristic.characteristic().get().push(transmission);
	}

}
