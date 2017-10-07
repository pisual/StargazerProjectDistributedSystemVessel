package com.stargazerproject.userinterface.resources;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.model.util.ColorUtil;
import com.stargazerproject.model.util.FontUtil;
import com.stargazerproject.model.util.ParameterStringUtil;

/**
 * 加载界面进度条文字标识
 *@author Felixerio
 */
@Component(value="loadingProgressInfo")
@Qualifier("loadingProgressInfo")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoadingProgressInfoCharacteristic extends JLabel implements BaseCharacteristic<JLabel>{
	private static final long serialVersionUID = -7016844370715643376L;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	
	public LoadingProgressInfoCharacteristic() {}
	
	@Override
	@Bean(name="loadingProgressInfoCharacteristic")
	@Lazy(true)
	public Optional<JLabel> characteristic() {
		initLoadingProgressInfo(fontInitialization(), fontColorInitialization());
		return Optional.of(this);
	}
	
	private Font fontInitialization(){
		String Loading_Frame_ProgressInfo_StandbyFontPath = systemParameter.get(Optional.of("Loading_Frame_ProgressInfo_StandbyFontPath")).get();
		String Loading_Frame_ProgressInfo_FontName = systemParameter.get(Optional.of("Loading_Frame_ProgressInfo_FontName")).get();
		Font loadingProgressInfoFont = FontUtil.getConsoleFont(Loading_Frame_ProgressInfo_FontName,Loading_Frame_ProgressInfo_StandbyFontPath);
		return loadingProgressInfoFont;
	}
	
	private Color fontColorInitialization(){
		Color loading_ProgressInfo_FontColor = ColorUtil.getColorFromIntRGBParament(ParameterStringUtil.parameterTransToNormallArray(systemParameter.get(Optional.of("loading_ProgressInfo_FontColor")), Optional.of(","), Optional.of(3)).get());
		return loading_ProgressInfo_FontColor;
	}
	
	private void initLoadingProgressInfo(Font font, Color color){
		this.setFont(font);
		this.setForeground(color); 
		this.setBounds(730, 238, 200, 30);
	}

}