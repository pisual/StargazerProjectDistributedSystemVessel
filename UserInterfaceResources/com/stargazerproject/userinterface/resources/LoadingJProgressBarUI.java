package com.stargazerproject.userinterface.resources;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.util.ColorUtil;
import com.stargazerproject.util.ParameterStringUtil;

/**
 * 加载界面进度条UI
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
@Component(value="loadingJProgressBarUI")
@Qualifier("loadingJProgressBarUI")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingJProgressBarUI extends BasicProgressBarUI implements BaseCharacteristic<LoadingJProgressBarUI>{
	
	/** @name 加载界面背景 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_LoadingFrame_ProgressBar_Color;
	
	public LoadingJProgressBarUI() {}
	
	public void loadingJProgressBarUIInit(JProgressBar loadingframeJProgressBar){
		loadingframeJProgressBar.setForeground(jProgressBarColorInitialization());
		loadingframeJProgressBar.setBounds(430, 250, 670, 2);
	}
	
	@Override
	public Optional<LoadingJProgressBarUI> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	protected void paintDeterminate(Graphics g, JComponent c) {
		super.paintDeterminate(g, c);
	}
	
	private Color jProgressBarColorInitialization(){
		int[] colorArray = ParameterStringUtil.segmentationArray(Optional.of(Kernel_UserInterface_LoadingFrame_ProgressBar_Color), decollator(","), arrayLength(3)).get();
		Color ConsoleText_FontColor = ColorUtil.getColorFromIntRGBParament(colorArray);
		return ConsoleText_FontColor;
	}
	
	private Optional<String> decollator(String decollator){
		return Optional.of(decollator);
	}
	
	private Optional<Integer> arrayLength(int arrayLength){
		return Optional.of(arrayLength);
	}
	
}
