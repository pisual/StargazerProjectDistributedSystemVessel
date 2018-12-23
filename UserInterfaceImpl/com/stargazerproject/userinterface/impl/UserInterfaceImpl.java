package com.stargazerproject.userinterface.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.userinterface.UserInterface;
import com.stargazerproject.userinterface.base.impl.BaseUserInterface;

@Component(value="userInterfaceImpl")
@Qualifier("userInterfaceImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserInterfaceImpl extends BaseUserInterface implements StanderCharacteristicShell<UserInterface>{

	@Override
	public void initialize(Optional<UserInterface> baseUserInterfaceArg) {
		userInterface = baseUserInterfaceArg.get();
	}

}
