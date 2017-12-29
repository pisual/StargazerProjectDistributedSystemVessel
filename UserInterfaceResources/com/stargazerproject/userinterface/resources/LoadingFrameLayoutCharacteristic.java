package com.stargazerproject.userinterface.resources;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;

/**
 *混合主界面布局器 加载进度界面布局
 *@author Felixerio
 **/
@Component(value="loadingFrameLayout")
@Qualifier("loadingFrameLayout")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingFrameLayoutCharacteristic implements BaseCharacteristic<LoadingFrameLayoutCharacteristic>{
	
	private Optional<JDialog> jDialog;
	private Optional<JProgressBar> jProgressBar;
	private Optional<JLabel> jLabel;
	
	public LoadingFrameLayoutCharacteristic() {}
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="loadingFrameLayoutCharacteristic")
	@Lazy(true)
	public Optional<LoadingFrameLayoutCharacteristic> characteristic() {
		jDialog = BeanContainer.instance().getBean(Optional.of("loadingBaseFrameJDialogCharacteristic"), Optional.class);
		jProgressBar = BeanContainer.instance().getBean(Optional.of("loadingJProgressBarCharacteristic"), Optional.class);
		jLabel = BeanContainer.instance().getBean(Optional.of("loadingProgressInfoCharacteristic"), Optional.class);
		initMainFrameLayout(jDialog.get(), jProgressBar.get(), jLabel.get());
		return Optional.of(this);
	}
	
	private void initMainFrameLayout(JDialog jDialog, JProgressBar progressBar, JLabel progressInfo) {
		GroupLayout layout = new GroupLayout(jDialog.getContentPane());
		jDialog.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			  .addComponent(progressBar, GroupLayout.Alignment.TRAILING,GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
			  .addComponent(progressInfo, GroupLayout.Alignment.TRAILING,GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			  .addGroup(GroupLayout.Alignment.TRAILING,layout.createSequentialGroup()
			  .addGap(282)
			  .addComponent(progressBar,GroupLayout.PREFERRED_SIZE,2,GroupLayout.PREFERRED_SIZE)
			  .addComponent(progressInfo,GroupLayout.PREFERRED_SIZE, 18,GroupLayout.PREFERRED_SIZE)
			  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)));
	}

	
}
