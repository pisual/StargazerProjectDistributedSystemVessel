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
import com.stargazerproject.validation.NumericalCheck;
import com.stargazerproject.validation.configuration.GroupValidationDIConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GroupValidationDIConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class IntegerValidationTest implements WorkInTest{
	
	@Autowired
	@Qualifier("integerValidation")
	private NumericalCheck numericalCheck;

	@Override
	@Test
	public void interfaceInitialize() {
		System.out.println(numericalCheck);
	}

}
