package com.stargazerproject.ui.assembly.impl;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import com.stargazerproject.ui.util.ColorUtil;

/**
 * 加载界面进度条UI
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class LoadingJProgressBarUI extends BasicProgressBarUI {
	/**加载界面进度条**/
	private static JProgressBar loadingframeJProgressBar;
	/**加载界面进度条 RGB色组**/
	private static Color Loading_Frame_ProgressBar_Color;
	
	public LoadingJProgressBarUI(JProgressBar jProgressBar) {
		Loading_Frame_ProgressBar_Color  = ColorUtil.getColorFromSystemmParanment("Loading_Frame_ProgressBar_Color");
		loadingframeJProgressBar = jProgressBar;
	}
	
	@Override
	protected void paintDeterminate(Graphics g, JComponent c) {
		loadingframeJProgressBar.setForeground(Loading_Frame_ProgressBar_Color);
		loadingframeJProgressBar.setBounds(430, 250, 670, 2);
		super.paintDeterminate(g, c);
	}
}
