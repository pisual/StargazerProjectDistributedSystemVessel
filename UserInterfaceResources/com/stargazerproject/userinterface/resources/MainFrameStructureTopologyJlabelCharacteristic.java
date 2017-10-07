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
 *@author Felixerio
 **/
@Component(value="MainFrameStructureTopologyJlabel")
@Qualifier("MainFrameStructureTopologyJlabel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameStructureTopologyJlabelCharacteristic extends GradientLoadInterface implements BaseCharacteristic<JLabel>{
	private static final long serialVersionUID = 5010683231088848230L;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	private MainFrameStructureTopologyJlabelCharacteristic() {}
	
	@Override
	@Bean(name="mainFrameStructureTopologyJlabelCharacteristic")
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
		super.readImage(systemParameter.get(Optional.of("StructureTopology.png")));
		initMainFrameLogoJlabel();
	}
	
	private void initMainFrameLogoJlabel(){
		int structureTopologyJlabelLocation[] = ParameterStringUtil.parameterTransToNormallArray(systemParameter.get(Optional.of("Main_Frame_StructureTopology_Location")), Optional.of(","), Optional.of(4)).get();
		this.setBounds(structureTopologyJlabelLocation[0], structureTopologyJlabelLocation[1], structureTopologyJlabelLocation[2], structureTopologyJlabelLocation[3]);
	}
}
