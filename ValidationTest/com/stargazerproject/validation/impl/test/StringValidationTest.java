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
import com.stargazerproject.validation.StringCheck;
import com.stargazerproject.validation.configuration.GroupValidationDIConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={GroupValidationDIConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StringValidationTest implements WorkInTest{
	
	@Autowired
	@Qualifier("stringValidation")
	private StringCheck stringCheck;

	@Override
	@Test
	public void interfaceInitialize() {
		System.out.println(stringCheck);
	}

}
