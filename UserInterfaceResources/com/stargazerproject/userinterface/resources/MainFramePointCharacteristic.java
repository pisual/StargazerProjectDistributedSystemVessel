package com.stargazerproject.userinterface.resources;

import java.awt.Point;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.characteristic.BaseCharacteristic;

@Component(value="mainFramePoint")
@Qualifier("mainFramePoint")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFramePointCharacteristic implements BaseCharacteristic<Point>{

	private Point origin = new Point();
	
	public MainFramePointCharacteristic() {}
	
	@Override
	@Bean(name="mainFramePointCharacteristic")
	@Lazy(true)
	public Optional<Point> characteristic() {
		return Optional.of(origin);
	}

}
