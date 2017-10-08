package com.stargazerproject.userinterface.resources;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;


/**
 *主界面控制台滚动条
 * 
 *@author Felixerio
 */
@Component(value="MainFrameRightJScrollPane")
@Qualifier("MainFrameRightJScrollPane")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameRightJScrollPaneCharacteristic implements BaseCharacteristic<JScrollPane> {
	
	private Optional<JTextPane> jTextPane;
	private JScrollPane jScrollPane;
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="mainFrameRightJScrollPaneCharacteristic")
	@Lazy(true)
	public Optional<JScrollPane> characteristic() {
		jTextPane = BeanContainer.instance().getBean(Optional.of("mainFrameRightConsoleTextPaneCharacteristic"), Optional.class);
		jScrollPane = new JScrollPane(jTextPane.get());
		initialization();
		return Optional.of(jScrollPane);
	}
	
	public void initialization(){
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane.setBorder(null);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
}
