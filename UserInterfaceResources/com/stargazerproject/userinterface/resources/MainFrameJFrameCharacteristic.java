package com.stargazerproject.userinterface.resources;

import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.annotation.description.NeedInject;
import com.stargazerproject.characteristics.Characteristic;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.resources.userinterface.UserinterfaceResource;
import com.stargazerproject.util.UIUtil;
import com.sun.awt.AWTUtilities;

/** 
 *  @name 主界面组件
 *  @illustrate 实现主界面的基础功能
 *  @author Felixerio
 *  **/
@Component(value="mainFrameJFrameCharacteristic")
@Qualifier("mainFrameJFrameCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameJFrameCharacteristic implements BaseCharacteristic<JFrame>{
		
	/** @name 主界面宽 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Size_Width;
	
	/** @name 主界面高 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Size_Height;
	
	/** @name 主界面使用者头像 **/
	@NeedInject(type="SystemParametersCache")
	private static String Kernel_UserInterface_MainFrame_Icon_UserHeadPortrait;
	
//	@Autowired
//	@Qualifier("componentsCharacteristic")
//	private Characteristic characteristic;
	
	private JFrame jFrame = new JFrame();
	
	@Override
	public Optional<JFrame> characteristic() {
//		synchronized(this){
//			if(characteristic.singleInitializationStata().get() == Boolean.FALSE){
				initialization();
//				characteristic.singleInitializationComplete();
//			}
//		}
		return Optional.of(jFrame);
	}

	private void initialization() {
		initializationJFrame();
		initializationJFrameIcon();
	}
	
	private void initializationJFrame(){
		jFrame.setSize(Integer.parseInt(Kernel_UserInterface_MainFrame_Size_Width),Integer.parseInt(Kernel_UserInterface_MainFrame_Size_Height));
		((JPanel)jFrame.getContentPane()).setOpaque(Boolean.TRUE);
		jFrame.setUndecorated(true);
		AWTUtilities.setWindowOpaque(jFrame, false);
		UIUtil.changeFrameToCenter(jFrame);
	}
	
	private void initializationJFrameIcon(){
		URL url = UserinterfaceResource.class.getResource(Kernel_UserInterface_MainFrame_Icon_UserHeadPortrait);
		jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
	}
	
}
