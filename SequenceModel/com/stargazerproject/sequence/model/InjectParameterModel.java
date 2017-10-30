package com.stargazerproject.sequence.model;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.BigCache;
import com.stargazerproject.sequence.base.impl.BaseSequenceModel;
import com.stargazerproject.util.SerializableUtil;

@Component(value="injectParameterModel")
@Qualifier("injectParameterModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InjectParameterModel extends BaseSequenceModel{
	
	@Autowired
	@Qualifier("byteArrayCache")
	protected BigCache<String, byte[]> byteArrayCache;

	public InjectParameterModel() {
		super();
		}
	
	@Override
	public Boolean method() {
		try {
			byte[] byteArray = byteArrayCache.get(Optional.of("AcquireParameterModel")).get();
			getParamentListFromClass(SerializableUtil.deserialize(byteArray));
			return true;
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
			return false;
		}
	}
	
	/**
	 * @illustrate 从CLass文件获取参数并注入到Map列表
	 * **/
	private void getParamentListFromClass(Object object){
		try {
			Field[] valueFields = object.getClass().getDeclaredFields();
			for(Field valueField : valueFields){
				valueField.setAccessible(true);
				systemParameter.put(Optional.of(valueField.getName()), Optional.of(valueField.get(valueField.getName()).toString()));
				log.DEBUG(this, "Afresh Load Parameters ，Key : " + valueField.getName() + "  Value : " + valueField.get(valueField.getName()).toString());
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.ERROR(this, e.getLocalizedMessage());
		}
	}
	
}
