package com.bharath.jms.messagestructure;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class MessageExpirationDemo {

	public static void main(String[] args) throws NamingException, InterruptedException, JMSException {

		InitialContext context = new InitialContext();
		Queue queue = (Queue) context.lookup("queue/myQueue");
		Queue expiryQueue = (Queue) context.lookup("queue/expiryQueue");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {
			JMSProducer producer = jmsContext.createProducer();
			producer.setTimeToLive(2000); 
			producer.send(queue, "Arise Awake and stop not till the goal is reached"); //message send to queue and will expire after 2 secs
			Thread.sleep(1000);// if we wait here more than 2 seconds then consumer won't receive any message

			Message messageReceived = jmsContext.createConsumer(queue).receive(5000); // consumer will wait for 5 sec , if it does not receive message then stop there..
			System.out.println(messageReceived);
			
			System.out.println(jmsContext.createConsumer(expiryQueue).receiveBody(String.class));

		}

	}

}
