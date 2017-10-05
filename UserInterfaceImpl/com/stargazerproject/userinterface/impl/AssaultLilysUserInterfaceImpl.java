package com.stargazerproject.userinterface.impl;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.userinterface.extend.AssaultLilysUserInterface;
import com.stargazerproject.userinterface.extend.impl.BaseAssaultLilysUserInterfaceImpl;

public class AssaultLilysUserInterfaceImpl extends BaseAssaultLilysUserInterfaceImpl implements StanderCharacteristicShell<AssaultLilysUserInterface>{

	@Override
	public void initialize(Optional<AssaultLilysUserInterface> assaultLilysUserInterfaceArg) {
		assaultLilysUserInterface = assaultLilysUserInterfaceArg.get();
	}

}
