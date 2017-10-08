package com.stargazerproject.userinterface.resources;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import com.google.common.base.Optional;
import com.stargazerproject.resources.userinterface.UserinterfaceResource;

public class GradientLoadInterface extends JLabel implements ActionListener {
	private static final long serialVersionUID = -3936803863823364655L;
	private int ANIMATION_FRAMES = 100;
	private int ANIMATION_INTERVAL = 10;
	private int startNumber = 0;
	private int frameIndex;
	private Timer timer;
	public BufferedImage image = null;
	
	public GradientLoadInterface(String imgPath) {
		super(new ImageIcon(imgPath));
		try {
			image = ImageIO.read(UserinterfaceResource.class.getResource(imgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void readImage(Optional<String> imagePath){
		try {
			image = ImageIO.read(UserinterfaceResource.class.getResource(imagePath.get()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frameIndex++;
		if (frameIndex >= ANIMATION_FRAMES)
			closeTimer();
		else
			repaint();
	}

	public void paintComponent(Graphics g) {
		if (null == image) {
			return;
		}
		g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
	}

	@Override
	public void paint(Graphics g) {
		if (startNumber == 0) {
			if (isAnimating()) {
				float alpha = (float) frameIndex / (float) ANIMATION_FRAMES;
				Graphics2D g2d = (Graphics2D) g;
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
				super.paint(g2d);
			} else {
				frameIndex = 0;
				timer = new Timer(ANIMATION_INTERVAL, this);
				timer.start();
			}
		} else {
			super.paint(g);
		}
	}

	private boolean isAnimating() {
		return timer != null && timer.isRunning();
	}

	private void closeTimer() {
		if (isAnimating()) {
			timer.stop();
			frameIndex = 0;
			timer = null;
			startNumber = 1;
		}
	}
}
