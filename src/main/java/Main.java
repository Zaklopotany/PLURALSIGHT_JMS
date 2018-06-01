
import org.apache.log4j.Logger;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
            InitialContext initialContext = new InitialContext();

            Queue queue = (Queue) initialContext.lookup("jms/P2PQueue");
            connectionFactory = (QueueConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
            //in jms 1.1
            // 2 more thing to create connection using connection factory and from connection we get session
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage(args[0]);
            messageProducer.send(textMessage);

            logger.info("Message produced");


        } catch (NamingException e) {
            e.printStackTrace();
            logger.debug("problem");
        } catch (JMSException e) {
            logger.debug("problem1");
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
