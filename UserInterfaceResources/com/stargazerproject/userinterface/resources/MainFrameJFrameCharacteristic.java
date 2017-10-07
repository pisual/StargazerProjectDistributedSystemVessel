package com.stargazerproject.userinterface.resources;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
 * 主要操作界面
 * 
 *@author Felixerio
 */
@Component(value="mainFrameJFrame")
@Qualifier("mainFrameJFrame")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameJFrameCharacteristic extends JFrame implements BaseCharacteristic<JFrame>{
	
	private static final long serialVersionUID = -2355092246173538052L;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;

	private MainFrameJFrameCharacteristic() {}
	
	@Override
	@Bean(name="mainFrameJFrameCharacteristic")
	@Lazy(true)
	public Optional<JFrame> characteristic() {
		initialization();
		return Optional.of(this);
	}

	private void initialization() {
		Integer FRAME_SIZE_WIDTH = Integer.parseInt(systemParameter.get(Optional.of("FRAME_SIZE_WIDTH")).get());
		Integer FRAME_SIZE_HIGTH = Integer.parseInt(systemParameter.get(Optional.of("FRAME_SIZE_HIGTH")).get());
		setSize(FRAME_SIZE_WIDTH,FRAME_SIZE_HIGTH);
		((JPanel) getContentPane()).setOpaque(Boolean.TRUE);
		setUndecorated(true);
	}
	
}
