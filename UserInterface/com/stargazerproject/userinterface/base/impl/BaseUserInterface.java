package com.stargazerproject.userinterface.base.impl;

import com.google.common.base.Optional;
import com.stargazerproject.userinterface.UserInterface;

public class BaseUserInterface implements UserInterface{
	
	protected UserInterface userInterface;

	@Override
	public void startLoading() {
		userInterface.startLoading();
	}

	@Override
	public void increaseProgressBar(Optional<String> title, Optional<Integer> increasePercent) {
		userInterface.increaseProgressBar(title, increasePercent);
	}

	@Override
	public void endLoading() {
		userInterface.endLoading();
	}

	@Override
	public void startMain() {
		userInterface.startMain();
	}

	@Override
	public void endMain() {
		userInterface.endMain();
	}

	@Override
	public void visualLeftTerminal() {
		userInterface.visualLeftTerminal();
	}

	@Override
	public void unvisualLeftTerminal() {
		userInterface.unvisualLeftTerminal();
	}

	@Override
	public void addLeftTerminalText(Optional<String> content) {
		userInterface.addLeftTerminalText(content);
	}

	@Override
	public void visualRightTerminal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unvisualRightTerminal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRightTerminalText(Optional<String> content) {
		// TODO Auto-generated method stub
		
	}

}
