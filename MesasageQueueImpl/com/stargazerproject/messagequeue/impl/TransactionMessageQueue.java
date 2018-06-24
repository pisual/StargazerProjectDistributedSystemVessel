package com.stargazerproject.messagequeue.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.StanderCharacteristicShell;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.base.impl.BaseMessageQueue;
import com.stargazerproject.transaction.Transaction;

@Component(value="transactionMessageQueue")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier("transactionMessageQueue")
public class TransactionMessageQueue extends BaseMessageQueue<Transaction> implements StanderCharacteristicShell<MessageQueue<Transaction>>{
	
	protected TransactionMessageQueue() {}

	@Override
	public void initialize(Optional<MessageQueue<Transaction>> messageQueueArg) {
		messageQueue = messageQueueArg.get();
	}
	
}
