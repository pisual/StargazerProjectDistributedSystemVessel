package com.stargazerproject.negotiate.resources;

import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.characteristic.BaseCharacteristic;

@Component(value="negotiateRetryPolicy")
@Qualifier("negotiateRetryPolicy")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NegotiateRetryPolicyCharacteristic implements BaseCharacteristic<RetryPolicy>{
	
	@Autowired
	@Qualifier("systemParameterCahce")
	private Cache<String,String> systemParameter;
	private RetryPolicy retryPolicy;
	
	public NegotiateRetryPolicyCharacteristic() {}

	@Override
	@Bean(name="negotiateRetryPolicyCharacteristic")
	@Lazy(true)
	public Optional<RetryPolicy> characteristic() {
		retryPolicyInitialization();
		return Optional.of(retryPolicy);
	}
	
	private void retryPolicyInitialization(){
		 retryPolicy = new ExponentialBackoffRetry(getIntegerParameter("Zookeeeper_Retry_Policy_Base_Sleep_Time_Ms"), getIntegerParameter("Zookeeeper_Retry_Policy_Retry_Connect_Number"));
	}

	private Integer getIntegerParameter(String key){
		return Integer.parseInt(systemParameter.get(Optional.of(key)).get());
	}
}
