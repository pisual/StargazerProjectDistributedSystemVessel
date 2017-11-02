package com.stargazerproject.sequence.resources.shell;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceMethod;
import com.stargazerproject.sequence.SequenceTransaction;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="sequenceResourcesShell")
@Qualifier("sequenceResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SequenceResourcesShell implements Sequence, BaseCharacteristic<Sequence>{
	
	private Multimap<String, SequenceMethod> scoreMultimap = LinkedHashMultimap.create(); 
	private Map<String, Cache<String,String>> aggregateRootCacheMap = new HashMap<String, Cache<String,String>>();
	
	public SequenceResourcesShell() {}
	
	@Override
	@Bean(name="sequenceResourcesCharacteristic")
	@Lazy(true)
	public Optional<Sequence> characteristic() {
		return Optional.of(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SequenceTransaction addModel(Optional<String> sequenceGroup, Optional<SequenceMethod> sequenceMethod) {
		if(null == aggregateRootCacheMap.get(sequenceGroup.get())){
			Cache<String,String> cache = BeanContainer.instance().getBean(Optional.of("objectParameterCache"), Cache.class);
			aggregateRootCacheMap.put(sequenceGroup.get(), cache);
			sequenceMethod.get().setAggregateRootCache(Optional.of(cache));
		}
		else{
			Cache<String,String> cache = aggregateRootCacheMap.get(sequenceGroup.get());
			sequenceMethod.get().setAggregateRootCache(Optional.of(cache));
		}
		scoreMultimap.put(sequenceGroup.get(), sequenceMethod.get());
		return this;
	}

	@Override
	public void startSequence(Optional<String> sequenceGroup) {
		scoreMultimap.get(sequenceGroup.get()).stream().forEach(e -> e.method());
	}
	
	@Override
	public void clear(Optional<String> sequenceGroup) {
		scoreMultimap.removeAll(sequenceGroup.get());
	}
	
}