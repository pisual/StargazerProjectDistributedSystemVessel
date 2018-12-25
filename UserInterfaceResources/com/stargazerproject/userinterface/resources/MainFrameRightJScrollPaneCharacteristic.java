package com.stargazerproject.userinterface.resources;

import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;


/**
 *主界面控制台滚动条
 * 
 *@author Felixerio
 */
@Component(value="mainFrameRightJScrollPaneCharacteristic")
@Qualifier("mainFrameRightJScrollPaneCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameRightJScrollPaneCharacteristic implements BaseCharacteristic<JScrollPane> {
	
	@Autowired
	@Qualifier("mainFrameRightConsoleTextPaneCharacteristic")
	private BaseCharacteristic<MainFrameRightConsoleTextPaneCharacteristic> mainFrameRightConsoleTextPaneCharacteristic;
	
	private JScrollPane jScrollPane;
	
	private Boolean init = Boolean.FALSE;
	
	@Override
	public Optional<JScrollPane> characteristic() {
		synchronized(init){
			if(init == Boolean.FALSE){
				initialization();
				init = Boolean.TRUE;
			}
		}
		return Optional.of(jScrollPane);
	}
	
	private void initialization(){
		jScrollPane = new JScrollPane(mainFrameRightConsoleTextPaneCharacteristic.characteristic().get().getJTextPane().get());
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane.setBorder(null);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
}
