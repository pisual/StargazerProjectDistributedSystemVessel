package com.stargazerproject.sequence.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.sequence.base.impl.BaseSequenceModel;
import com.stargazerproject.util.Sequence;

/** 
 *  @name Cell生成ID序列组
 *  @illustrate Cells生成序列的第一步，生成UUID组，UUID组的格式为  XXX:XXX,使用：来进行组分割
 *  @author Felixerio
 *  **/
@Component(value="initializationCellsGroupModel")
@Qualifier("initializationCellsGroupModel")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InitializationCellsGroupModel extends BaseSequenceModel{

	public InitializationCellsGroupModel() {super();}
	
	@Override
	public Boolean method() {
		String UUID = Sequence.getUUID();
		if(systemParameter.get(Optional.of("Cells_Group")).equals(Optional.absent())){
			systemParameter.put(Optional.of("Cells_Group"), Optional.of(UUID + ":"));
		}
		else{
			String originalParameter = systemParameter.get(Optional.of("Cells_UUID")).get();
			systemParameter.put(Optional.of("Cells_Group"), Optional.of(originalParameter + ":" + UUID));
		}
		aggregateRootCache.put(Optional.of("This_Cells_UUID"), Optional.of(UUID));
		log.INFO(this, "Cells_Group Initialization: " + systemParameter.get(Optional.of("Cells_Group")).get());
		return Boolean.TRUE;
	}
	
}
