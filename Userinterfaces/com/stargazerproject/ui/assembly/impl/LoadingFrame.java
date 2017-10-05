package com.stargazerproject.ui.assembly.impl;

import com.stargazerproject.parameter.impl.SystemParameter;

/**
 * 混合主界面,启动加载界面
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class LoadingFrame {
	/**加载进度界面**/
	private static LoadingBaseFrameJDialog loadingBaseFrameJDialog;
	/**加载进度界面进度条**/
	private static LoadingJProgressBar loadingJProgressBar;
	/**加载界面进度条文字标识**/
	private static LoadingProgressInfo loadingProgressInfo;
	/**加载进度页面背景**/
	private static LoadingFrameBackgroundJlabel loadingFrameBackgroundJlabel;
	/**加载进度界面布局**/
	private static LoadingFrameLayout loadingFrameLayout;
	
	public LoadingFrame() {
		loadingBaseFrameJDialog = LoadingBaseFrameJDialog.getInstance();
		loadingJProgressBar = LoadingJProgressBar.getInstance();
		loadingProgressInfo = LoadingProgressInfo.getInstance();
		loadingFrameBackgroundJlabel = LoadingFrameBackgroundJlabel.getInstance(SystemParameterCahce.getInstance().getString("LOADING_INTERFACE_BACKGROUND"));
		loadingFrameLayout = LoadingFrameLayout.getInstance();
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
