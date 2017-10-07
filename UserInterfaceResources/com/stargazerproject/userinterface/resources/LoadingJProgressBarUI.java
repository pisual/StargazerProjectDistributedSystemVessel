package com.stargazerproject.userinterface.resources;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.model.util.ColorUtil;
import com.stargazerproject.model.util.ParameterStringUtil;
import com.stargazerproject.spring.container.impl.BeanContainer;

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
		Cache<String,String> systemParameter = BeanContainer.instance().getBean(Optional.of("systemParameterCahce"), Cache.class);
		Loading_Frame_ProgressBar_Color  = ColorUtil.getColorFromIntRGBParament(ParameterStringUtil.parameterTransToNormallArray(systemParameter.get(Optional.of("Loading_Frame_ProgressBar_Color")), Optional.of(","), Optional.of(3)).get());
		loadingframeJProgressBar = jProgressBar;
	}
	
	@Override
	protected void paintDeterminate(Graphics g, JComponent c) {
		loadingframeJProgressBar.setForeground(Loading_Frame_ProgressBar_Color);
		loadingframeJProgressBar.setBounds(430, 250, 670, 2);
		super.paintDeterminate(g, c);
	}
}
