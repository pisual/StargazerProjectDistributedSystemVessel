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
import com.stargazerproject.util.ParameterStringUtil;

/**
 *主界面Logo 
 **/
@Component(value="mainFrameLogoJlabelCharacteristic")
@Qualifier("mainFrameLogoJlabelCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameLogoJlabelCharacteristic implements BaseCharacteristic<JLabel>{
	
	/** @name 主界面Logo **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Icon_Logo;
	
	/** @name 主界面Logo位置 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Icon_Logo_Location;
	
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
		initMainFrameLogoJlabel();
	}
	
	private void initMainFrameLogoJlabel(){
		int logoLocation[] = ParameterStringUtil.segmentationArray(Optional.of(Kernel_UserInterface_MainFrame_Icon_Logo), Optional.of(","), Optional.of(4)).get();
		gradientLoadInterface = new GradientLoadInterface(Optional.of(Kernel_UserInterface_MainFrame_Icon_Logo));
		gradientLoadInterface.setBounds(logoLocation[0], logoLocation[1], logoLocation[2], logoLocation[3]);
	}

}
