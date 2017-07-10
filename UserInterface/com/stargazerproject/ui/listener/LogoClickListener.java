package com.stargazerproject.ui.listener;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

/**
 *操控头像单击事件
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class LogoClickListener extends MouseAdapter {
	/**主界面**/
	private static JFrame MainJFrame;
	/**单例模式**/
	private static  LogoClickListener logoClickListener;
	
	public static LogoClickListener getInstance(JFrame jFrame){
		if(LogoClickListener.logoClickListener == null){
			LogoClickListener.logoClickListener = new LogoClickListener();
			MainJFrame = jFrame;
		}
		return LogoClickListener.logoClickListener;
	}
	
	private LogoClickListener() {
	}
	
	@Override
	 public void mouseClicked(MouseEvent evt) {
		 if (evt.getClickCount() == 5) {
			 System.out.println("System has Exit By User");
			 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 System.exit(0);
		 } else if (evt.getClickCount() == 2) {
			 MainJFrame.setExtendedState(Frame.ICONIFIED);
		 }
		 }
}
