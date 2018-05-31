package com.stargazerproject.order.factory;

import java.util.ArrayList;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Target;
import com.stargazerproject.order.base.impl.BaseEvent;
import com.stargazerproject.order.impl.AddressTarget;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.order.impl.Transaction;
import com.stargazerproject.order.impl.Transmission;

public class OrderFluentFactory {
	
	private Optional<String> id;
	private Target source;
	private Target receive;
	protected ArrayList<BaseEvent> event;
	
	public OrderFluentFactory() {
		event = new ArrayList<BaseEvent>();
	}
	
	public OrderFluentFactory addID(Optional<String> idArg){
		id = idArg;
		return this;
	}
	
	public TransmissionTarget addTransmission(){
		return new TransmissionTarget();
	}
	
	public TransactionEvents addTransactionEvents(){
		return new TransactionEvents();
	}
	
	public class TransmissionTarget{
		
		public TransmissionTarget addSourceTarget(Optional<String> ipArg, Optional<Integer> portArg){
			source  = new AddressTarget(ipArg, portArg);
			return this;
		}
		
		public TransmissionTarget addReceiveTarget(Optional<String> ipArg, Optional<Integer> portArg){
			receive  = new AddressTarget(ipArg, portArg);
			return this;
		}
		
		public TransactionEvents next(){
			return new TransactionEvents();
		}
	}
	
	public class TransactionEvents{
		public EventsParameter addEvents(){
			return new EventsParameter();
		}
	}
	
	public class EventsParameter{
		
		private Cache<String, String> cache;
		
		public EventsParameter addCache(Optional<Cache<String, String>> cacheArg){
			cache = cacheArg.get();
			return this;
		}
		
		public EventsParameter addparameter(Optional<String> key, Optional<String> value){
			cache.put(key, value);
			return this;
		}
		
		public TransactionEvents addnextEvent(){
			event.add(new BaseEvent(id, Optional.of(cache)));
			return new TransactionEvents();
		}
		
		public Order complete(){
			event.add(new BaseEvent(id, Optional.of(cache)));
			BaseEvent[] eventArray = new BaseEvent[event.size()];
			BaseEvent[] evenResult = event.toArray(eventArray);
			
			Optional<BaseEvent>[] eventOptional = new Optional[event.size()];
			
			for (int i = 0; i < event.size(); i++) {
				eventOptional[i] = Optional.of(evenResult[i]);
			}
	
			Transmission transmission = new Transmission(Optional.of(source), Optional.of(receive));
			Transaction transaction = new Transaction(id, eventOptional);			
			Order order = new Order(id, Optional.of(transmission), Optional.of(transaction));
			return order;
		}
		
	}
	
}