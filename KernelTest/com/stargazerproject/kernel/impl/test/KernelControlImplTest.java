package com.stargazerproject.kernel.impl.test;

import org.junit.Test;

import com.stargazerproject.kernel.impl.KernelControlImpl;

public class KernelControlImplTest {

	@Test
	public void mainTest(){
		String[] args = {"Cells_UUID:111","Kernel:Cells"};
		KernelControlImpl kernelControlImpl = new KernelControlImpl();
		kernelControlImpl.start(args);
	}
	
	public static void main(String[] args) {
		String[] argss = {"Cells_UUID:111","Kernel:Cells"};
		KernelControlImpl kernelControlImpl = new KernelControlImpl();
		kernelControlImpl.start(argss);
	}
}
