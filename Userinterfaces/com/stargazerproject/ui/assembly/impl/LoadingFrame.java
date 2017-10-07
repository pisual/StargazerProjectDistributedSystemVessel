package com.stargazerproject.ui.assembly.impl;

import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.userinterface.resources.LoadingBaseFrameJDialogCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingFrameBackgroundJlabelCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingFrameLayoutCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingJProgressBarCharacteristic;
import com.stargazerproject.userinterface.resources.LoadingProgressInfoCharacteristic;

/**
 * 混合主界面,启动加载界面
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class LoadingFrame {
	/**加载进度界面**/
	private static LoadingBaseFrameJDialogCharacteristic loadingBaseFrameJDialog;
	/**加载进度界面进度条**/
	private static LoadingJProgressBarCharacteristic loadingJProgressBar;
	/**加载界面进度条文字标识**/
	private static LoadingProgressInfoCharacteristic loadingProgressInfo;
	/**加载进度页面背景**/
	private static LoadingFrameBackgroundJlabelCharacteristic loadingFrameBackgroundJlabel;
	/**加载进度界面布局**/
	private static LoadingFrameLayoutCharacteristic loadingFrameLayout;
	
	public LoadingFrame() {
		loadingBaseFrameJDialog = LoadingBaseFrameJDialogCharacteristic.getInstance();
		loadingJProgressBar = LoadingJProgressBarCharacteristic.getInstance();
		loadingProgressInfo = LoadingProgressInfoCharacteristic.getInstance();
		loadingFrameBackgroundJlabel = LoadingFrameBackgroundJlabelCharacteristic.getInstance(SystemParameterCahce.getInstance().getString("LOADING_INTERFACE_BACKGROUND"));
		loadingFrameLayout = LoadingFrameLayoutCharacteristic.getInstance();
		loadingFrameLayout.initMainFrameLayout(loadingBaseFrameJDialog,loadingJProgressBar,loadingProgressInfo);
		loadingBaseFrameJDialog.getLayeredPane().add(loadingFrameBackgroundJlabel, new Integer(Integer.MIN_VALUE));
		loadingBaseFrameJDialog.getLayeredPane().add(loadingJProgressBar, new Integer(Integer.MAX_VALUE));
		loadingBaseFrameJDialog.getLayeredPane().add(loadingProgressInfo, new Integer(Integer.MAX_VALUE));

	}
	
	public void visualLoadingFrame(){
		loadingBaseFrameJDialog.setVisible(Boolean.TRUE);
	}
	
	public void unVisualLoadingFrame(){
		loadingBaseFrameJDialog.setVisible(Boolean.FALSE);
	}
	
	@SuppressWarnings("deprecation")
	public void disposeLoadingFrame(){
		loadingBaseFrameJDialog.disable();
	}
}
