package com.stargazerproject.kernel.impl.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.stargazerproject.kernel.impl.KernelControlImpl;
import com.stargazerproject.kernel.impl.KernelGuideImpl;

@FixMethodOrder(MethodSorters.JVM)
public class KernelGuideClientTest {

	@Test
	public void startTest(){
		String[] args = {"Cells_Kind:Cells_Child"};
		KernelGuideImpl kernelGuideImpl = new KernelGuideImpl();
		KernelControlImpl kernelControlImpl = new KernelControlImpl();
		kernelControlImpl.start(args);
		kernelGuideImpl.differentiation()
		               .startContainer(args)
		               .loadKernelServer()
		               .loadBootSequence()
		               .loadModelServer()
		               .startKernelGuide();
	}
	
	public static void main(String[] args) {
		String[] argss = {"Cells_Kind:Cells_Child"};
		KernelGuideImpl kernelGuideImpl = new KernelGuideImpl();
		KernelControlImpl kernelControlImpl = new KernelControlImpl();
		kernelControlImpl.start(argss);
		kernelGuideImpl.differentiation()
		               .startContainer(argss)
		               .loadKernelServer()
		               .loadBootSequence()
		               .loadModelServer()
		               .startKernelGuide();
	}
}
