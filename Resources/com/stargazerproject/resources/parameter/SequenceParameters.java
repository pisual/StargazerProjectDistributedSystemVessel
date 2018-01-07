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
@Component(value="sequenceParameters")
@Qualifier("sequenceParameters")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Parameters(value="sequenceParameters")
@SuppressWarnings("unused")
public class SequenceParameters {
	
	public SequenceParameters() {}
	
	    //Sequence 参数 Start
		/** Sequence 指令等待期间，检测结果的最大重试次数 **/
		/** @illustrate 参数类 **/
		private static final String Sequence_Retries_Number = "1024";
		
		/** Sequence 指令等待期间，检测结果的间隔时间 单位为（Secsond）秒 **/
		/** @illustrate 参数类 **/
		private static final String Sequence_Intervaltime_Secsond = "10";
		//Sequence 参数 End
		
}
