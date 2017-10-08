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
import com.stargazerproject.model.util.ParameterStringUtil;

/**
 *操控头像 
 **/
@Component(value="mainFrameLogoJlabel")
@Qualifier("mainFrameLogoJlabel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameLogoJlabelCharacteristic implements BaseCharacteristic<JLabel>{
	
	private GradientLoadInterface gradientLoadInterface;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	@Override
	@Bean(name="mainFrameLogoJlabelCharacteristic")
	@Lazy(true)
	public Optional<JLabel> characteristic() {
		try {
			gradientLoadInterface = new GradientLoadInterface(systemParameter.get(Optional.of("Main_Frame_Logo_Path")).get());
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
		int logoLocation[] = ParameterStringUtil.parameterTransToNormallArray(systemParameter.get(Optional.of("Main_Frame_LogoLocation")), Optional.of(","), Optional.of(4)).get();
		gradientLoadInterface.setBounds(logoLocation[0], logoLocation[1], logoLocation[2], logoLocation[3]);
	}

}
