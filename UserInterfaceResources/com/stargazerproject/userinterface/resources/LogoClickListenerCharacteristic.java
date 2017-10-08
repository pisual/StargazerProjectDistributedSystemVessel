package com.stargazerproject.userinterface.resources;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.spring.container.impl.BeanContainer;

/**
 *操控头像单击事件
 *@author Felixerio
 */
@Component(value="logoClickListener")
@Qualifier("logoClickListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LogoClickListenerCharacteristic extends MouseAdapter implements BaseCharacteristic<MouseAdapter>{
	
	/**混合主界面**/
	private Optional<JFrame> baseFrame;
	
	public LogoClickListenerCharacteristic() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="logoClickListenerCharacteristic")
	@Lazy(true)
	public Optional<MouseAdapter> characteristic() {
		baseFrame = BeanContainer.instance().getBean(Optional.of("mainFrameJFrameCharacteristic"), Optional.class);
		return Optional.of(this);
	}
	
	@Override
	 public void mouseClicked(MouseEvent evt) {
		 if (evt.getClickCount() == 5) {
			 System.out.println("System has Exit By User");
			 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 System.exit(0);
		 } else if (evt.getClickCount() == 2) {
			 baseFrame.get().setExtendedState(Frame.ICONIFIED);
		 }
		 }

}
