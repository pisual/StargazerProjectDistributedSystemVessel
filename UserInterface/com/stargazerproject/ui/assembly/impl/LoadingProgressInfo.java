package com.stargazerproject.ui.assembly.impl;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.ui.util.ColorUtil;
import com.stargazerproject.ui.util.FontUtil;

/**
 * 加载界面进度条文字标识
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class LoadingProgressInfo extends JLabel {
	private static final long serialVersionUID = -7016844370715643376L;
	/**加载输出字符字体类型**/
	private static Font loadingProgressInfoFont;
	/**加载界面备用字体的绝对路径 在系统包含指定字体的情况下将不使用备用字体**/
	public static String Loading_Frame_ProgressInfo_StandbyFontPath;
	/**加载界面指定字体名称**/
	public static String Loading_Frame_ProgressInfo_FontName;
	/**加载界面输出字符字体颜色**/
	private static Color loading_ProgressInfo_FontColor;
	/**LoadingProgressInfo单例**/
	private static LoadingProgressInfo loadingProgressInfo;
	
	public static final LoadingProgressInfo getInstance(){
		if(loadingProgressInfo == null){
			loadingProgressInfo = new LoadingProgressInfo();
			loadingProgressInfo.initLoadingProgressInfo();
		}
		return loadingProgressInfo;
	}
	
	private LoadingProgressInfo() {
		Loading_Frame_ProgressInfo_StandbyFontPath = SystemParameterCahce.getInstance().getString("Loading_Frame_ProgressInfo_StandbyFontPath");
		Loading_Frame_ProgressInfo_FontName = SystemParameterCahce.getInstance().getString("Loading_Frame_ProgressInfo_FontName");
		loading_ProgressInfo_FontColor = ColorUtil.getColorFromSystemmParanment("loading_ProgressInfo_FontColor");
		loadingProgressInfoFont = FontUtil.getConsoleFont(Loading_Frame_ProgressInfo_FontName,Loading_Frame_ProgressInfo_StandbyFontPath);
	}
	
	public void initLoadingProgressInfo(){
		loadingProgressInfo.setFont(loadingProgressInfoFont);
		loadingProgressInfo.setForeground(loading_ProgressInfo_FontColor); 
		loadingProgressInfo.setBounds(730, 238, 200, 30);
	}
	
	/**更新进度条文字标识**/
    public static void updateProgressInfo(String info) {  
    	LoadingProgressInfo.getInstance().setText(info); 
    }
}
