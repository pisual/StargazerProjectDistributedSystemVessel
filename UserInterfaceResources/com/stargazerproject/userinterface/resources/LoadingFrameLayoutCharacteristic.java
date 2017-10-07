package com.stargazerproject.userinterface.resources;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;

/**
 *混合主界面布局器 加载进度界面布局
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio
 **/
public class LoadingFrameLayoutCharacteristic implements BaseCharacteristic<LoadingFrameLayoutCharacteristic>{
	
	private Optional<JDialog> jDialog;
	private Optional<JProgressBar> jProgressBar;
	private Optional<JLabel> jLabel;
	
	public LoadingFrameLayoutCharacteristic() {}
	
	@Override
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
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				  .addGroup(GroupLayout.Alignment.TRAILING,layout.createSequentialGroup()
				  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)));
	}
	
}
