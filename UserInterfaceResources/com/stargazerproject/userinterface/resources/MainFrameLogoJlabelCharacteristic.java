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
public class MainFrameLogoJlabelCharacteristic extends GradientLoadInterface implements BaseCharacteristic<JLabel>{
	private static final long serialVersionUID = 5010683231088848230L;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	public MainFrameLogoJlabelCharacteristic() {}
	
	@Override
	@Bean(name="mainFrameLogoJlabelCharacteristic")
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
		super.readImage(systemParameter.get(Optional.of("Main_Frame_Logo_Path")));
		initMainFrameLogoJlabel();
	}
	
	private void initMainFrameLogoJlabel(){
		int logoLocation[] = ParameterStringUtil.parameterTransToNormallArray(systemParameter.get(Optional.of("Main_Frame_LogoLocation")), Optional.of(","), Optional.of(4)).get();
		this.setBounds(logoLocation[0], logoLocation[1], logoLocation[2], logoLocation[3]);
	}

}
