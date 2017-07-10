package com.stargazerproject.resources.systemparameter.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stargazerproject.resources.parameter.StargazerProjectParameterList;
import com.stargazerproject.test.pattern.WorkInTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={StargazerProjectParameterList.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class SystemParameterListTest implements WorkInTest{
	
	@Autowired
	@Qualifier("stargazerProjectParameterList")
	private StargazerProjectParameterList stargazerProjectParameterList;

	@Override
	@Test
	public void interfaceInitialize() {
		System.out.println(stargazerProjectParameterList);
	}

}
