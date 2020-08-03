package com.bharath.jms.basics;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//JMS1 - need to do more code
public class FirstTopic {

	public static void main(String[] args) throws Exception {
	
		
		InitialContext initialContext = new InitialContext();
		Topic topic = (Topic) initialContext.lookup("topic/myTopic"); // TOPIC
		
		ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("ConnectionFactory"); // connection factory
		Connection connection = cf.createConnection(); // create connection
		
		Session session = connection.createSession(); // create session
		MessageProducer producer = session.createProducer(topic); // create producer and destination is topic
		
	
		MessageConsumer consumer1 = session.createConsumer(topic); // create consumer before send message , pass topic from which consumer read message
		MessageConsumer consumer2 = session.createConsumer(topic); // cretae another consumer
		
		TextMessage message = session.createTextMessage("All the power is with in me.I can do anything and everything.");

		producer.send(message); // send text message 
		
		connection.start(); // this is to tell JMS provider that consumer are ready to receive message.. start connection before receiving message
		
		TextMessage message1 = (TextMessage) consumer1.receive();
		
		System.out.println("Consumer 1 message received:"+message1.getText());
		
		
		TextMessage message2 = (TextMessage) consumer2.receive();
		
		System.out.println("Consumer 2 message received:"+message2.getText());
		
		connection.close();
		initialContext.close();

		//So, from topic multiple subscriber can read the message...
	}

}







