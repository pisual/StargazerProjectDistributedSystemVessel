package com.stargazerproject.sequence.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.sequence.base.impl.BaseSequenceModel;

@Component(value="createBaseNodeModel")
@Qualifier("createBaseNodeModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CreateBaseNodeModel extends BaseSequenceModel{
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;

	public CreateBaseNodeModel() {
		super();
		}
	
	@Override
	public Boolean method() {
		try {
			
			if(nodeNegotiate.checkNodeExists(Optional.of("EdenCells"), Optional.of("/System/")) != Boolean.TRUE){
				nodeNegotiate.creatPersistentNode(Optional.of("EdenCells"), Optional.of("/System/"), Optional.absent());
				log.INFO(this, "Create Ephemeral Node : /System/EdenCells");
			}
			if(nodeNegotiate.checkNodeExists(Optional.of("List"), Optional.of("/Master_Cells/")) != Boolean.TRUE){
				nodeNegotiate.creatPersistentNode(Optional.of("List"), Optional.of("/Master_Cells/"), Optional.absent());
				log.INFO(this, "Create Ephemeral Node : /Master_Cells/List");
			}
			
			return true;
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
			return false;
		}
	}
	
}
