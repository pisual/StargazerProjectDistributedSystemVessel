package com.stargazer.ui.loadingui;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class LoadingJDialog extends JDialog {
	private static final long serialVersionUID = -8468427950400962429L;
	public LoadingJDialog() {
		((JPanel) this.getContentPane()).setOpaque(false);
	}
}
