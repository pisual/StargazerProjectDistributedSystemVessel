package com.stargazerproject.userinterface.resources;

import javax.swing.JProgressBar;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;

/**
 * 加载界面进度条
 *@author Felixerio
 */
@Component(value="loadingJProgressBar")
@Qualifier("loadingJProgressBar")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingJProgressBarCharacteristic extends JProgressBar implements BaseCharacteristic<JProgressBar>{
	private static final long serialVersionUID = 1L;
	
	public LoadingJProgressBarCharacteristic() {}
	
	@Override
	@Bean(name="loadingJProgressBarCharacteristic")
	@Lazy(true)
	public Optional<JProgressBar> characteristic() {
		initLoadingJProgressBar();
		return Optional.of(this);
	}
	
	private void initLoadingJProgressBar(){
		this.setUI(new LoadingJProgressBarUI(this));
		this.setBounds(100, 400, 1000, 2);
	}
	
}
