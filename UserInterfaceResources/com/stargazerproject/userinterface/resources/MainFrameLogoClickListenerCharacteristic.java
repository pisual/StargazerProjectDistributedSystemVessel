package com.stargazerproject.userinterface.resources;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/**
 *操控头像单击事件
 *@author Felixerio
 */
@Component(value="mainFrameLogoClickListenerCharacteristic")
@Qualifier("mainFrameLogoClickListenerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameLogoClickListenerCharacteristic extends MouseAdapter implements BaseCharacteristic<MouseAdapter>{
	
	/**混合主界面特征**/
	@Autowired
	@Qualifier("mainFrameJFrameCharacteristic")
	private BaseCharacteristic<JFrame> mainFrameJFrameCharacteristic;
	
	/**混合主界面**/
	private JFrame mainFrameJFrame;
	
	public MainFrameLogoClickListenerCharacteristic() {
		super();
	}
	
	@Override
	public Optional<MouseAdapter> characteristic() {
		mainFrameJFrame = mainFrameJFrameCharacteristic.characteristic().get();
		return Optional.of(this);
	}
	
	@Override
	 public void mouseClicked(MouseEvent evt) {
		 if (evt.getClickCount() == 5) {
			 System.out.println("System Exit");
			 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 System.exit(0);
		 } else if (evt.getClickCount() == 2) {
			 mainFrameJFrame.setExtendedState(Frame.ICONIFIED);
		 }
		 }

}
