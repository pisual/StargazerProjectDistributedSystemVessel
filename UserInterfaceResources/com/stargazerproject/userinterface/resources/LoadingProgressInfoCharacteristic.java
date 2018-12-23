package com.stargazerproject.userinterface.resources;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.characteristics.Characteristic;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.util.ColorUtil;
import com.stargazerproject.util.FontUtil;
import com.stargazerproject.util.ParameterStringUtil;

/**
 * 加载界面进度条文字标识
 *@author Felixerio
 */
@Component(value="loadingProgressInfoCharacteristic")
@Qualifier("loadingProgressInfoCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingProgressInfoCharacteristic implements BaseCharacteristic<JLabel>{
	
	/** @name 加载界面指定字体名称 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_LoadingFrame_Font_ProgressInfo;
	
	/** @name 加载界面字体的绝对路径 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_LoadingFrame_Font_Path_ProgressInfo;
	
	/** @name 加载界面指定字体颜色 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_LoadingFrame_Font_Color_ProgressInfo;
	
	private JLabel jLabel = new JLabel();
	
	private Boolean init = Boolean.FALSE;
	
	public LoadingProgressInfoCharacteristic() {}
	
	@Override
	public Optional<JLabel> characteristic() {
		synchronized(init){
			if(init == Boolean.FALSE){
				initLoadingProgressInfo(fontInitialization(), fontColorInitialization());
				init = Boolean.TRUE;
			}
		}
		return Optional.of(jLabel);
	}
	
	private Font fontInitialization(){
		Font ConsoleTextFont = FontUtil.getConsoleFont(Kernel_UserInterface_LoadingFrame_Font_ProgressInfo, Kernel_UserInterface_LoadingFrame_Font_Path_ProgressInfo);
		return ConsoleTextFont;
	}
	
	private Color fontColorInitialization(){
		int[] colorArray = ParameterStringUtil.segmentationArray(Optional.of(Kernel_UserInterface_LoadingFrame_Font_Color_ProgressInfo), decollator(","), arrayLength(3)).get();
		Color ConsoleText_FontColor = ColorUtil.getColorFromIntRGBParament(colorArray);
		return ConsoleText_FontColor;
	}
	
	private void initLoadingProgressInfo(Font font, Color color){
		jLabel.setFont(font);
		jLabel.setForeground(color); 
		jLabel.setBounds(730, 238, 200, 30);
	}
	
	private Optional<String> decollator(String decollator){
		return Optional.of(decollator);
	}
	
	private Optional<Integer> arrayLength(int arrayLength){
		return Optional.of(arrayLength);
	}

}