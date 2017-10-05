package com.stargazerproject.ui.listener;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 获取鼠标点击位置事件
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class MouseAdapterListener extends MouseAdapter{
	/**操纵点坐标**/
	private static Point mainJframeOrigin;
	/**单例模式**/
	private static MouseAdapterListener mouseAdapterListener;

	public static MouseAdapterListener getInstance(Point origin){
		if(mouseAdapterListener == null){
			mouseAdapterListener = new MouseAdapterListener();
			mainJframeOrigin = origin;
		}
		return mouseAdapterListener;
	}
	
	private MouseAdapterListener() {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		mainJframeOrigin.x = e.getX();
		mainJframeOrigin.y = e.getY();
		}
}
