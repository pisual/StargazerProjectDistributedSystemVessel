package com.stargazerproject.analysis.resources.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.EventAssembleAnalysis;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

@Component(value="eventAssembleAnalysisImplResourcesShell")
@Qualifier("eventAssembleAnalysisImplResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventAssembleAnalysisImplResourcesShell implements EventAssembleAnalysis, BaseCharacteristic<EventAssembleAnalysis>{

	@Autowired
	@Qualifier("eventInteractionCache")
	private Cache<String, String> cacheAssemble;
	
	@Override
	public Optional<EventAssembleAnalysis> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> interactionCache) {
		cacheAssemble.entrySet().get().stream().forEach(x -> interactionCache.get().put(Optional.of(x.getKey()), Optional.of(x.getValue())));
		return Optional.of(Boolean.TRUE);
	}

	@Override
	public void injectEventParameter(Optional<String> key, Optional<String> value) {
		cacheAssemble.put(key, value);
	}

}
