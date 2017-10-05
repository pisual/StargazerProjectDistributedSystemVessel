package com.stargazerproject.ui.assembly.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.resources.userinterface.UserinterfaceResource;

/**
 *加载进度页面背景
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class LoadingFrameBackgroundJlabel extends GradientLoadInterface{
	private static final long serialVersionUID = 4040037367225492924L;
	
	/**单例模式**/
	private static LoadingFrameBackgroundJlabel loadingFrameBackgroundJlabel;
	
	public static LoadingFrameBackgroundJlabel getInstance(String background){
		if(loadingFrameBackgroundJlabel == null){
			ImageIcon backgroundImage = new ImageIcon(background);
			loadingFrameBackgroundJlabel = new LoadingFrameBackgroundJlabel(backgroundImage);
			try {
				loadingFrameBackgroundJlabel.initMainFrameBackgroundJlabel(ImageIO.read(UserinterfaceResource.class.getResource(background)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return loadingFrameBackgroundJlabel;
	}
	
	private LoadingFrameBackgroundJlabel(ImageIcon background) {
		super(SystemParameterCahce.getInstance().getString("LOADING_INTERFACE_BACKGROUND"));
	}
	private void initMainFrameBackgroundJlabel(BufferedImage image){
		loadingFrameBackgroundJlabel.setBounds(0, 0,image.getWidth(), image.getHeight());
	}
}
