package br.com.messages;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

import br.com.model.Pedido;

public class SubscribeTest {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		factory.setTrustAllPackages(true);
//		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection("yuri", "yuri");
		connection.setClientID("mozo");
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic topic = (Topic) context.lookup("xilito");
		//informando o nome da assinatura no subscribe
		MessageConsumer messageConsumer = session.createDurableSubscriber(topic, "toindoxilito");
		messageConsumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				 ObjectMessage  mapMessage = (ObjectMessage) message;
//				ObjectMessage objectMessage =  (ObjectMessage) message;
				try {
					Pedido pedido = (Pedido) mapMessage.getObject();
					System.out.println(pedido);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		
	}
}
