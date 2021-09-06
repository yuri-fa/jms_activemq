package br.com.messages;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicTest {
	
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = (Destination) context.lookup("xilito");
		
		MessageProducer messageProducer = session.createProducer(destination);
		Message message = session.createTextMessage("<xilito><name>xilitao de 1 real</name><preco>1</preco><fiado>false</fiado></xilito>");
		message.setBooleanProperty("fiado", true);
		messageProducer.send(message);
		
		session.close();
		connection.close();
		context.close();
	}

}
