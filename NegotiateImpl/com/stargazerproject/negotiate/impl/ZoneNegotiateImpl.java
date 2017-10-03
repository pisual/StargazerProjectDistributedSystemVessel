package com.stargazerproject.negotiate.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.negotiate.Negotiate;
import com.stargazerproject.negotiate.base.impl.BaseNegotiateImpl;

@Component(value="zoneNegotiateImpl")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("zoneNegotiateImpl")
public class ZoneNegotiateImpl extends BaseNegotiateImpl implements StanderCharacteristicShell<Negotiate>{

	@Override
	public void initialize(Optional<Negotiate> negotiateArg) {
		this.negotiate = negotiateArg.get();
	}

}
