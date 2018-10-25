package com.stargazerproject.analysis.resources.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.EventBusResultAnalysisExtend;
import com.stargazerproject.analysis.extend.SequenceTransactionExecuteAnalysisExtend;
import com.stargazerproject.bus.BusNoBlockMethod;
import com.stargazerproject.bus.exception.BusEventTimeoutException;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.Event;

/** 
 *  @name 非阻塞型序列运行器
 *  @illustrate 以非阻塞的方式运行序列
 *  @version 1.0.0
 *  **/
public class SequenceTransactionExecuteAnalysisShell implements SequenceTransactionExecuteAnalysisExtend, BaseCharacteristic<SequenceTransactionExecuteAnalysisExtend>{

	@Autowired
	@Qualifier("eventBusImpl")
	private BusNoBlockMethod<Event> bus;
	
	@Autowired
	@Qualifier("eventBusResultAnalysisExtend")
	private EventBusResultAnalysisExtend eventBusResultAnalysisExtend;
	
	@Override
	public Optional<Boolean> analysis(Optional<List<Event>> eventExecute) throws BusEventTimeoutException{
		eventExecute.get().stream().forEach(event -> bus.pushNoBlock(Optional.of(event)));
		return Optional.of(Boolean.TRUE);
	}

	@Override
	public Optional<SequenceTransactionExecuteAnalysisExtend> characteristic() {
		return Optional.of(this);
	}
		

}
