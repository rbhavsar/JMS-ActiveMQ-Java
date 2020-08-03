package com.bharath.jsm.hr;

import javax.jms.JMSContext;

import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;



/*
 * Run first all subscribed apps - WelnessApp, SecurityApp and PayrollApp
 * Run HRApp
 * See message printed at Subsriber apps
 */

public class HRApp {

	//Created HR App, Publishing message to topic
	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/empTopic");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

			Employee employee = new Employee();
			employee.setId(123);
			employee.setFirstName("Bharath");
			employee.setLastName("Thippireddy");
			employee.setDesignation("Software Architect");
			employee.setEmail("bharath@bharath.com");
			employee.setPhone("123456");
			for (int i = 1; i <= 10; i++) {
				jmsContext.createProducer().send(topic, employee);
			}

			System.out.println("Message Sent");

		}

	}

}
