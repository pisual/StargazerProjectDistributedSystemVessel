package com.stargazerproject.userinterface.resources;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.spring.container.impl.BeanContainer;
import com.stargazerproject.util.ColorUtil;
import com.stargazerproject.util.ParameterStringUtil;

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
	
	@SuppressWarnings("unchecked")
	public LoadingJProgressBarUI(JProgressBar jProgressBar) {
		Cache<String,String> systemParameter = BeanContainer.instance().getBean(Optional.of("systemParameterCahce"), Cache.class);
		Loading_Frame_ProgressBar_Color  = ColorUtil.getColorFromIntRGBParament(ParameterStringUtil.parameterTransToNormallArray(systemParameter.get(Optional.of("Loading_Frame_ProgressBar_Color")), Optional.of(","), Optional.of(3)).get());
		loadingframeJProgressBar = jProgressBar;
		loadingframeJProgressBar.setForeground(Loading_Frame_ProgressBar_Color);
		loadingframeJProgressBar.setBounds(430, 250, 670, 2);
	}
	
	@Override
	protected void paintDeterminate(Graphics g, JComponent c) {
		super.paintDeterminate(g, c);
	}
}
