package com.stargazer.mainframe;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.stargazer.listener.LogoClickListener;

public class MainFrameLogoJlabel extends JLabel {
	private static final long serialVersionUID = 5010683231088848230L;

	public JLabel mainFrameLogoJlabel(ImageIcon backgroundLogo,Point origin,MainFrameJDialog mainFrameJDialog) {
		LogoClickListener logoClickListener = new LogoClickListener(mainFrameJDialog);
		JLabel jLabel = new JLabel(backgroundLogo);
		jLabel.setBounds(1385, 20, 100, 100);
		jLabel.addMouseListener(logoClickListener);
		jLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		jLabel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = mainFrameJDialog.getLocation();
				mainFrameJDialog.setLocation(p.x + e.getX() - origin.x, p.y+ e.getY() - origin.y);}});
		return jLabel;
	}
}
