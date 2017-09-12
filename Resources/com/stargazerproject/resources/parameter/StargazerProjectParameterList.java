package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/** 
 *  @name 核心参数列表
 *  @illustrate 系统所需的参数
 *  @author Felixerio
 *  **/
@Component
@Qualifier("stargazerProjectParameterList")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@SuppressWarnings("unused")
public class StargazerProjectParameterList {
	
	private StargazerProjectParameterList() {}

	/** @illustrate 全局根路径 **/
	private static final String BasePath = System.getProperty("user.dir") + "/StargazerUIAssaultLily/";

	/** @illustrate 是否进行了集群配置 **/
	private static final String CluStar_Cache_Initialize = "false";

	//言语及区域配置 Start
	/** @illustrate I18N全局语言配置 **/
	private static final String I18N_Initialize_PropertiesPath = "StargazerI18N_en.properties";
	//言语及区域配置 End
	
	//UI配置 Start
	/** @illustrate 界面宽 **/
	private static final String FRAME_SIZE_WIDTH = "1024";
	
	/** @illustrate 界面高 **/
	private static final String FRAME_SIZE_HIGTH = "1024";
	
	/** @illustrate 加载进度页面背景 **/
	private static final String LOADING_INTERFACE_BACKGROUND = "Loading.png";
	
	/** @illustrate 主界面页面背景 **/
	private static final String MAIN_INTERFACE_BACKGROUND = "Background.png";
	
	/** @illustrate 滚动条闸道背景颜色 RGB色组**/
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
	//UI配置 End
	
	//Event队列 Start
	/**接收Event队列消费者数目**/
	/** @illustrate 参数类 **/
	private static final String Receive_Event_Number_of_consumers = "4";
	/**接收Event队列缓存**/
	/** @illustrate 参数类 **/
	private static final String Receive_Event_Size_of_bufferSize = "65536";
	//Event队列 End
	
	//系统核心日志队列配置 Start
	/**系统核心日志队列消费者数目**/
	/** @illustrate 参数类 **/
	private static final String Receive_Log_Number_of_consumers = "1";
	/**系统核心日志队列缓存**/
	/** @illustrate 参数类 **/
	private static final String Receive_Log_Size_of_bufferSize = "65536";
	//系统核心日志队列配置 End
	
	//Netty接收Order超时缓存(Google Guava)配置 Start OrderCache
	/** Netty接收Order缓存初始化数目 **/
	/** @illustrate 参数类 **/
	private static final String OrderCache_initialSize = "65536";
	/** 拆分Order缓存最大数目 **/
	/** @illustrate 参数类 **/
	private static final String OrderCache_maxSize = "65537";
	/** 拆分Order缓存 并行级别数目 **/
	/** @illustrate 参数类 **/
	private static final String OrderCache_concurrencyLevel = Receive_Event_Number_of_consumers;
	/** 拆分Order缓存 非写销毁时间 **/
	/** @illustrate 参数类 **/
	private static final String OrderCache_expireAfterWriteTime = "6553";
	/** 拆分Order缓存 非读销毁时间 **/
	/** @illustrate 参数类 **/
	private static final String OrderCache_expireAfterReadTime = "5553";
	//Netty接收Order超时缓存(Google Guava)配置 End
	
	//运算级别指令队列 OperationLevelInstruction Start
	/** @illustrate 运算级别指令队列消费者数目 **/
	private static final String Operation_Level_Instruction_Number_of_consumers = "8";
	
	/** @illustrate 运算级别指令队列缓存 **/
	private static final String Operation_Level_Instruction_Size_of_bufferSize = "65536";
	//运算级别指令队列 operation Level instruction (运算级别指令) End
	
	//运算级别指令模块加载 OperationLevelInstruction Start
	/** @illustrate 运算级别指令模块加载位置 **/
	private static final String Operation_Level_Instruction_Model_Loading = "/Users/Felixerio/Desktop/StargazerProjectntelligenceSystemProtostuffBigcgModel.jar";
	//运算级别指令模块加载 operation Level instruction (运算级别指令) End
	
	//集群系统部署 Zookeeppr数据 Start
	/**
	 * Name: Cells 计算节点注册地址
	 * Node Type: 临时节点
	 * **/
	private static final String Cells_Base_Path = "StargazerSystem/cells/server";
	
	/**
	 * Name: 本计算节点Zookeeper通信端口
	 * **/
	private static final String Cells_Socket = "10841";
	
	private static String cellsSpeciesCluster = "StargazerSystem/cells/species/yandere_Cluster";
	
	/**
	 * 分区节点
	 * **/
	private static String Cells_Zone = "StargazerSystem/cells/yandere_Cluster/zone";
	
	//集群系统部署 Zookeeppr数据 End
	
}
