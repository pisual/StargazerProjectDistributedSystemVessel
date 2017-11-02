package com.stargazerproject.sequence.model;

import org.apache.curator.framework.api.CuratorListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.sequence.base.impl.BaseSequenceModel;
import com.stargazerproject.spring.container.impl.BeanContainer;

@Component(value="acquireParameterModel")
@Qualifier("acquireParameterModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AcquireParameterModel extends BaseSequenceModel{
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;

	public AcquireParameterModel() {
		super();
		waitMethod();
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean method() {
		try {
			nodeNegotiate.creatEphemeralNode(aggregateRootCache.get(Optional.of("This_Cells_UUID")), Optional.of("/System/EdenCells/"), Optional.absent());
			Optional<CuratorListener> negotiateNodeCuratorListenerCharacteristic = BeanContainer.instance().getBean(Optional.of("negotiateInjectParameterTreeCacheListenerCharacteristic"), Optional.class);
			nodeNegotiate.registeredSingleWatcher(aggregateRootCache.get(Optional.of("This_Cells_UUID")), Optional.of("/System/EdenCells/"), negotiateNodeCuratorListenerCharacteristic);
			return true;
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
			return false;
		}
	}
	
}
