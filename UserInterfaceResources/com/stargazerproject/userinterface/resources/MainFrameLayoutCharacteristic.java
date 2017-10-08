package com.stargazerproject.userinterface.resources;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

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
import com.stargazerproject.spring.container.impl.BeanContainer;

/**
 *混合主界面布局器
 *@author Felixerio
 **/
@Component(value="mainFrameLayout")
@Qualifier("mainFrameLayout")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameLayoutCharacteristic implements BaseCharacteristic<MainFrameLayoutCharacteristic>{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	private Optional<JFrame> jFrame;
	private Optional<JScrollPane> jScrollPane;
	private Optional<JScrollPane> rightJScrollPane;
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="mainFrameLayoutCharacteristic")
	@Lazy(true)
	public Optional<MainFrameLayoutCharacteristic> characteristic() {
		jFrame = BeanContainer.instance().getBean(Optional.of("mainFrameJFrameCharacteristic"), Optional.class);
		jScrollPane = BeanContainer.instance().getBean(Optional.of("mainFrameJScrollPaneCharacteristic"), Optional.class);
		rightJScrollPane = BeanContainer.instance().getBean(Optional.of("mainFrameRightJScrollPaneCharacteristic"), Optional.class);
		initialization(jFrame.get(), jScrollPane.get(), rightJScrollPane.get());
		return Optional.of(this);
	}
	
	private void initialization(JFrame jFrame,JScrollPane jScrollPane, JScrollPane rightJScrollPane) {
		int Main_Frame_Console_Height = Integer.parseInt(systemParameter.get(Optional.of("Main_Frame_Console_Height")).get());
		GroupLayout layout = new GroupLayout(jFrame.getContentPane());
		jFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			  .addGap(35, 35, 35)
			  .addComponent(true,jScrollPane,GroupLayout.DEFAULT_SIZE, 610, 610)) 
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			  .addComponent(true,jScrollPane,GroupLayout.DEFAULT_SIZE, 610, 610)
			  .addGap(565, 565, 565)
			  .addComponent(true,rightJScrollPane,GroupLayout.DEFAULT_SIZE, 610, 610)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			  .addGap(55, 55, 55)
			  .addComponent(jScrollPane,GroupLayout.PREFERRED_SIZE,Main_Frame_Console_Height,GroupLayout.PREFERRED_SIZE))
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			  .addGap(55, 55, 55)
			  .addComponent(rightJScrollPane,GroupLayout.PREFERRED_SIZE,Main_Frame_Console_Height,GroupLayout.PREFERRED_SIZE)));
	}
}
