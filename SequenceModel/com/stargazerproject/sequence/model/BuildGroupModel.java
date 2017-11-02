package com.stargazerproject.sequence.model;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.sequence.base.impl.BaseSequenceModel;

@Component(value="buildGroupModel")
@Qualifier("buildGroupModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BuildGroupModel extends BaseSequenceModel{
	
	@Autowired
	@Qualifier("nodenNegotiate")
	private Negotiate nodeNegotiate;

	public BuildGroupModel() {
		super();
		}
	
	@Override
	public Boolean method() {
		try {
			nodeNegotiate.creatEphemeralNode(aggregateRootCache.get(Optional.of("This_Cells_UUID")), Optional.of("/Master_Cells_List/"), Optional.absent());
			
//			while(true){
//				TimeUnit.SECONDS.sleep(randomInt(1,1));
//				if(nodeNegotiate.creatLock(aggregateRootCache.get(Optional.of("This_Cells_UUID")), Optional.of("/Master_Cells_List/"))){
//					List<String> pathNodeList = nodeNegotiate.getPathNode(Optional.of(""), Optional.of("/Master_Cells_List/"));
//					String origin = Sequence.getUUID();	
//					for(String pathNode : pathNodeList){
//						if(matchingAlgorithm(origin, pathNode, 2).equals(Boolean.TRUE)){
//							System.out.println("Find Mathcing Node : " + pathNode);
//							return true;
//						}
//					}
//					
//				}
//			}
			return true;
			
		} catch (Exception e) {
			log.ERROR(this, e.getMessage());
			return false;
		}
	}
	
	private int randomInt(int min, int max){
		return new Random().nextInt(max)%(max-min+1) + min;
	}
	
	private Boolean matchingAlgorithm(String origin, String target, int matchLenght){
		if(origin.substring(origin.length() - matchLenght + 1).equals(target.length() - matchLenght +1)){
			return Boolean.TRUE;
		}
		else{
			return Boolean.FALSE;
		}
	}
	
}
