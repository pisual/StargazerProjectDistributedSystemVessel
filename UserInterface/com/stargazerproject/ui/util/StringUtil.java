package com.stargazerproject.ui.util;

/**
 * String 常用工具类
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class StringUtil {
	
	public static void checkForNull(Object o, String message)
	{
		if (o == null)
		{
			throw new NullPointerException(message);
		}
	}
	
	public static void checkForEmpty(String s, String message)
	{
		if (s.length() == 0)
		{
			throw new IllegalArgumentException(message);
		}
	}
	
}
