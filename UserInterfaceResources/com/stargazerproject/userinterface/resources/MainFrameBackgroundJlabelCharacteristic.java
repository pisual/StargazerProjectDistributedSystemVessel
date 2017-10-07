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
import com.stargazerproject.characteristic.BaseCharacteristic;

/**
 *主界面背景
 * 
 *@author Felixerio
 */
@Component(value="mainFrameBackgroundJlabel")
@Qualifier("mainFrameBackgroundJlabel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameBackgroundJlabelCharacteristic extends GradientLoadInterface implements BaseCharacteristic<JLabel>{
	private static final long serialVersionUID = 4040037367225492924L;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	public MainFrameBackgroundJlabelCharacteristic() {}
	
	@Override
	@Bean(name="mainFrameBackgroundJlabelCharacteristic")
	@Lazy(true)
	public Optional<JLabel> characteristic() {
		try {
			initialization();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.of(this);
	}
	
	private void initialization() throws IOException {
		super.readImage(systemParameter.get(Optional.of("MAIN_INTERFACE_BACKGROUND")));
		this.setBounds(0, 0,image.getWidth(), image.getHeight());
	}
}
