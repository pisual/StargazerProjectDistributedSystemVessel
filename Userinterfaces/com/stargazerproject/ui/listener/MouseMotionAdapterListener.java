package com.stargazerproject.ui.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

/**
 * 鼠标拖拽事件
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class MouseMotionAdapterListener extends MouseMotionAdapter{
	/**操纵点坐标**/
	private static Point mainOrigin;
	/**主界面**/
	private static JFrame mainJFrame;
	/**MouseMotionAdapterListener单例**/
	private static MouseMotionAdapterListener mouseMotionAdapterListener;
	
	public static final MouseMotionAdapterListener getInstance(Point origin,JFrame jFrame){
		if(mouseMotionAdapterListener == null){
			mouseMotionAdapterListener = new MouseMotionAdapterListener(origin,jFrame);
		}
		return mouseMotionAdapterListener;
	}
	
	private MouseMotionAdapterListener(Point origin,JFrame jFrame) {
		mainOrigin = origin;
		mainJFrame = jFrame;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = mainJFrame.getLocation();
		mainJFrame.setLocation(p.x + e.getX() - mainOrigin.x, p.y+ e.getY() - mainOrigin.y);
		}

}
