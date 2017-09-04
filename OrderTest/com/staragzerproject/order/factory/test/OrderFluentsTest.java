package com.staragzerproject.order.factory.test;


import com.google.common.base.Optional;
import com.staragzerproject.order.factory.OrderFluentFactory;
import com.stargazerproject.cache.impl.OrderParameterCache;
import com.stargazerproject.order.impl.Order;

public class OrderFluentsTest {
	public static void main(String[] args) {
		OrderFluentFactory o = new OrderFluentFactory();
  Order order = o.addID(Optional.of("ID"))
		         .addTransmission().addReceiveTarget(Optional.of("192.168.1.1"), Optional.of(1010))
		                           .addSourceTarget(Optional.of("192.168.1.2"), Optional.of(2020))
		                           .next()
		                           .addEvents()
		                               .addCache(Optional.fromNullable(new OrderParameterCache()))
		                               .addparameter(Optional.of("a"), Optional.of("a"))
		                               .addnextEvent()
		                           .addEvents()
		                               .addCache(Optional.fromNullable(new OrderParameterCache()))
                                       .addparameter(Optional.of("a"), Optional.of("a"))
                              .complete();
  
  System.out.println(order.toString());
  
	}
	
	
}
