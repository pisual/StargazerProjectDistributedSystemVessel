package com.stargazerproject.userinterface.resources;

import java.io.IOException;

import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/**
 *加载进度页面背景
 *@author Felixerio
 */
@Component(value="loadingFrameBackgroundJlabelCharacteristic")
@Qualifier("loadingFrameBackgroundJlabelCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingFrameBackgroundJlabelCharacteristic implements BaseCharacteristic<JLabel>{
	
	/** @name 加载界面背景 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_LoadingFrame_Background;
	
	private GradientLoadInterface gradientLoadInterface;
	
	@Override
	public Optional<JLabel> characteristic() {
		try {
			gradientLoadInterface = new GradientLoadInterface(Optional.of(Kernel_UserInterface_LoadingFrame_Background));
			initialization();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.of(gradientLoadInterface);
	}
	
	private void initialization() throws IOException {
		gradientLoadInterface.setBounds(0, 0,gradientLoadInterface.image.getWidth(), gradientLoadInterface.image.getHeight());
	}
	
}
