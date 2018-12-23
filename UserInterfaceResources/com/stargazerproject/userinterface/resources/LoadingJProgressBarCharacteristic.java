package com.stargazerproject.userinterface.resources;

import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristics.Characteristic;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/**
 * 加载界面进度条
 *@author Felixerio
 */
@Component(value="loadingJProgressBarCharacteristic")
@Qualifier("loadingJProgressBarCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingJProgressBarCharacteristic implements BaseCharacteristic<JProgressBar>{
	
	/**加载界面进度条UI**/
	@Autowired
	@Qualifier("loadingJProgressBarUI")
	private BaseCharacteristic<LoadingJProgressBarUI> loadingJProgressBarUICharacteristic;
	
	private LoadingJProgressBarUI loadingJProgressBarUI;
	
	private JProgressBar jProgressBar = new JProgressBar();
	
	private Boolean init = Boolean.FALSE;

	public LoadingJProgressBarCharacteristic() {}
	
	@Override
	public Optional<JProgressBar> characteristic() {
		synchronized(init){
			if(init == Boolean.FALSE){
				loadingJProgressBarUI = loadingJProgressBarUICharacteristic.characteristic().get();
				initLoadingJProgressBar();
				init = Boolean.TRUE;
			}
		}	
		return Optional.of(jProgressBar);
	}
	
	private void initLoadingJProgressBar(){
		loadingJProgressBarUI.loadingJProgressBarUIInit(jProgressBar);
		jProgressBar.setUI(loadingJProgressBarUI);
		jProgressBar.setBounds(100, 400, 1000, 2);
	}
	
}
