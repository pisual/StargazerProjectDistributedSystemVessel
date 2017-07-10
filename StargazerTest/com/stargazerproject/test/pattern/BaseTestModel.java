package com.stargazerproject.test.pattern;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class BaseTestModel implements WorkInTest{
	
	@Autowired
	@Qualifier("object")
	private Object object;

	@Override
	@Test
	public void interfaceInitialize() {
		System.out.println(object);
	}

}
