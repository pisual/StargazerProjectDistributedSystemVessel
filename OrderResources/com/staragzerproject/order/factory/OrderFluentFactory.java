package com.staragzerproject.order.factory;

import java.util.ArrayList;

import com.google.common.base.Optional;
import com.stargazerproject.cache.Cache;
import com.stargazerproject.order.Target;
import com.stargazerproject.order.impl.AddressTarget;
import com.stargazerproject.order.impl.Event;
import com.stargazerproject.order.impl.Order;
import com.stargazerproject.order.impl.Transaction;
import com.stargazerproject.order.impl.Transmission;

public class OrderFluentFactory {
	
	private Optional<String> id;
	
	private Transmission transmission;
	private TransmissionTarget transmissionTarget;
	private Target source;
	private Target receive;
	
	private TransactionEvents transactionEvents;
	protected ArrayList<Event> event = new ArrayList<Event>();
	int eventPoint;

	private EventsParameter eventsParameter;
	
	public OrderFluentFactory() {
		transmissionTarget = new TransmissionTarget();
		transactionEvents = new TransactionEvents();
	}
	
	public OrderFluentFactory addID(Optional<String> idArg){
		id = idArg;
		return this;
	}
	
	public TransmissionTarget addTransmission(){
		return transmissionTarget;
	}
	
	public TransactionEvents addTransactionEvents(){
		return transactionEvents;
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
		
		public EventsParameter addCache(Optional<Cache> cacheArg){
			cache = cacheArg.get();
			return this;
		}
		
		public EventsParameter addparameter(Optional<String> key, Optional<String> value){
			cache.put(key, value);
			return this;
		}
		
		public TransactionEvents addnextEvent(){
			event.add(new Event(id, Optional.of(cache)));
			return transactionEvents;
		}
		
		public Order complete(){
			Event[] eventArray = new Event[event.size()];
			Event[] evenResult = event.toArray(eventArray);
			
			Optional<Event>[] eventOptional = new Optional[event.size()];
			
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
