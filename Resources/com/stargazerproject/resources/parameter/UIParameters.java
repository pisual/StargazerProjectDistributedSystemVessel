package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.resources.annotation.Parameters;

/** 
 *  @name uiParameters 核心参数列表
 *  @illustrate 系统所需的uiParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="uiParameters")
@Qualifier("uiParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="uiParameters")
@SuppressWarnings("unused")
public class UIParameters {
	
//	/** @illustrate 全局根路径 **/
//	private static final String BasePath = System.getProperty("user.dir") + "/StargazerUIAssaultLily/";
	
	//UI配置 Start
	
	/** @illustrate 加载界面Logo **/
	private static final String Kernel_UserInterface_LoadingFrame_Icon_Logo = "logo.png";
	
	/** @illustrate 加载进度页面背景 **/
	private static final String Kernel_UserInterface_LoadingFrame_Background = "Loading.png";
	
	/** @illustrate 加载界面进度条 RGB色组 **/
	private static final String Kernel_UserInterface_LoadingFrame_ProgressBar_Color = "255,255,255";
	
	/** @illustrate 加载界面指定字体名称 **/
	private static final String Kernel_UserInterface_LoadingFrame_Font_ProgressInfo = "Copperplate";
	
	/** @illustrate 加载界面指定字体颜色 **/
	private static final String Kernel_UserInterface_LoadingFrame_Font_Color_ProgressInfo = "255,255,255";
	
	/** @illustrate 主界面宽 **/
	private static final String Kernel_UserInterface_MainFrame_Size_Width = "1824";
	
	/** @illustrate 主界面高 **/
	private static final String Kernel_UserInterface_MainFrame_Size_Height = "617";
	
	/** @illustrate 主界面背景 **/
	private static final String Kernel_UserInterface_MainFrame_Background = "Background.png";
	
	/** @illustrate 主界面使用者头像 **/
	private static final String Kernel_UserInterface_MainFrame_Icon_UserHeadPortrait = "logo.png";
	
	/** @illustrate 主界面Logo **/
	private static final String Kernel_UserInterface_MainFrame_Icon_Logo = "logo.png";
	
	/** @illustrate 主界面Logo位置及尺寸 格式 X ：Y：W ：H **/
	private static final String Kernel_UserInterface_MainFrame_Icon_Logo_Location = "1675,23,124,124";
	
	/** @illustrate 系统构架拓扑图标路径 **/
	private static final String Kernel_UserInterface_MainFrame_Icon_TructureTopology = "StructureTopology.png";
	
	/** @illustrate 主界面系统构架拓扑图标位置及尺寸 格式 X ：Y：W ：H **/
	private static final String Kernel_UserInterface_MainFrame_Icon_TructureTopology_Location = "1140,33,645,540";
	
	/** @illustrate 主界面控制台高度 **/
	private static final String Kernel_UserInterface_MainFrame_Console_Size_Height = "500";
	
	/** @illustrate 主界面控制台字体的名称 **/
	private static final String Kernel_UserInterface_MainFrame_Font_Console = "Copperplate";
	
	/** @illustrate 主界面控制台字体的RGB颜色 **/
	private static final String Kernel_UserInterface_MainFrame_Font_Color_Console = "247,247,247";
	
	/** @illustrate 主界面控制台字体前端行装饰 **/
	private static final String Kernel_UserInterface_MainFrame_Font_Icon_Line = "CircleText.png";
	
	//UI配置 End
	
}
