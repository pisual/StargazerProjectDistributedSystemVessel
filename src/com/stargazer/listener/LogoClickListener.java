package com.stargazer.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.stargazer.mainframe.MainFrameJDialog;

public class LogoClickListener extends MouseAdapter {
	private MainFrameJDialog mainFrameJDialog;
	public LogoClickListener(MainFrameJDialog mainFrameJDialog) {
		this.mainFrameJDialog = mainFrameJDialog;
	}
	 public void mouseClicked(MouseEvent evt) {
		 if (evt.getClickCount() == 5) {
			 System.out.println("System has Exit By User");
			 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 System.exit(0);
		 } else if (evt.getClickCount() == 2) {
			 mainFrameJDialog.setSize(1920, 1080);
		 }
		 }
}
