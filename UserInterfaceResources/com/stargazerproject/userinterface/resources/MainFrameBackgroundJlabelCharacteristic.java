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
 *主界面背景
 * 
 *@author Felixerio
 */
@Component(value="mainFrameBackgroundJlabelCharacteristic")
@Qualifier("mainFrameBackgroundJlabelCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameBackgroundJlabelCharacteristic implements BaseCharacteristic<JLabel>{
	
	/** @name 主界面背景 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Background;
	
	private GradientLoadInterface gradientLoadInterface;
	
	@Override
	public Optional<JLabel> characteristic() {
		try {
			initialization();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.of(gradientLoadInterface);
	}
	
	private void initialization() throws IOException {
		gradientLoadInterface = new GradientLoadInterface(Optional.of(Kernel_UserInterface_MainFrame_Background));
		gradientLoadInterface.setBounds(0, 0,gradientLoadInterface.image.getWidth(), gradientLoadInterface.image.getHeight());
		gradientLoadInterface.setOpaque(true);
	}
}
