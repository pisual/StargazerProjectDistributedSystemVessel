package com.stargazer.mainframe;

import javax.swing.JScrollPane;

import com.stargazer.ui.util.ConsoleTextArea;

public class MainFrameJScrollPane extends JScrollPane{
	private static final long serialVersionUID = -5454456364291306581L;
	public JScrollPane mainFrameJScrollPane(ConsoleTextArea consoleTextArea) {
		JScrollPane jScrollPane = new JScrollPane(consoleTextArea);
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		return jScrollPane;
	}
}
