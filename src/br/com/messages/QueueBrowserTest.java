package br.com.messages;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueBrowserTest {
	public static void main(String[] args) throws JMSException, NamingException {
		InitialContext context = new InitialContext();
		
		ConnectionFactory fabrica = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = fabrica.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination fila = (Destination) context.lookup("liserio");
		QueueBrowser queueBrowser = session.createBrowser((Queue) fila);
		
		Enumeration e = queueBrowser.getEnumeration();
		
		while(e.hasMoreElements()) {
			TextMessage textMessage = (TextMessage) e.nextElement();
			System.out.println(textMessage.getText());
		}
	}
}
