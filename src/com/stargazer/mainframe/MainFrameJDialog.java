package com.stargazer.mainframe;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class MainFrameJDialog extends JDialog {
	private static final long serialVersionUID = -3720555214012386633L;

	public MainFrameJDialog() {
		((JPanel) this.getContentPane()).setOpaque(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "/pisualcells" + "/ui/"+ "ban.png"));
	}
}
