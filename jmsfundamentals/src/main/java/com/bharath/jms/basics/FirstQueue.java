package com.bharath.jms.basics;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//JMS1 - need to do more code
public class FirstQueue {

	public static void main(String[] args) {

		InitialContext initialContext = null;
		Connection connection = null;

		try {
			initialContext = new InitialContext(); // read from jndi.properties
			ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory"); // ConnectionFactory
			 connection = cf.createConnection(); // create connection
			Session session = connection.createSession(); // create session
			Queue queue = (Queue) initialContext.lookup("queue/myQueue"); // destination - queue
			MessageProducer producer = session.createProducer(queue); // create producer
			TextMessage message = session.createTextMessage("I am the creator of my destiny"); // create message
			producer.send(message); // producer send it JMS queue
			System.out.println("Message Sent: " + message.getText());

			MessageConsumer consumer = session.createConsumer(queue); // create consumer (destination-queue)
			connection.start(); // to receive a message , start connection to tell JSM provider that we are ready to consume message
			TextMessage messageReceived = (TextMessage) consumer.receive(5000); // by default we receive Message and cast it to TextMessage as publisher send object type TextMessage
			System.out.println("Message Received: " + messageReceived.getText());

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}finally {
			if(initialContext!=null) {
				try {
					initialContext.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
			
			if(connection!=null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
