package com.stargazer.mainframe;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

public class MainFrameUIStart {
	public MainFrameUIStart() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException {
		System.out.println(System.getProperty("user.dir") + "/stargazerUI/"+"BackGround.png");
	    String file = System.getProperty("user.dir") + "/stargazerUI/"+"BackGround.png";
		StargazerMainFrame stargazerMainFrame = new StargazerMainFrame(file,System.getProperty("user.dir") + "/stargazerUI/"+"logo.png");
		stargazerMainFrame.setVisible(true);
	}
}
