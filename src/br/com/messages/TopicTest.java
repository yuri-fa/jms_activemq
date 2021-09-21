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

import br.com.model.Pedido;
import br.com.util.PedidoFactory;

public class TopicTest {
	
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
		//passando usuario e senha para a autenticação
		Connection connection = factory.createConnection("yuri","yuri");
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = (Destination) context.lookup("xilito");
		Pedido pedido = PedidoFactory.gerarPedidoComValores();
		MessageProducer messageProducer = session.createProducer(destination);
		
		Message message = session.createObjectMessage(pedido);
		message.setBooleanProperty("fiado", false);
		messageProducer.send(message);
		
		session.close();
		connection.close();
		context.close();
	}

}
