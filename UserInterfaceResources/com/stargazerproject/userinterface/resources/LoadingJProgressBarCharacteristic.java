package com.stargazerproject.userinterface.resources;

import javax.swing.JProgressBar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.Characteristic;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/**
 * 加载界面进度条
 *@author Felixerio
 */
@Component(value="loadingJProgressBarCharacteristic")
@Qualifier("loadingJProgressBarCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingJProgressBarCharacteristic extends JProgressBar implements BaseCharacteristic<JProgressBar>{
	
	private static final long serialVersionUID = 70148292084859317L;
	
	/**加载界面进度条UI**/
	@Autowired
	@Qualifier("loadingJProgressBarUI")
	private LoadingJProgressBarUI loadingJProgressBarUI;
	
	@Autowired
	@Qualifier("characteristicInitialization")
	private Characteristic characteristic;

	public LoadingJProgressBarCharacteristic() {}
	
	@Override
	public Optional<JProgressBar> characteristic() {
		synchronized(this){
			if(characteristic.singleInitializationStata().get() == Boolean.FALSE){
				initLoadingJProgressBar();
				characteristic.singleInitializationComplete();
			}
		}
		return Optional.of(this);
	}
	
	private void initLoadingJProgressBar(){
		loadingJProgressBarUI.loadingJProgressBarUIInit(this);
		this.setUI(loadingJProgressBarUI);
		this.setBounds(100, 400, 1000, 2);
	}
	
}
