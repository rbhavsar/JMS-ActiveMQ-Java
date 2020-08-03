package com.bharath.jms.hm.eligibilitycheck;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/*
 * First run EligibilityCheckerApp ( 10 secs delay kept )
 * Second run Clinical App
 * Note : EligibilityCheckerApp should be up and running while we run Clinical App
 */


import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.bharath.jms.hm.eligibilitycheck.listeners.EligibilityCheckListener;

public class EligibilityCheckerApp {

	public static void main(String[] args) throws NamingException, InterruptedException {
		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue"); // read the message from queue send by producer

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext("eligibilityuser", "eligibilitypass")) {
			JMSConsumer consumer1 = jmsContext.createConsumer(requestQueue);
			//JMSConsumer consumer2 = jmsContext.createConsumer(requestQueue);
			consumer1.setMessageListener(new EligibilityCheckListener()); // instead of reading message, going to handle it async using listener..
			
			
			//Uncomment when you want to consume message in sync
			/*for(int i=1;i<=1000;i+=2) {
				System.out.println("Consumer1: "+consumer1.receive());
				System.out.println("Consumer2: "+consumer2.receive());
			}*/
			
			Thread.sleep(10000);

		};

	}

}
