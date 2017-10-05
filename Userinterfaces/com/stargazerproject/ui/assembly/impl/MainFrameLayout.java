package com.stargazerproject.ui.assembly.impl;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.stargazerproject.parameter.impl.SystemParameter;

/**
 *混合主界面布局器
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 **/
public class MainFrameLayout {
	/**主界面控制台高度**/
	private static int Main_Frame_Console_Height;
	/**MainFrameLayout单例**/
	private static MainFrameLayout mainFrameLayout;
	
	public static final MainFrameLayout getInstance(){
		if(mainFrameLayout == null){
			mainFrameLayout = new MainFrameLayout();
		}
		return mainFrameLayout;
	}
	
	private MainFrameLayout() {
		Main_Frame_Console_Height = SystemParameterCahce.getInstance().getInteger("Main_Frame_Console_Height");
	}
	
	public void initMainFrameLayout(JFrame jFrame,JScrollPane jScrollPane, JScrollPane rightJScrollPane) {
		GroupLayout layout = new GroupLayout(jFrame.getContentPane());
		jFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			  .addGap(35, 35, 35)
			  .addComponent(true,jScrollPane,GroupLayout.DEFAULT_SIZE, 610, 610)
			 ) 
				.addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
						  .addComponent(true,jScrollPane,GroupLayout.DEFAULT_SIZE, 610, 610)
						   .addGap(565, 565, 565)
					 .addComponent(true,rightJScrollPane,GroupLayout.DEFAULT_SIZE, 610, 610)
						 ) 
				);
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			   .addGap(55, 55, 55)
			  .addComponent(jScrollPane,GroupLayout.PREFERRED_SIZE,Main_Frame_Console_Height,GroupLayout.PREFERRED_SIZE))
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
					   .addGap(55, 55, 55)
					  .addComponent(rightJScrollPane,GroupLayout.PREFERRED_SIZE,Main_Frame_Console_Height,GroupLayout.PREFERRED_SIZE)
					   ) );
	}
}
