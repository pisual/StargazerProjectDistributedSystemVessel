package com.stargazerproject.userinterface.resources;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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
 * 鼠标拖拽事件
 *@author Felixerio
 */
@Component(value="mainFrameMouseMotionAdapterListener")
@Qualifier("mainFrameMouseMotionAdapterListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameMouseMotionAdapterListenerCharacteristic extends MouseMotionAdapter implements BaseCharacteristic<MouseMotionAdapter>{
	
	/**混合主界面**/
	private Optional<JFrame> baseFrame;
	/**操纵点坐标**/
	private Optional<Point> point;
	
	public MainFrameMouseMotionAdapterListenerCharacteristic() {}
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="mainFrameMouseMotionAdapterListenerCharacteristic")
	@Lazy(true)
	public Optional<MouseMotionAdapter> characteristic() {
		baseFrame = BeanContainer.instance().getBean(Optional.of("mainFrameJFrameCharacteristic"), Optional.class);
		point = BeanContainer.instance().getBean(Optional.of("mainFramePointCharacteristic"), Optional.class);
		return Optional.of(this);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = baseFrame.get().getLocation();
		baseFrame.get().setLocation(p.x + e.getX() - point.get().x, p.y+ e.getY() - point.get().y);
		}
	 
}
