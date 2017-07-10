package com.stargazerprojec.cache.element.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.cache.base.impl.PermanentCacheImpl;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;

/** 
 *  @name 基础映射单元
 *  @illustrate 替代系统的基础Map，每一次注入都是全新的对象
 *  @param <K> 缓存的Key值类型
 *  @param <V> 缓存的Value类型
 *  @author Felixerio
 *  **/
@Component
@Qualifier("baseMapUnit")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseMapUnit<K, V> extends PermanentCacheImpl<K, V> implements StanderCharacteristicShell<Map<K, V>>, Cache<K, V>{
	
	/** @construction 初始化构造 **/
	public BaseMapUnit() {}
	
	@Override
	public void initialize(Optional<Map<K, V>> mapArg) {
		map = mapArg.get();
	}
}
