package com.stargazer.mainframe;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class MainFrameBackgroundJlabel extends JLabel {
	private static final long serialVersionUID = -5958603846596081529L;

	public JLabel mainFrameBackgroundJlabel(ImageIcon background) {
		JLabel jLabel = new JLabel(background);
		jLabel.setBounds(0, 0,background.getIconWidth(), background.getIconHeight());
		return jLabel;
	}

}
