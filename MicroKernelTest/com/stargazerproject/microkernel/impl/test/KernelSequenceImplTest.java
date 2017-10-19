package com.stargazerproject.microkernel.impl.test;

import com.google.common.base.Optional;
import com.stargazerproject.microkernel.base.impl.BaseKernelModel;
import com.stargazerproject.microkernel.impl.KernelSequenceImpl;

public class KernelSequenceImplTest {
	
	public static void main(String[] args) {
		
		KernelSequenceImpl k = new KernelSequenceImpl();
		k.addGroup(Optional.of("TestGroup1")).addModel(Optional.of(new BaseKernelModel()))
				                             .addModel(Optional.of(new BaseKernelModel()))
				                             .addModel(Optional.of(new BaseKernelModel())).next()
		 .addGroup(Optional.of("TestGroup2")).addModel(Optional.of(new BaseKernelModel()))
		                                     .addModel(Optional.of(new BaseKernelModel()));
		k.startSequence();
	}
}
