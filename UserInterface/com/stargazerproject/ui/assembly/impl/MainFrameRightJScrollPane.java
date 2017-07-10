package com.stargazerproject.ui.assembly.impl;

import javax.swing.JScrollPane;


/**
 *主界面控制台滚动条
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class MainFrameRightJScrollPane extends JScrollPane{
	private static final long serialVersionUID = -6704110635604470232L;
	/**单例模式**/
	private static MainFrameRightJScrollPane mainFrameRightJScrollPane;
	
	public static final MainFrameRightJScrollPane getInstance(RightConsoleTextPane consoleTextArea){
		if(mainFrameRightJScrollPane == null){
			mainFrameRightJScrollPane = new MainFrameRightJScrollPane(consoleTextArea);
			mainFrameRightJScrollPane.initMainFrameJScrollPane();
		}
		return mainFrameRightJScrollPane;
	}
	
	private MainFrameRightJScrollPane(RightConsoleTextPane consoleTextArea) {
		super(consoleTextArea);
	}
	
	public void initMainFrameJScrollPane(){
		mainFrameRightJScrollPane.setOpaque(false);
		mainFrameRightJScrollPane.getViewport().setOpaque(false);
		mainFrameRightJScrollPane.setBorder(null);
		mainFrameRightJScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		mainFrameRightJScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
	}
}
