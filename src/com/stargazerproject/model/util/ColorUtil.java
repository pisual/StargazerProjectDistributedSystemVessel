package com.stargazerproject.model.util;

import java.awt.Color;

/**
 * 颜色控制工具
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class ColorUtil {
	
	/**从Int[]类型RGB获取Color类*/
	public static Color getColorFromIntRGBParament(int RGB[]){
		Color color = new Color(0,0,0);
		if(RGB.length == 3){
			color = new Color(RGB[0],RGB[1],RGB[2]);
		}
		return color;
	}
	
}
