package com.stargazerproject.ui.assembly.impl;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

/**
 * 加载进度界面
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class LoadingBaseFrameJDialog extends JDialog {
	private static final long serialVersionUID = 5171904966548890916L;
	/**单例模式**/
	private static LoadingBaseFrameJDialog loadingBaseFrameJDialog;
	
	public static final LoadingBaseFrameJDialog getInstance(){
		if(loadingBaseFrameJDialog == null){
			loadingBaseFrameJDialog = new LoadingBaseFrameJDialog();
			loadingBaseFrameJDialog.initLoadingBaseFrameJDialog();
		}
		return loadingBaseFrameJDialog;
	}
	
	private LoadingBaseFrameJDialog() {
	}
	
	private void initLoadingBaseFrameJDialog(){
		((JPanel) loadingBaseFrameJDialog.getContentPane()).setOpaque(false);
		loadingBaseFrameJDialog.setUndecorated(true);
		loadingBaseFrameJDialog.setSize(295,296);
       // UIUtil.changeJDialogToCenter(loadingBaseFrameJDialog);
		loadingBaseFrameJDialog.setBounds(839, 403, 296, 296);
		AWTUtilities.setWindowOpaque(this, false);
	}
}
