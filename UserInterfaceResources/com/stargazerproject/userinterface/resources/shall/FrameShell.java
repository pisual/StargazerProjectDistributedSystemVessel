package com.stargazerproject.userinterface.resources.shall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.userinterface.LoadingUserInterface;
import com.stargazerproject.userinterface.MainUserInterface;
import com.stargazerproject.userinterface.UserInterface;

@Component(value="frameShell")
@Qualifier("frameShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FrameShell implements UserInterface, BaseCharacteristic<UserInterface>{

	@Autowired
	@Qualifier("loadingFrameShell")
	protected BaseCharacteristic<LoadingUserInterface> loadingUserInterfaceCharacteristic;
	
	private LoadingUserInterface loadingUserInterface;
	
	@Autowired
	@Qualifier("mainFrameShell")
	protected BaseCharacteristic<MainUserInterface> mainUserInterfaceCharacteristic;

	private MainUserInterface mainUserInterface;
	
	@Override
	public Optional<UserInterface> characteristic() {
		loadingUserInterface = loadingUserInterfaceCharacteristic.characteristic().get();
		mainUserInterface = mainUserInterfaceCharacteristic.characteristic().get();
		return Optional.of(this);
	}
	
	@Override
	public void startLoading() {
		loadingUserInterface.startLoading();
	}

	@Override
	public void increaseProgressBar(Optional<String> title, Optional<Integer> increasePercent) {
		loadingUserInterface.increaseProgressBar(title, increasePercent);
	}

	@Override
	public void endLoading() {
		loadingUserInterface.endLoading();
	}

	@Override
	public void startMain() {
		mainUserInterface.startMain();
	}

	@Override
	public void endMain() {
		mainUserInterface.endMain();
	}

	@Override
	public void visualLeftTerminal() {
		mainUserInterface.visualLeftTerminal();
	}

	@Override
	public void unvisualLeftTerminal() {
		mainUserInterface.unvisualLeftTerminal();
	}

	@Override
	public void addLeftTerminalText(Optional<String> content) {
		mainUserInterface.addLeftTerminalText(content);
	}

	@Override
	public void visualRightTerminal() {
		mainUserInterface.visualRightTerminal();
	}

	@Override
	public void unvisualRightTerminal() {
		mainUserInterface.unvisualRightTerminal();
	}

	@Override
	public void addRightTerminalText(Optional<String> content) {
		mainUserInterface.addRightTerminalText(content);
	}	
	
}
