package com.stargazerproject.messagequeue.resources.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.messagequeue.MessageQueue;
import com.stargazerproject.messagequeue.MessageQueueAcquire;
import com.stargazerproject.messagequeue.MessageQueueControl;
import com.stargazerproject.messagequeue.MessageQueuePush;
import com.stargazerproject.transaction.Transaction;

@Component(value="transactionMessageQueueShall")
@Qualifier("transactionMessageQueueShall")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionMessageQueueShall implements MessageQueue<Transaction>, BaseCharacteristic<MessageQueue<Transaction>>{

	@Autowired
	@Qualifier("transactionMessageQueueControlCharacteristic")
	private BaseCharacteristic<MessageQueueControl<Transaction>> transactionMessageQueueControlCharacteristic;
	
	@Autowired
	@Qualifier("transactionMessageQueueAcquireCharacteristic")
	private BaseCharacteristic<MessageQueueAcquire<Transaction>> transactionMessageQueueAcquireCharacteristic;
	
	@Autowired
	@Qualifier("transactionMessageQueuePushCharacteristic")
	private BaseCharacteristic<MessageQueuePush<Transaction>> transactionMessageQueuePushCharacteristic;
	
	private MessageQueueControl<Transaction>  transactionMessageQueueControl;
	
	private MessageQueueAcquire<Transaction>  transactionMessageQueueAcquire;
	
	private MessageQueuePush<Transaction>  transactionMessageQueuePush;
	
	@Override
	public Optional<MessageQueue<Transaction>> characteristic() {
		transactionMessageQueueControl = transactionMessageQueueControlCharacteristic.characteristic().get();
		transactionMessageQueueAcquire = transactionMessageQueueAcquireCharacteristic.characteristic().get();
		transactionMessageQueuePush    = transactionMessageQueuePushCharacteristic.characteristic().get();
		return Optional.of(this);
	}

	@Override
	public Optional<List<Transaction>> get(Optional<Integer> messageNumber) {
		return transactionMessageQueueAcquire.get(messageNumber);
	}
	
	@Override
	public void put(Optional<List<Transaction>> transactionList) {
		transactionMessageQueuePush.put(transactionList);
	}

	@Override
	public void join() {
		transactionMessageQueueControl.join();
	}

	@Override
	public void out() {
		transactionMessageQueueControl.out();
	}

}
