package com.stargazerproject.userinterface.resources;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/**
 * 获取鼠标点击位置事件
 *@author Felixeri
 */
@Component(value="mainFrameMouseAdapterListenerCharacteristic")
@Qualifier("mainFrameMouseAdapterListenerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameMouseAdapterListenerCharacteristic extends MouseAdapter implements BaseCharacteristic<MouseAdapter>{
	
	/**主界面坐标点特征特征 **/
	@Autowired
	@Qualifier("mainFramePointCharacteristic")
	private BaseCharacteristic<Point> mainFramePointCharacteristic;
	
	/**主界面鼠标点击主界面坐标点特征**/
	private Point mainFramePoint;

	
	public MainFrameMouseAdapterListenerCharacteristic() {
		super();
	}
	
	@Override
	public Optional<MouseAdapter> characteristic() {
		mainFramePoint = mainFramePointCharacteristic.characteristic().get();
		return Optional.of(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mainFramePoint.x = e.getX();
		mainFramePoint.y = e.getY();
		}

}
