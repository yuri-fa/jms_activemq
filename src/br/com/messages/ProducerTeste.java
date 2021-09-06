package br.com.messages;

import java.util.Iterator;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ProducerTeste {
	
	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		
		ConnectionFactory fabrica = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = fabrica.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination fila = (Destination) context.lookup("liserio");
		
		MessageProducer producer = (MessageProducer) session.createProducer(fila);
		
		for (int  iterator = 0; iterator < 100 ; iterator++) {
			
			Message message = (Message) session.createTextMessage("Minhas bolas cabiludas crescendo :"+ iterator );
			
			producer.send(message);
		}
		
		session.close();
		connection.close();
		context.close();
	}
	
	private static String cabelosBolasCrescendo(int iterador) {
		StringBuilder sb = new StringBuilder();
		for (int  i = 0; i < iterador ; i++) {
			sb.append("-");
		}
		return sb.toString() + "/n";
	}
}
