package com.stargazerproject.sequence.resources;

import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.sequence.base.impl.BaseSequenceModel;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="cellsNodeParameterControlModel")
@Qualifier("cellsNodeParameterControlModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CellsNodeParameterControlModel extends BaseSequenceModel{
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;
	
	@Autowired
	@Qualifier("systemParameterCahce")
	protected Cache<String,String> systemParameterCahce;

	public CellsNodeParameterControlModel() {
		super();
		waitMethod();
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean method() {
		try {
			Optional<PathChildrenCacheListener> negotiateParametersInjectMonitoringNodePathChildrenCacheListener = BeanContainer.instance().getBean(Optional.of("negotiateParametersInjectMonitoringNodePathChildrenCacheListenerCharacteristic"), Optional.class);
			nodeNegotiate.registeredCirculationWatcher(Optional.of("/System/EdenCells"), Optional.of(""), negotiateParametersInjectMonitoringNodePathChildrenCacheListener);
			systemParameterCahce.put(Optional.of("CellsNodeParameterControlModel"), Optional.of("Continue"));
			return true;
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
			return false;
		}
	}
	
}
