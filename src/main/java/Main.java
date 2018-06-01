
import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.QueueConnectionFactory;
import org.apache.log4j.Logger;


import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by mzukowski on 31/05/2018.
 * God bless young Java Dev : *
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Must supply a message");
            return;
        } else {
            System.out.println(args[0]);
        }

        ConnectionFactory connectionFactory;
        Connection connection = null;
        try {
//            Properties props = new Properties();
//            props = new Properties();
//            props.setProperty("java.naming.factory.initial","com.sun.enterprise.naming.SerialInitContextFactory");
//            props.setProperty("java.naming.factory.url.pkgs","com.sun.enterprise.naming");
//            props.setProperty("java.naming.factory.state","com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
//            // Create the initial context for remote JMS server
//            InitialContext cntxt = new InitialContext(props);
//            System.out.println("Context Created");
//            connectionFactory = (QueueConnectionFactory) cntxt.lookup("jms/__defaultConnectionFactory");
//            System.out.println("factory");
////
            InitialContext initialContext = new InitialContext();
            System.out.println("initialized");

            Queue queue = (Queue) initialContext.lookup("P2PQueue");
            System.out.println("qwe2");
            //in jms 1.1
            connectionFactory = (QueueConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
//            // 2 more thing to create connection using connection factory and from connection we get session
//
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//            MessageProducer messageProducer = session.createProducer(queue);
//            TextMessage textMessage = session.createTextMessage(args[0]);
//            messageProducer.send(textMessage);

            logger.info("Message produced");


        } catch (NamingException e) {
            e.printStackTrace();
            logger.debug("problem");
        } /*catch (JMSException e) {
            logger.debug("problem1");
            e.printStackTrace();
        }*/ catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

}
