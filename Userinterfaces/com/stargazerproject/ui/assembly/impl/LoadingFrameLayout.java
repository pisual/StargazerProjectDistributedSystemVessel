package com.stargazerproject.ui.assembly.impl;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.LayoutStyle;

/**
 *混合主界面布局器 加载进度界面布局
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 **/
public class LoadingFrameLayout {
	/**LoadingFrameLayout单例**/
	private static LoadingFrameLayout loadingFrameLayout;
	
	public static final LoadingFrameLayout getInstance(){
		if(loadingFrameLayout == null){
			loadingFrameLayout = new LoadingFrameLayout();
		}
		return loadingFrameLayout;
	}
	
	private LoadingFrameLayout() {
	}
	
	public void initMainFrameLayout(JDialog jDialog, LoadingJProgressBar progressBar,LoadingProgressInfo progressInfo) {
		GroupLayout layout = new GroupLayout(jDialog.getContentPane());
		jDialog.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			//	.addComponent(progressBar, GroupLayout.Alignment.TRAILING,GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
				//  .addComponent(progressInfo, GroupLayout.Alignment.TRAILING,GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			
				  .addGroup(GroupLayout.Alignment.TRAILING,layout.createSequentialGroup()
			//	  .addComponent(progressBar,GroupLayout.PREFERRED_SIZE,2,GroupLayout.PREFERRED_SIZE)
				//  .addContainerGap(265, Short.MAX_VALUE)
				//  .addComponent(progressInfo,GroupLayout.PREFERRED_SIZE, 18,GroupLayout.PREFERRED_SIZE)
				  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				  ));
	}
}
