package com.stargazerproject.analysis.resources.shell;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.SequenceTransactionExecuteAnalysis;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;

/** 
 *  @name 阻塞型序列运行器
 *  @illustrate 以阻塞的方式运行序列
 *  @version 1.0.0
 *  **/
public class SequenceBlockTransactionExecuteAnalysisShell implements SequenceTransactionExecuteAnalysis, BaseCharacteristic<SequenceTransactionExecuteAnalysis>{

//	@Autowired
//	@Qualifier("eventBusImpl")
//	private BusBlockMethod<Event> bus;
//	
//	@Autowired
//	@Qualifier("eventBusResultAnalysisExtend")
//	private EventBusResultAnalysisExtend eventBusResultAnalysisExtend;
//	
//	@Override
//	public Optional<Boolean> analysis(Optional<List<Event>> eventExecute) throws BusEventTimeoutException{
//		eventExecute.get().stream().forEach(event -> {S
//			event.eventResult(Optional.of(eventBusResultAnalysisExtend));
//			try {
//				bus.push(Optional.of(event), 
//						eventBusResultAnalysisExtend.busEventTimeout().get().getTimeUnit(), 
//						eventBusResultAnalysisExtend.busEventTimeout().get().getTimeout());
//			} catch (BusEventTimeoutException busEventTimeoutException) {
//				throw busEventTimeoutException;
//			}});
//		return null;
//	}
//
//	@Override
//	public Optional<SequenceTransactionExecuteAnalysisExtend> characteristic() {
//		return Optional.of(this);
//	}
		

	@Override
	public Optional<SequenceTransactionExecuteAnalysis> characteristic() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
