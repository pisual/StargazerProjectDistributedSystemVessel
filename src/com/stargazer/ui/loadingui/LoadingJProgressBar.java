package com.stargazer.ui.loadingui;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class LoadingJProgressBar extends JProgressBar {
	private static final long serialVersionUID = -6357363784043592552L;
	private class GyrProgressUI extends BasicProgressBarUI {
		private JProgressBar jProgressBar;
		private GyrProgressUI(JProgressBar jProgressBar) {
			this.jProgressBar = jProgressBar;
		}
		@Override
		protected void paintDeterminate(Graphics g, JComponent c) {
			jProgressBar.setForeground(new java.awt.Color(149, 148, 148));
			super.paintDeterminate(g, c);
		}
	}
	public LoadingJProgressBar() {
		this.setUI(new GyrProgressUI(this));
	}
}