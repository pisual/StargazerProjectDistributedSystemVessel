package com.stargazerproject.userinterface.resources;

import java.io.IOException;

import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/**
 *加载进度页面背景
 *@author Felixerio
 */
@Component(value="loadingFrameBackgroundJlabel")
@Qualifier("loadingFrameBackgroundJlabel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingFrameBackgroundJlabelCharacteristic implements BaseCharacteristic<JLabel>{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	private GradientLoadInterface gradientLoadInterface;
	
	@Override
	@Bean(name="loadingFrameBackgroundJlabelCharacteristic")
	@Lazy(true)
	public Optional<JLabel> characteristic() {
		try {
			gradientLoadInterface = new GradientLoadInterface(systemParameter.get(Optional.of("LOADING_INTERFACE_BACKGROUND")).get());
			initialization();
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
