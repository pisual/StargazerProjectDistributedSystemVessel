package com.stargazerproject.ui.assembly.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.stargazerproject.log.collocation.impl.LocalityLog;
import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.resources.userinterface.UserinterfaceResource;

/**
 *主界面背景
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class MainFrameBackgroundJlabel extends GradientLoadInterface{
	private static final long serialVersionUID = 4040037367225492924L;
	/**单例模式**/
	private static MainFrameBackgroundJlabel mainFrameBackgroundJlabel;
	
	public static MainFrameBackgroundJlabel getInstance(String background){
		if(mainFrameBackgroundJlabel == null){
			mainFrameBackgroundJlabel = new MainFrameBackgroundJlabel();
			try {
				mainFrameBackgroundJlabel.initMainFrameBackgroundJlabel(ImageIO.read(UserinterfaceResource.class.getResource(background)));
			} catch (IOException e) {
				LocalityLog.getInstance().ERROR(MainFrameBackgroundJlabel.class, e.getMessage());
			}
		}
		return mainFrameBackgroundJlabel;
	}
	
	private MainFrameBackgroundJlabel() {
		super(SystemParameterCahce.getInstance().getString("MAIN_INTERFACE_BACKGROUND"));
	}
	
	private void initMainFrameBackgroundJlabel(BufferedImage image){
		mainFrameBackgroundJlabel.setBounds(0, 0,image.getWidth(), image.getHeight());
	}
}
