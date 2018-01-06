package com.stargazerproject.resources.parameter;

import java.io.Serializable;

public class InjectParameters implements Serializable {

	private static final long serialVersionUID = -61107373055806462L;

	// Netty接收Order超时缓存(Google Guava)配置 Start OrderCache
	/** @illustrate Netty接收Order缓存初始化数目 **/
	private static final String OrderCache_initialSize = "65536";
	/** @illustrate 拆分Order缓存最大数目 **/
	private static final String OrderCache_maxSize = "65537";
	/** @illustrate 拆分Order缓存 并行级别数目 **/
	private static final String OrderCache_concurrencyLevel = QueueParameters.Receive_Event_Number_of_consumers;
	/** @illustrate 拆分Order缓存 非写销毁时间 **/
	private static final String OrderCache_expireAfterWriteTime = "6553";
	/** @illustrate 拆分Order缓存 非读销毁时间 **/
	private static final String OrderCache_expireAfterReadTime = "5553";
	// Netty接收Order超时缓存(Google Guava)配置 End

	// Event队列 Start
	/** @illustrate order Export 队列消费者数目 **/
	private static final String Order_Export_Number_of_consumers = "1";
	/** @illustrate orderExport 队列缓存**/
	private static final String Order_Export_Size_of_bufferSize = "65536";
	// Event队列 End

	// Event队列 Start
	/** 接收Event队列消费者数目 **/
	/** @illustrate 参数类 **/
	public static final String Receive_Event_Number_of_consumers = "4";
	/** 接收Event队列缓存 **/
	/** @illustrate 参数类 **/
	private static final String Receive_Event_Size_of_bufferSize = "65536";
	// Event队列 End

	/** @illustrate 全局根路径 **/
	private static final String BasePath = System.getProperty("user.dir") + "/StargazerUIAssaultLily/";

	// 言语及区域配置 Start
	/** @illustrate I18N全局语言配置 **/
	private static final String I18N_Initialize_PropertiesPath = "StargazerI18N_en.properties";
	// 言语及区域配置 End

	// UI配置 Start
	/** @illustrate 界面宽 **/
	private static final String FRAME_SIZE_WIDTH = "1824";

	/** @illustrate 界面高 **/
	private static final String FRAME_SIZE_HIGTH = "617";

	/** @illustrate 加载进度页面背景 **/
	private static final String LOADING_INTERFACE_BACKGROUND = "Loading.png";

	/** @illustrate 主界面页面背景 **/
	private static final String MAIN_INTERFACE_BACKGROUND = "Background.png";

	/** @illustrate 滚动条闸道背景颜色 RGB色组 **/
	private static final String JScrollPaneUI_TrackColor = "247,247,247";

	/** @illustrate 滚动条把手边框颜色 RGB色组 **/
	private static final String JScrollPaneUI_Scroll_Border_Color = "109,109,109";

	/** @illustrate 界面系统图标 **/
	private static final String Main_Frame_IconImage = "logo.png";

	/** @illustrate 操纵图标头像位置及尺寸 **/
	private static final String Main_Frame_LogoLocation = "1675,23,124,124";

	/** @illustrate 主界面系统构架拓扑图标位置及尺寸 **/
	private static final String Main_Frame_StructureTopology_Location = "1140,33,645,540";

	/** @illustrate 操纵图标头像文件路径 **/
	private static final String Main_Frame_Logo_Path = "logo.png";

	/** @illustrate 系统构架拓扑图标路径 **/
	private static final String Main_Frame_StructureTopology_Path = "StructureTopology.png";

	/** @illustrate 主界面控制台高度 **/
	private static final String Main_Frame_Console_Height = "500";

	/** @illustrate 控制台备用字体的绝对路径 在系统包含指定字体的情况下将不使用备用字体 **/
	private static final String Main_Frame_Console_StandbyFontPath = BasePath + "Copperplate.ttf";

	/** @illustrate 控制台指定字体名称 **/
	private static final String Main_Frame_Console_FontName = "Copperplate";

	/** @illustrate 控制台指定字体RGB颜色 **/
	private static final String ConsoleText_FontColor = "247,247,247";

	/** @illustrate 加载界面进度条 RGB色组 **/
	private static final String Loading_Frame_ProgressBar_Color = "255,255,255";

	/** @illustrate 加载界面备用字体的绝对路径 在系统包含指定字体的情况下将不使用备用字体 **/
	private static final String Loading_Frame_ProgressInfo_StandbyFontPath = BasePath + "Copperplate.ttf";

	/** @illustrate 加载界面指定字体名称 **/
	private static final String Loading_Frame_ProgressInfo_FontName = "Copperplate";

	/** @illustrate 加载界面指定字体颜色 **/
	private static final String loading_ProgressInfo_FontColor = "255,255,255";

	/** @illustrate 加载界面指定字体颜色 **/
	private static final String ConsoleTextPane_Text_Circle = "CircleText.png";
	// UI配置 End

}
