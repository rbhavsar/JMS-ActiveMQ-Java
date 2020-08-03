package com.bharath.jms.basics;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class JMSContextDemo {

	public static void main(String[] args) throws NamingException {
		
		InitialContext context = new InitialContext();
		Queue queue = (Queue) context.lookup("queue/myQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()){ // Basically club the Connection + Session 
			jmsContext.createProducer().send(queue,"Arise Awake and stop not till the goal is reached"); // create producer and send message to queue
			String messageReceived = jmsContext.createConsumer(queue).receiveBody(String.class); // receive message consumer
			System.out.println(messageReceived);
			//JMSProducer and JMSConsumer both are autoClosable -> no need to close it...
		}

	}

}
