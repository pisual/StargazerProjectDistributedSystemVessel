package com.stargazerproject.ui.assembly.impl;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.stargazerproject.parameter.impl.SystemParameter;
import com.stargazerproject.ui.util.ColorUtil;

/**
 *JScrollPaneUI 用于覆写横滚动条的整体UI设计
 * 
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 */
public class HorizontalScrollBarUI extends BasicScrollBarUI {
	/**滚动条上部把手背景颜色
	 *  ▲  ←
	 * |||
	 * |||
	 *  ▽
	 * **/
	private static String JScrollPaneUI_Down_PNG;
	/**滚动条下部把手背景颜色
	 *  △ 
	 * |||
	 * |||
	 *  ▼ ←
	 * **/
	private static String JScrollPaneUI_UP_PNG;
	/**滚动条闸道背景颜色 RGB色组**/
	private static Color JScrollPaneUI_TrackColor;
	/**滚动条闸道背景颜色 RGB色组**/
	private static Color JScrollPaneUI_Scroll_Border_Color;
	
	public HorizontalScrollBarUI() {
		JScrollPaneUI_Down_PNG = SystemParameterCahce.getInstance().getString("JScrollPaneUI_Down_PNG");
		JScrollPaneUI_UP_PNG = SystemParameterCahce.getInstance().getString("JScrollPaneUI_UP_PNG");
		JScrollPaneUI_TrackColor  = ColorUtil.getColorFromSystemmParanment("JScrollPaneUI_TrackColor");
		JScrollPaneUI_Scroll_Border_Color  = ColorUtil.getColorFromSystemmParanment("JScrollPaneUI_Scroll_Border_Color");
	}
			
	@Override
	protected void configureScrollBarColors() {
		trackColor = JScrollPaneUI_TrackColor;
	}
	
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		g.translate(thumbBounds.x, thumbBounds.y); 
		g.setColor(JScrollPaneUI_Scroll_Border_Color);
		g.drawRoundRect(0, 0, 8, thumbBounds.height-5, 5, 5); 
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Graphics2D g2 = (Graphics2D) g;
		g2.addRenderingHints(rh);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
		g2.fillRoundRect(0, 0, 8, thumbBounds.height-5, 5, 5);
	}
	
	@Override
	protected JButton createIncreaseButton(int orientation) {
		JButton button = new JButton(new ImageIcon(JScrollPaneUI_Down_PNG));
		button.setBorder(null);
		return button;
	}
	
	@Override
	protected JButton createDecreaseButton(int orientation) {
		JButton button = new JButton(new ImageIcon(JScrollPaneUI_UP_PNG));
		button.setBorder(null);
		return button;
	}
}
