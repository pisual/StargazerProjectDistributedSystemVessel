package com.stargazerproject.ui.assembly.impl;

import java.awt.Point;

import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.ui.listener.LogoClickListener;
import com.stargazerproject.ui.listener.MouseAdapterListener;
import com.stargazerproject.ui.listener.MouseMotionAdapterListener;
import com.stargazerproject.userinterface.resources.MainFrameJFrameCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameJScrollPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameLayoutCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameLogoJlabelCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameStructureTopologyJlabelCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameConsoleTextPaneCharacteristic;
import com.stargazerproject.userinterface.resources.MainFrameBackgroundJlabelCharacteristic;

/**
 * 混合主界面,启动界面
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class MainFrame {
	/**混合主界面**/
	private static MainFrameJFrameCharacteristic baseFrame;
	/**控制台**/
	private static MainFrameConsoleTextPaneCharacteristic consoleTextPane;
	/**控制台**/
	private static RightConsoleTextPane rightConsoleTextPane;
	/**主界面滚动条**/
	private static MainFrameJScrollPaneCharacteristic jScrollPane;
	/**主界面滚动条**/
	private static MainFrameRightJScrollPane rightJScrollPane;
	/**操纵图标头像位置**/
	private static MainFrameLogoJlabelCharacteristic mainFrameLogoJlabel;
	/**系统构架拓扑**/
	private static MainFrameStructureTopologyJlabelCharacteristic structureTopologyJlabel;
	/**主界面背景**/
	private static MainFrameBackgroundJlabelCharacteristic mainFrameBackgroundJlabel;
	/**混合主界面布局**/
	private static MainFrameLayoutCharacteristic mainFrameLayout;
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
		consoleTextPane = MainFrameConsoleTextPaneCharacteristic.getInstance();
		rightConsoleTextPane = RightConsoleTextPane.getInstance();
		rightJScrollPane = MainFrameRightJScrollPane.getInstance(rightConsoleTextPane);
		jScrollPane = MainFrameJScrollPaneCharacteristic.getInstance(consoleTextPane);
		mainFrameLogoJlabel = MainFrameLogoJlabelCharacteristic.getInstance(SystemParameterCahce.getInstance().getString("Main_Frame_Logo_Path"));
		structureTopologyJlabel = MainFrameStructureTopologyJlabelCharacteristic.getInstance(SystemParameterCahce.getInstance().getString("Main_Frame_StructureTopology_Path"));
		mainFrameBackgroundJlabel = MainFrameBackgroundJlabelCharacteristic.getInstance(SystemParameterCahce.getInstance().getString("MAIN_INTERFACE_BACKGROUND"));
		mainFrameLayout = MainFrameLayoutCharacteristic.getInstance();
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
