package com.stargazerproject.userinterface.resources;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/**
 *混合主界面布局器
 *@author Felixerio
 **/
@Component(value="mainFrameLayoutCharacteristic")
@Qualifier("mainFrameLayoutCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameLayoutCharacteristic implements BaseCharacteristic<MainFrameLayoutCharacteristic>{
	
	/** @name 主界面控制台高度**/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Console_Size_Height;
	
	/**混合主界面特征**/
	@Autowired
	@Qualifier("mainFrameJFrameCharacteristic")
	private BaseCharacteristic<JFrame> mainFrameJFrameCharacteristic;
	
	/**混合主界面**/
	private JFrame mainFrameJFrame;
	
	/**主界面左面滚动条特征**/
	@Autowired
	@Qualifier("mainFrameJScrollPaneCharacteristic")
	private BaseCharacteristic<JScrollPane> mainFrameJScrollPaneCharacteristic;
	
	/**主界面左面滚动条**/
	private JScrollPane mainFrameJScrollPane;
	
	/**主界面右面滚动条特征**/
	@Autowired
	@Qualifier("mainFrameRightJScrollPaneCharacteristic")
	private BaseCharacteristic<JScrollPane> mainFrameRightJScrollPaneCharacteristic;

	/**主界面右面滚动条**/
	private JScrollPane mainFramerightJScrollPane;
	
	@Override
	public Optional<MainFrameLayoutCharacteristic> characteristic() {
		mainFrameJFrame = mainFrameJFrameCharacteristic.characteristic().get();
		mainFrameJScrollPane = mainFrameJScrollPaneCharacteristic.characteristic().get();
		mainFramerightJScrollPane = mainFrameRightJScrollPaneCharacteristic.characteristic().get();
		initialization(mainFrameJFrame, mainFrameJScrollPane, mainFramerightJScrollPane);
		return Optional.of(this);
	}
	
	private void initialization(JFrame jFrame,JScrollPane jScrollPane, JScrollPane rightJScrollPane) {
		GroupLayout layout = new GroupLayout(jFrame.getContentPane());
		jFrame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			  .addGap(35, 35, 35)
			  .addComponent(true,jScrollPane,GroupLayout.DEFAULT_SIZE, 610, 610)) 
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			  .addComponent(true,jScrollPane,GroupLayout.DEFAULT_SIZE, 610, 610)
			  .addGap(565, 565, 565)
			  .addComponent(true,rightJScrollPane,GroupLayout.DEFAULT_SIZE, 610, 610)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			  .addGap(55, 55, 55)
			  .addComponent(jScrollPane,GroupLayout.PREFERRED_SIZE,Integer.parseInt(Kernel_UserInterface_MainFrame_Console_Size_Height),GroupLayout.PREFERRED_SIZE))
			  .addGroup(GroupLayout.Alignment.LEADING,layout.createSequentialGroup()
			  .addGap(55, 55, 55)
			  .addComponent(rightJScrollPane,GroupLayout.PREFERRED_SIZE,Integer.parseInt(Kernel_UserInterface_MainFrame_Console_Size_Height),GroupLayout.PREFERRED_SIZE)));
	}
}
