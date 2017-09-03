package com.staragzerproject.order.factory.test;


import com.google.common.base.Optional;
import com.staragzerproject.order.factory.OrderFluentFactory;
import com.stargazerproject.order.impl.Order;

public class OrderFluentsTest {
	public static void main(String[] args) {
		OrderFluentFactory o = new OrderFluentFactory();
Order order = o.addID(Optional.of("ID"))
		 .addTransmission().addReceiveTarget(Optional.of("source"), Optional.of(1))
		                   .addSourceTarget(Optional.of("source"), Optional.of(1))
		                   .next()
		                   .addEvents()
		                               .addCache(Optional.fromNullable(null))
		                               .addparameter(Optional.of(""), Optional.of(""))
		                               .addnextEvent()
		                   .addEvents()
		                               .addCache(Optional.fromNullable(null))
                                       .addparameter(Optional.of(""), Optional.of(""))
                           .complete();
	}
}
