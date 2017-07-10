package com.stargazerproject.ui.assembly.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.stargazerproject.ui.assembly.BaseAssembly;

/**
 * 主要操作界面
 * 
 *@author Felixerio
 */
@Component
public class BaseFrame extends JFrame implements BaseAssembly {
	private static final long serialVersionUID = -2355092246173538052L;
	
	private Integer FRAME_SIZE_WIDTH;
	private Integer FRAME_SIZE_HIGTH;

	@Autowired
	private BaseFrame(@Value("#{systemParameter.get('FRAME_SIZE_WIDTH')}") Integer frameSizeWidth,
			          @Value("#{systemParameter.get('FRAME_SIZE_HIGTH')}") Integer frameSizeHigth) {
		FRAME_SIZE_WIDTH = frameSizeWidth;
		FRAME_SIZE_HIGTH = frameSizeHigth;
	}

	@Override
	public void initialization() {
		setSize(FRAME_SIZE_WIDTH,FRAME_SIZE_HIGTH);
		((JPanel) getContentPane()).setOpaque(Boolean.TRUE);
		setUndecorated(true);
	}
	
	@Override
	public String toString() {
		return "FRAME_SIZE_WIDTH: "+FRAME_SIZE_WIDTH+"  FRAME_SIZE_HIGTH:"+FRAME_SIZE_HIGTH;
	}
}
