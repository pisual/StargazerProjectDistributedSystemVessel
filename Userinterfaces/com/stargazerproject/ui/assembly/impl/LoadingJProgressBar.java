package com.stargazerproject.ui.assembly.impl;

import javax.swing.JProgressBar;

/**
 * 加载界面进度条
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class LoadingJProgressBar extends JProgressBar {
	private static final long serialVersionUID = 1L;
	/**LoadingJProgressBar 单例**/
	private static LoadingJProgressBar loadingJProgressBar;
	
	public static final LoadingJProgressBar getInstance(){
		if(loadingJProgressBar == null){
			loadingJProgressBar = new LoadingJProgressBar();
			loadingJProgressBar.InitLoadingJProgressBar();
		}
		return loadingJProgressBar;
	}
	
	private LoadingJProgressBar() {
	}
	
	private void InitLoadingJProgressBar(){
		loadingJProgressBar.setUI(new LoadingJProgressBarUI(loadingJProgressBar));
		loadingJProgressBar.setBounds(100, 400, 1000, 2);
	}
	
	/**更新进度条进度**/
    public static void updateJProgressBar(int value) {  
    	LoadingJProgressBar.getInstance().setValue(value);  
    }
}
