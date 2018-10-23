package com.stargazerproject.analysis.resources.shell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Optional;
import com.stargazerproject.analysis.extend.SequenceTransactionExecuteAnalysisExtend;
import com.stargazerproject.bus.Bus;
import com.stargazerproject.interfaces.characteristic.shell.BaseCharacteristic;
import com.stargazerproject.transaction.EventExecute;

/** 
 *  @name 非阻塞型序列运行器
 *  @illustrate 以非阻塞的方式运行序列
 *  @version 1.0.0
 *  **/
public class SequenceTransactionExecuteAnalysisShell implements SequenceTransactionExecuteAnalysisExtend, BaseCharacteristic<SequenceTransactionExecuteAnalysisExtend>{

	@Autowired
	@Qualifier("eventBusImpl")
	private Bus<EventExecute> bus;
	
	@Override
	public Optional<Boolean> analysis(Optional<List<EventExecute>> eventExecute) {
		eventExecute.get().stream().forEach(x -> bus.push(busEvent, timeUnit, timeout));
		return null;
	}

	@Override
	public Optional<SequenceTransactionExecuteAnalysisExtend> characteristic() {
		return Optional.of(this);
	}

}
