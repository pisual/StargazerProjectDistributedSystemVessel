package com.stargazerproject.resources.parameter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stargazerproject.resources.Parameters;

/** 
 *  @name 核心参数列表 systemParameters
 *  @illustrate 系统所需的systemParameters 参数
 *  @author Felixerio
 *  **/
@Component(value="systemParameters")
@Qualifier("systemParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="systemParameters")
@SuppressWarnings("unused")
public class SystemParameters {
	
	public SystemParameters() {}
	
	   //Cells 节点参数 Start
		/** @illustrate 系统ID  **/
		private static final String Kernel_System_CellsID = "65536";
		//Cells 节点参数 End
		
}
