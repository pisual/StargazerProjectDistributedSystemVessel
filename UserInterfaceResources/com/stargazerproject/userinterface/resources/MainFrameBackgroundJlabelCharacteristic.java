package com.stargazerproject.userinterface.resources;

import java.io.IOException;

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
 *主界面背景
 * 
 *@author Felixerio
 */
@Component(value="mainFrameBackgroundJlabel")
@Qualifier("mainFrameBackgroundJlabel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameBackgroundJlabelCharacteristic implements BaseCharacteristic<GradientLoadInterface>{
	
	private GradientLoadInterface gradientLoadInterface;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	@Override
	@Bean(name="mainFrameBackgroundJlabelCharacteristic")
	@Lazy(true)
	public Optional<GradientLoadInterface> characteristic() {
		try {
			gradientLoadInterface = new GradientLoadInterface(systemParameter.get(Optional.of("MAIN_INTERFACE_BACKGROUND")).get());
			initialization();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.of(gradientLoadInterface);
	}
	
	private void initialization() throws IOException {
		gradientLoadInterface.setBounds(0, 0,gradientLoadInterface.image.getWidth(), gradientLoadInterface.image.getHeight());
		gradientLoadInterface.setOpaque(true);
	}
}
