package com.stargazerproject.userinterface.resources;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.Characteristic;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;


/**
 *主界面控制台滚动条
 * 
 *@author Felixerio
 */
@Component(value="mainFrameJScrollPaneCharacteristic")
@Qualifier("mainFrameJScrollPaneCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameJScrollPaneCharacteristic implements BaseCharacteristic<JScrollPane> {
	
	@Autowired
	@Qualifier("mainFrameConsoleTextPaneCharacteristic")
	private BaseCharacteristic<JTextPane> mainFrameConsoleTextPaneCharacteristic;
	
	@Autowired
	@Qualifier("characteristicInitialization")
	private Characteristic characteristic;
	
	private JScrollPane jScrollPane;
	
	@Override
	public Optional<JScrollPane> characteristic() {
		synchronized(this){
			if(characteristic.singleInitializationStata().get() == Boolean.FALSE){
				initialization();
				characteristic.singleInitializationComplete();
			}
		}
		return Optional.of(jScrollPane);
	}
	
	public void initialization(){
		jScrollPane = new JScrollPane(mainFrameConsoleTextPaneCharacteristic.characteristic().get());
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane.setBorder(null);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
}
