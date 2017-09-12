package com.staragzerproject.order.factory.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.fasterxml.uuid.Generators;
import com.google.common.base.Optional;
import com.staragzerproject.order.factory.OrderFluentFactory;
import com.stargazerproject.cache.impl.OrderParameterCache;
import com.stargazerproject.order.impl.Order;

public class OrderFluentsTest {
	
	private static List<Order> orderList = new ArrayList<Order>();

	private Order getOrder() {
		OrderFluentFactory o = new OrderFluentFactory();
		UUID uuid = Generators.randomBasedGenerator().generate();
		Order order = o.addID(Optional.of(uuid.toString())).addTransmission()
				.addReceiveTarget(Optional.of("192.168.1.1"), Optional.of(1010))
				.addSourceTarget(Optional.of("192.168.1.2"), Optional.of(2020)).next().addEvents()
				.addCache(Optional.of(new OrderParameterCache()))
				.addparameter(Optional.of("a"), Optional.of("a")).addnextEvent().addEvents()
				.addCache(Optional.of(new OrderParameterCache()))
				.addparameter(Optional.of("a"), Optional.of("a")).complete();
		return order;
	}
	
	private String getUUID(){
		UUID uuid = Generators.randomBasedGenerator().generate();
		System.out.println(uuid.toString());
		return uuid.toString();
	}

	@Test
	public void getSingleOrder() {
		getOrder();
	}
}
