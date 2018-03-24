package com.stargazerproject.userinterface.resources;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/**
 * 鼠标拖拽事件
 *@author Felixerio
 */
@Component(value="mainFrameMouseMotionAdapterListenerCharacteristic")
@Qualifier("mainFrameMouseMotionAdapterListenerCharacteristic")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameMouseMotionAdapterListenerCharacteristic extends MouseMotionAdapter implements BaseCharacteristic<MouseMotionAdapter>{
	
	/**混合主界面特征**/
	@Autowired
	@Qualifier("mainFrameJFrameCharacteristic")
	private BaseCharacteristic<JFrame> mainFrameJFrameCharacteristic;
	
	/**混合主界面**/
	private JFrame mainFrameJFrame;
	
	/**主界面坐标点特征特征 **/
	@Autowired
	@Qualifier("mainFramePointCharacteristic")
	private BaseCharacteristic<Point> mainFramePointCharacteristic;
	
	/**主界面鼠标点击主界面坐标点特征**/
	private Point mainFramePoint;
	
	public MainFrameMouseMotionAdapterListenerCharacteristic() {}
	
	@Override
	public Optional<MouseMotionAdapter> characteristic() {
		mainFrameJFrame = mainFrameJFrameCharacteristic.characteristic().get();
		mainFramePoint = mainFramePointCharacteristic.characteristic().get();
		return Optional.of(this);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = mainFrameJFrame.getLocation();
		mainFrameJFrame.setLocation(p.x + e.getX() - mainFramePoint.x, p.y+ e.getY() - mainFramePoint.y);
		}
	 
}
