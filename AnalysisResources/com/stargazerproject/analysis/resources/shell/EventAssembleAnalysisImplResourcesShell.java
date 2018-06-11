package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.EventAssembleAnalysisExtend;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

public class EventAssembleAnalysisImplResourcesShell implements EventAssembleAnalysisExtend, BaseCharacteristic<EventAssembleAnalysisExtend>{

	@Override
	public Optional<EventAssembleAnalysisExtend> characteristic() {
		return Optional.of(this);
	}
	
	@Override
	public Optional<Boolean> analysis(Optional<Cache<String, String>> interactionCache) {
		
		return null;
	}

	@Override
	public void injectEventParameter(Optional<String> Key, Optional<String> value) {
		
	}

	@Override
	public void injectEventName(Optional<String> value) {
		
	}

}
