package com.stargazerproject.ui.assembly.impl;

import java.awt.Point;

import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.ui.listener.LogoClickListener;
import com.stargazerproject.ui.listener.MouseAdapterListener;
import com.stargazerproject.ui.listener.MouseMotionAdapterListener;

/**
 * 混合主界面,启动界面
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class MainFrame {
	/**混合主界面**/
	private static BaseFrame baseFrame;
	/**控制台**/
	private static ConsoleTextPane consoleTextPane;
	/**控制台**/
	private static RightConsoleTextPane rightConsoleTextPane;
	/**主界面滚动条**/
	private static MainFrameJScrollPane jScrollPane;
	/**主界面滚动条**/
	private static MainFrameRightJScrollPane rightJScrollPane;
	/**操纵图标头像位置**/
	private static MainFrameLogoJlabel mainFrameLogoJlabel;
	/**系统构架拓扑**/
	private static StructureTopologyJlabel structureTopologyJlabel;
	/**主界面背景**/
	private static MainFrameBackgroundJlabel mainFrameBackgroundJlabel;
	/**混合主界面布局**/
	private static MainFrameLayout mainFrameLayout;
	/**主界面坐标点，用于界面拖动**/
	private static Point origin = new Point();
	/**主界面鼠标点击事件**/
	private static LogoClickListener logoClickListener;
	/**获取主界面鼠标点击位置**/
	private static MouseAdapterListener mouseAdapterListener;
	/**主界面鼠标点击事件**/
	private static MouseMotionAdapterListener mouseMotionAdapterListener;
	
	public MainFrame(){
	//	baseFrame = BaseFrame.getInstance();
		consoleTextPane = ConsoleTextPane.getInstance();
		rightConsoleTextPane = RightConsoleTextPane.getInstance();
		rightJScrollPane = MainFrameRightJScrollPane.getInstance(rightConsoleTextPane);
		jScrollPane = MainFrameJScrollPane.getInstance(consoleTextPane);
		mainFrameLogoJlabel = MainFrameLogoJlabel.getInstance(SystemParameterCahce.getInstance().getString("Main_Frame_Logo_Path"));
		structureTopologyJlabel = StructureTopologyJlabel.getInstance(SystemParameterCahce.getInstance().getString("Main_Frame_StructureTopology_Path"));
		mainFrameBackgroundJlabel = MainFrameBackgroundJlabel.getInstance(SystemParameterCahce.getInstance().getString("MAIN_INTERFACE_BACKGROUND"));
		mainFrameLayout = MainFrameLayout.getInstance();
		logoClickListener = LogoClickListener.getInstance(baseFrame);
		mouseAdapterListener = MouseAdapterListener.getInstance(origin);
		mouseMotionAdapterListener = MouseMotionAdapterListener.getInstance(origin,baseFrame);
		
		mainFrameLayout.initMainFrameLayout(baseFrame,jScrollPane,rightJScrollPane);
		baseFrame.getLayeredPane().add(mainFrameBackgroundJlabel,new Integer(Integer.MIN_VALUE));
		baseFrame.getLayeredPane().add(mainFrameLogoJlabel,new Integer(Integer.MIN_VALUE)+1);
		baseFrame.getLayeredPane().add(structureTopologyJlabel,new Integer(Integer.MIN_VALUE)+2);
		mainFrameLogoJlabel.addMouseListener(logoClickListener);
		mainFrameLogoJlabel.addMouseListener(mouseAdapterListener);
		mainFrameLogoJlabel.addMouseMotionListener(mouseMotionAdapterListener);	
	}
	
	public void VisualMainFrame(){
		baseFrame.setVisible(Boolean.TRUE);
		mainFrameLogoJlabel.setVisible(Boolean.FALSE);
		structureTopologyJlabel.setVisible(Boolean.FALSE);
	}
}
