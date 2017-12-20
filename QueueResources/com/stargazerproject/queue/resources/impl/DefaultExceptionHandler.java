package com.stargazerproject.queue.resources.impl;

import com.lmax.disruptor.ExceptionHandler;

public class DefaultExceptionHandler<E> implements ExceptionHandler<E>{

	@Override
	public void handleEventException(Throwable ex, long sequence, E e) {
		System.out.println("handleEventException TimeOut" + ex.toString());
	}

	@Override
	public void handleOnStartException(Throwable ex) {
		System.out.println("handleEventException TimeOut" + ex.toString());
	}

	@Override
	public void handleOnShutdownException(Throwable ex) {
		System.out.println("handleEventException TimeOut" + ex.toString());
	}

}
