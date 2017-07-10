package com.stargazerproject.ui.assembly.impl;

import javax.swing.JScrollPane;


/**
 *主界面控制台滚动条
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class MainFrameJScrollPane extends JScrollPane{
	private static final long serialVersionUID = -6704110635604470232L;
	/**单例模式**/
	private static MainFrameJScrollPane mainFrameJScrollPane;
	
	public static final MainFrameJScrollPane getInstance(ConsoleTextPane consoleTextArea){
		if(mainFrameJScrollPane == null){
			mainFrameJScrollPane = new MainFrameJScrollPane(consoleTextArea);
			mainFrameJScrollPane.initMainFrameJScrollPane();
		}
		return mainFrameJScrollPane;
	}
	
	private MainFrameJScrollPane(ConsoleTextPane consoleTextArea) {
		super(consoleTextArea);
	}
	
	public void initMainFrameJScrollPane(){
		mainFrameJScrollPane.setOpaque(false);
		mainFrameJScrollPane.getViewport().setOpaque(false);
		mainFrameJScrollPane.setBorder(null);
		mainFrameJScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		mainFrameJScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
	}
}
