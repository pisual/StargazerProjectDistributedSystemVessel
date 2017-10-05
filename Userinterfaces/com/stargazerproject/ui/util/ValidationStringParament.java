package com.stargazerproject.ui.util;

/**
 * 校验初始化过程中的参数，提供两种校验
 * 1，是否为NULL
 * 2，是否长度为0
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class ValidationStringParament {
	public static Boolean validationStringParament(String parament) {
		Boolean resultState = Boolean.FALSE;
		if (parament == null)
		{
			throw new NullPointerException("StargazerSystem Error Report : " + parament + " 参数为空");
		}
		else if (parament.length() == 0)
		{
			throw new IllegalArgumentException("StargazerSystem Error Report : " + parament +" 参数长度为0");
		}
		else{
			resultState = Boolean.TRUE;
		}
		return resultState;
	}
}
