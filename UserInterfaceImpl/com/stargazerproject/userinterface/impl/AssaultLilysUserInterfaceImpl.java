package com.stargazerproject.userinterface.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.userinterface.extend.AssaultLilysUserInterface;
import com.stargazerproject.userinterface.extend.impl.BaseAssaultLilysUserInterfaceImpl;

@Component(value="assaultLilysUserInterface")
@Qualifier("assaultLilysUserInterface")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AssaultLilysUserInterfaceImpl extends BaseAssaultLilysUserInterfaceImpl implements StanderCharacteristicShell<AssaultLilysUserInterface>{

	@Override
	public void initialize(Optional<AssaultLilysUserInterface> assaultLilysUserInterfaceArg) {
		assaultLilysUserInterface = assaultLilysUserInterfaceArg.get();
	}

}
