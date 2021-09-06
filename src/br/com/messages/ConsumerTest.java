package br.com.messages;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConsumerTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws NamingException, JMSException {
		
		//pega o contexto da aplicação
		InitialContext context = new InitialContext();
		//informa o nome da conexão para a fabrica
		ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
		//pega o objeto de conexão
		Connection connection = connectionFactory.createConnection();
		//estabelece a conexão
		connection.start();
		
		//crio um nova sessao a sessão fica responsavel pela parte transacional e pela confirmação do recebimento da mensagem
		//primeiro paramentro um booleano para indicar se quer uma transação ou não
		//segundo parametro informa se quer que seja automaticamento confirmada o recebimento da mensagem
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//informa o nome da fila no lookup do context
		Destination destino = (Destination) context.lookup("liserio");
		MessageConsumer messageConsumer = session.createConsumer(destino);
		//recebendo a messagem que nosso consumer detectou
//		Message message = messageConsumer.receive();
//		System.out.println("Recive : " + message.toString());
		
		messageConsumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println(textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}

}
