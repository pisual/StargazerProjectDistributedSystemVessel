package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.SequenceTransactionExecuteAnalysis;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/** 
 *  @name 非阻塞型序列运行器
 *  @illustrate 以非阻塞的方式运行序列
 *  @version 1.0.0
 *  **/
public class SequenceTransactionExecuteAnalysisShell implements SequenceTransactionExecuteAnalysis, BaseCharacteristic<SequenceTransactionExecuteAnalysis>{

//	@Autowired
//	@Qualifier("eventBusImpl")
//	private BusNoBlockMethod<Event> bus;
//	
//	@Autowired
//	@Qualifier("eventBusResultAnalysisExtend")
//	private EventBusResultAnalysisExtend eventBusResultAnalysisExtend;
//	
//	@Override
//	public Optional<Boolean> analysis(Optional<List<Event>> eventExecute) throws BusEventTimeoutException{
//		eventExecute.get().stream().forEach(event -> bus.pushNoBlock(Optional.of(event)));
//		return Optional.of(Boolean.TRUE);
//	}
//
//	@Override
//	public Optional<SequenceTransactionExecuteAnalysisExtend> characteristic() {
//		return Optional.of(this);
//	}
//		

	@Override
	public Optional<SequenceTransactionExecuteAnalysis> characteristic() {
		// TODO Auto-generated method stub
		return null;
	}
}
