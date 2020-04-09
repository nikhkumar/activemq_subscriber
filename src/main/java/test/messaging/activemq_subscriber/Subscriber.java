package test.messaging.activemq_subscriber;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {

//	@JmsListener(destination = "test.queue")
//	public void receiveQueue(ReportRequest request) {
//		
//		System.out.println("Message Received: "+request.toString());
//	}
	
	

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // default broker URL is : tcp://localhost:61616"
 
    // Name of the queue we will receive messages from
    private static String subject = "test.queue";
 
    public static void main(String[] args)  {
    	try {
    		Thread.sleep(5000);
    		System.out.println("start");
    		// Getting JMS connection from the server
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();
     
            // Creating session for reading messages
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
     
            // Getting the queue
            Destination destination = session.createQueue(subject);
     
            // MessageConsumer is used for receiving (consuming) messages
            MessageConsumer consumer = session.createConsumer(destination);
     
            // Here we receive the message.
            Message message = consumer.receive();
     
            // We will be using TestMessage in our example. MessageProducer sent us a TextMessage
            // so we must cast to it to get access to its .getText() method.
            if (message instanceof ReportRequest) {
            	ReportRequest request = (ReportRequest) message;
                System.out.println("Received message '" + request.toString());
            }
            connection.close();
            System.out.println("End");
		} catch (Exception e) {
		e.printStackTrace();
		}
        
    }
}
