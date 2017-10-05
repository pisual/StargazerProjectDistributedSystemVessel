package com.stargazerproject.userinterface.extend.impl;

import com.google.common.base.Optional;
import com.stargazerproject.userinterface.base.impl.BaseUserInterface;
import com.stargazerproject.userinterface.extend.AssaultLilysUserInterface;

public class BaseAssaultLilysUserInterfaceImpl extends BaseUserInterface implements AssaultLilysUserInterface{

	protected AssaultLilysUserInterface assaultLilysUserInterface;
	
	@Override
	public void visualRightTerminal() {
		assaultLilysUserInterface.visualRightTerminal();
	}

	@Override
	public void unvisualRightTerminal() {
		assaultLilysUserInterface.unvisualRightTerminal();
	}

	@Override
	public void addRightTerminalText(Optional<String> content) {
		assaultLilysUserInterface.addRightTerminalText(content);
	}

}
