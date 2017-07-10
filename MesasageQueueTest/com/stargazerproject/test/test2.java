package com.stargazerproject.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stargazerproject.model.util.TimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Config.class,Listener.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class test2 {
	
	@Autowired
	private Listener listener;

	@Autowired
	private KafkaTemplate<Integer, String> template;

	@Test
	public void testSimple() throws Exception {
		while(true){
		    template.send("topic1", 0, "f");
		    template.flush();
			TimeUnit.SECONDS.sleep(5);
		}

	//    assertTrue(this.listener.latch1.await(10, TimeUnit.SECONDS));
	}
	
}
