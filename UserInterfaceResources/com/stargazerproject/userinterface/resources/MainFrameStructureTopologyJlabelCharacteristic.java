package com.stargazerproject.userinterface.resources;

import java.io.IOException;

import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.annotation.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.util.ParameterStringUtil;

/**
 *主界面拓扑图标
 *@author Felixerio
 **/
@Component(value="MainFrameStructureTopologyJlabelCharacteristic")
@Qualifier("MainFrameStructureTopologyJlabelCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameStructureTopologyJlabelCharacteristic implements BaseCharacteristic<JLabel>{
	
	/** @name 主界面拓扑图标 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Icon_TructureTopology;
	
	/** @name 主界面拓扑图标位置 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Icon_TructureTopology_Location;
	
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
		int structureTopologyJlabelLocation[] = ParameterStringUtil.segmentationArray(Optional.of(Kernel_UserInterface_MainFrame_Icon_TructureTopology_Location), Optional.of(","), Optional.of(4)).get();
		gradientLoadInterface = new GradientLoadInterface(Optional.of(Kernel_UserInterface_MainFrame_Icon_TructureTopology));
		gradientLoadInterface.setBounds(structureTopologyJlabelLocation[0], structureTopologyJlabelLocation[1], structureTopologyJlabelLocation[2], structureTopologyJlabelLocation[3]);
	}
}
