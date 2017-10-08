package com.stargazer.ui.loadingui;

import java.awt.Font;

import javax.swing.JLabel;

public class LoadingProgressInfo extends JLabel {
	private static final long serialVersionUID = -7016844370715643376L;
	public LoadingProgressInfo() {
		this.setFont(new Font("Copperplate", Font.BOLD, 13));
		this.setForeground(new java.awt.Color(255, 255, 255)); 
	}
}
