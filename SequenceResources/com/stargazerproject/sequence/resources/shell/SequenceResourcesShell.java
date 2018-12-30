package com.stargazerproject.sequence.resources.shell;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.sequence.ParallelSequenceTransaction;
import com.stargazerproject.sequence.Sequence;
import com.stargazerproject.sequence.SequenceObserver;
import com.stargazerproject.sequence.SequenceTransaction;

@Component(value="sequenceResourcesShell")
@Qualifier("sequenceResourcesShell")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SequenceResourcesShell implements Sequence<Order>, BaseCharacteristic<Sequence<Order>>{

	@Override
	public Optional<ParallelSequenceTransaction<Order>> creatParallelSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addParallelSequence(Optional<Order> transaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearParallelSequence(Optional<String> transactionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearParallelSequence() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<SequenceObserver<Order>> startBlockParallelSequence() throws BusEventTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SequenceObserver<Order>> startParallelSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SequenceObserver<Order>> shutDownParallelSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SequenceTransaction<Order>> creatSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSequence(Optional<Order> transaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearSequence(Optional<String> transactionID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearSequence() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<SequenceObserver<Order>> startBlockSequence() throws BusEventTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SequenceObserver<Order>> startSequence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Sequence<Order>> characteristic() {
		// TODO Auto-generated method stub
		return null;
	}
	
}