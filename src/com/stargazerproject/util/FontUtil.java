package com.stargazerproject.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FontUtil {
	
	/**获取字体**/
	public static Font getConsoleFont(String fontName){
		Font ConsoleFont = null;
		if(FontUtil.isFontExistInSystem(fontName)){
			ConsoleFont = new Font(fontName, Font.BOLD, 11);
			System.out.println("Stargazer System Report : 系统包含指定字体 "+fontName+" 将使用系统字体");
		}
		else{
			System.err.println("Stargazer System Report : 字体加载异常,没有寻找到制定字体 "+fontName+" 加载默认字体");
			ConsoleFont = new Font("serif", Font.PLAIN, 24);

		}
		return ConsoleFont;
	}
	
	/**获取系统所有字体的 HashMap<String, String> **/
	public static HashMap<String, String> getFontPathMap() {
		HashMap<String, String> systemFontCollection = new HashMap<String, String>();
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        String[] fontName = e.getAvailableFontFamilyNames(); 
        int fontTotalNum = fontName.length;
         for(int i = 0; i<fontTotalNum; i++){
        	 systemFontCollection.put(fontName[i], i+"");
         }
         return systemFontCollection;
    }
	
	/**判断指定字体在系统所有字体的 HashMap<String, String> 中是否寻在**/
	public static boolean isFontExistInSystem(String font){
		HashMap<String, String> systemFontCollection = FontUtil.getFontPathMap();
		if(Boolean.TRUE.equals(systemFontCollection.containsKey(font))){
			return Boolean.TRUE;
		}
		else{
			return Boolean.FALSE;
		}
	}
	
	
}
