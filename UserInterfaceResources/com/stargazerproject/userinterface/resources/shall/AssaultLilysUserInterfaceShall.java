package com.stargazerproject.userinterface.resources.shall;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.userinterface.LoadingUserInterface;
import com.stargazerproject.userinterface.extend.AssaultLilysUserInterface;
import com.stargazerproject.userinterface.extend.MainFrameAssaultLilysUserInterface;

@Component(value="assaultLilysUserInterfaceShall")
@Qualifier("assaultLilysUserInterfaceShall")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AssaultLilysUserInterfaceShall implements AssaultLilysUserInterface, BaseCharacteristic<AssaultLilysUserInterface>{

	private Optional<MainFrameAssaultLilysUserInterface> mainFrameAssaultLilysUserInterface;
	private Optional<LoadingUserInterface> loadingUserInterface;
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="assaultLilysUserInterfaceShallCharacteristic")
	@Lazy(true)
	public Optional<AssaultLilysUserInterface> characteristic() {
		mainFrameAssaultLilysUserInterface = BeanContainer.instance().getBean(Optional.of("mainFrameShallCharacteristic"), Optional.class);
		loadingUserInterface = BeanContainer.instance().getBean(Optional.of("loadingFrameShallCharacteristic"), Optional.class);
		return Optional.of(this);
	}
	
	@Override
	public void visualRightTerminal() {
		mainFrameAssaultLilysUserInterface.get().visualRightTerminal();
	}

	@Override
	public void unvisualRightTerminal() {
		mainFrameAssaultLilysUserInterface.get().unvisualRightTerminal();
	}

	@Override
	public void addRightTerminalText(Optional<String> content) {
		mainFrameAssaultLilysUserInterface.get().addRightTerminalText(content);
	}

	@Override
	public void startMain() {
		mainFrameAssaultLilysUserInterface.get().startMain();
	}

	@Override
	public void endMain() {
		mainFrameAssaultLilysUserInterface.get().endMain();
	}

	@Override
	public void visualLeftTerminal() {
		mainFrameAssaultLilysUserInterface.get().unvisualLeftTerminal();
	}

	@Override
	public void unvisualLeftTerminal() {
		mainFrameAssaultLilysUserInterface.get().unvisualLeftTerminal();
	}

	@Override
	public void addLeftTerminalText(Optional<String> content) {
		mainFrameAssaultLilysUserInterface.get().addLeftTerminalText(content);
	}

	@Override
	public void startLoading() {
		loadingUserInterface.get().startLoading();
	}

	@Override
	public void increaseProgressBar(Optional<String> title, Optional<Integer> increasePercent) {
		loadingUserInterface.get().increaseProgressBar(title, increasePercent);
	}

	@Override
	public void endLoading() {
		loadingUserInterface.get().endLoading();
	}

}
