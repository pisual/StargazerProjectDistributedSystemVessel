package com.stargazerproject.userinterface.resources;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
 * 获取鼠标点击位置事件
 *@author Felixeri
 */
@Component(value="mouseAdapterListener")
@Qualifier("mouseAdapterListener")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrameMouseAdapterListenerCharacteristic extends MouseAdapter implements BaseCharacteristic<MouseAdapter>{
	
	private Optional<Point> point;
	
	public MainFrameMouseAdapterListenerCharacteristic() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean(name="mainFrameMouseAdapterListenerCharacteristic")
	@Lazy(true)
	public Optional<MouseAdapter> characteristic() {
		point = BeanContainer.instance().getBean(Optional.of("mainFramePointCharacteristic"), Optional.class);
		return Optional.of(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		point.get().x = e.getX();
		point.get().y = e.getY();
		}

}
