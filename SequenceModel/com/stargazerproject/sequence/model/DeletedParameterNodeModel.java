package com.stargazerproject.sequence.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.sequence.base.impl.BaseSequenceModel;

@Component(value="deletedParameterNodeModel")
@Qualifier("deletedParameterNodeModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DeletedParameterNodeModel extends BaseSequenceModel{
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;

	public DeletedParameterNodeModel() {
		super();
		}
	
	@Override
	public Boolean method() {
		try {
			nodeNegotiate.deleteNode(aggregateRootCache.get(Optional.of("This_Cells_UUID")), Optional.of("/System/EdenCells/"));
			return true;
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
			return false;
		}
	}
	
}
