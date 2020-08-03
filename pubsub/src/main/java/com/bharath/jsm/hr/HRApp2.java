package com.bharath.jsm.hr;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class HRApp2 {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/empTopic");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {
			
			JMSConsumer consumer = jmsContext.createConsumer(topic,"salary>1000");

			ObjectMessage objectMessage = jmsContext.createObjectMessage();
			Employee employee = new Employee();
			employee.setId(123);
			employee.setFirstName("Bharath");
			employee.setLastName("Thippireddy");
			employee.setDesignation("Software Architect");
			employee.setEmail("bharath@bharath.com");
			employee.setPhone("123456");
			objectMessage.setObject(employee);
			objectMessage.setIntProperty("salary", 1000);
			for (int i = 1; i <= 10; i++) {
				
				jmsContext.createProducer().send(topic, objectMessage);
				objectMessage.setIntProperty("salary", 1000+i);
			}

			
			Message message = consumer.receive();
			Employee employeer = message.getBody(Employee.class);
			System.out.println("Consumer 1: " + employeer.getFirstName());

			System.out.println("Message Sent");

		}

	}

}
