package com.stargazer.mainframe;

import java.awt.Point;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;

import com.stargazer.ui.util.ConsoleTextArea;
import com.stargazer.ui.util.UIUtil;

/**
 * 加载进度界面
 * 
 * @author felixsion
 * **/
public class StargazerMainFrame extends MainFrameJDialog {
	private JScrollPane jScrollPane;
	private Point origin;
	private static final long serialVersionUID = -781617113970117755L;
	/**
	 * 初始化
	 * @author felixsion
	 * 
	 * **/
	public StargazerMainFrame(String loadingBackGroundFile,String logo) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		origin = new Point();
        MainFrameJScrollPane mainFrameJScrollPane = new MainFrameJScrollPane();
        MainFrameLogoJlabel mainFrameLogoJlabel = new MainFrameLogoJlabel();
        MainFrameBackgroundJlabel mainFrameBackgroundJlabel = new MainFrameBackgroundJlabel();
		jScrollPane = mainFrameJScrollPane.mainFrameJScrollPane(new ConsoleTextArea());
		JLabel label = mainFrameBackgroundJlabel.mainFrameBackgroundJlabel(new ImageIcon(loadingBackGroundFile));
		JLabel logoJlibel = mainFrameLogoJlabel.mainFrameLogoJlabel(new ImageIcon(logo),origin,this);
	//	this.frameLayout();
		this.setUndecorated(true);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		this.getLayeredPane().add(logoJlibel, 10); 
        this.setSize(new ImageIcon(loadingBackGroundFile).getIconWidth(), new ImageIcon(loadingBackGroundFile).getIconHeight());
        UIUtil.changeFrameToCenter(this);
	}

	/**窗口布局**/
	public void frameLayout() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane, GroupLayout.Alignment.TRAILING,GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				 GroupLayout.Alignment.LEADING)
				.addGroup(
				 GroupLayout.Alignment.TRAILING,
			     layout.createSequentialGroup()
			    .addContainerGap(265, Short.MAX_VALUE)
				.addComponent(jScrollPane,GroupLayout.PREFERRED_SIZE, 617,GroupLayout.PREFERRED_SIZE)));
	}
}