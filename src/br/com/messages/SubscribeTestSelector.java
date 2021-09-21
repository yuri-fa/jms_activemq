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

public class SubscribeTestSelector {
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		factory.setTrustAllPackages(true);
		Connection connection = factory.createConnection("yuri","yuri");
		connection.setClientID("dona_maria");
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic topic = (Topic) context.lookup("xilito");
		
		//aqui é definido o selector a ser enviado e se essa conexão vai ou não produzir mensagem
		//o valor definido para o seletor e fiado e o subcribe so recebera essa mensagem quando o publish não informar o seletor ou definilo com false
		MessageConsumer messageConsumer = session.createDurableSubscriber(topic, "mariadoxilito","fiado is null OR fiado=false",false);
		messageConsumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				ObjectMessage obj = (ObjectMessage) message;
				try {
					Pedido pedido = (Pedido) obj.getObject();
					System.out.println(pedido.getCodigo());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			
		});
	}
}
