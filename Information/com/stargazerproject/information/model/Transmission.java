package com.stargazerproject.information.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.stargazerproject.analysis.TransmissionAnalysis;
import com.stargazerproject.transaction.Entity;
import com.stargazerproject.transaction.base.impl.ID;
import com.stargazerproject.util.CloneUtil;

public class Transmission extends ID implements Entity<Transmission>{

	private TransmissionContent transmissionContent;
	
	/** @construction 初始化构造 **/
	public Transmission(Optional<String> idArg) {
		super(idArg);
	}
	
	public void analysis(Optional<TransmissionAnalysis> transmissionAnalysis){
		transmissionContent.analysis(transmissionAnalysis);
	}
	
	@Override
	public Transmission clone() throws CloneNotSupportedException {
		return (Transmission) CloneUtil.deepClone(Optional.of(this));
	}

	@Override
	public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("ID", super.IDSequence().get()).toString();
	}
	
}
