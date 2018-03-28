package com.stargazerproject.userinterface.resources;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/**
 *混合主界面布局器 加载进度界面布局
 *@author Felixerio
 **/
@Component(value="loadingFrameLayoutCharacteristic")
@Qualifier("loadingFrameLayoutCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingFrameLayoutCharacteristic implements BaseCharacteristic<LoadingFrameLayoutCharacteristic>{
	
	/**加载进度界面特征**/
	@Autowired
	@Qualifier("loadingBaseFrameJDialogCharacteristic")
	private BaseCharacteristic<JDialog> loadingBaseFrameJDialogCharacteristic;
	
	/**加载进度界面**/
	private JDialog loadingBaseFrameJDialog;
	
	/**加载进度界面进度条特征**/
	@Autowired
	@Qualifier("loadingJProgressBarCharacteristic")
	private BaseCharacteristic<JProgressBar> loadingJProgressBarCharacteristic;
	
	/**加载进度界面进度条**/
	private JProgressBar loadingJProgressBar;

	/**加载界面进度条文字标识特征**/
	@Autowired
	@Qualifier("loadingProgressInfoCharacteristic")
	private BaseCharacteristic<JLabel> loadingProgressInfoCharacteristic;
	
	/**加载界面进度条文字标识**/
	private JLabel loadingProgressInfo;
	
	public LoadingFrameLayoutCharacteristic() {}
	
	@Override
	public Optional<LoadingFrameLayoutCharacteristic> characteristic() {
		loadingBaseFrameJDialog = loadingBaseFrameJDialogCharacteristic.characteristic().get();
		loadingJProgressBar = loadingJProgressBarCharacteristic.characteristic().get();
		loadingProgressInfo = loadingProgressInfoCharacteristic.characteristic().get();
		initMainFrameLayout(loadingBaseFrameJDialog, loadingJProgressBar, loadingProgressInfo);
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
