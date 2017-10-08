package com.stargazer.ui.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

public class UIUtil {
	/**
	 * 调整窗口居中
	 * @author felixsion
	 * **/
	public static void changeFrameToCenter(JDialog jDialog){
		 Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包 
	     Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸 
	     int screenWidth = screenSize.width/2; // 获取屏幕的宽
	     int screenHeight = screenSize.height/2; // 获取屏幕的高
	     int height = jDialog.getHeight(); 
	     int width = jDialog.getWidth();
	     jDialog.setLocation(screenWidth-width/2, screenHeight-height/2);
	}
}
