package br.com.messages;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SubscribeTest {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.setClientID("mozo");
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic topic = (Topic) context.lookup("xilito");
		
		MessageConsumer messageConsumer = session.createDurableSubscriber(topic, "toindoxilito");
		messageConsumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				TextMessage textMessage =  (TextMessage) message;
				try {
					System.out.println(textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		
	}
}
