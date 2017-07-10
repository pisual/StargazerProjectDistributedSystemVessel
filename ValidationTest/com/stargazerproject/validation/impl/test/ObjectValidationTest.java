package com.stargazerproject.validation.impl.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stargazerproject.test.pattern.WorkInTest;
import com.stargazerproject.validation.ObjectCheck;
import com.stargazerproject.validation.configuration.GroupValidationDIConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GroupValidationDIConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class ObjectValidationTest implements WorkInTest{
	
	@Autowired
	@Qualifier("objectValidation")
	private ObjectCheck objectCheck;

	@Override
	@Test
	public void interfaceInitialize() {
		System.out.println(objectCheck);
	}

}
