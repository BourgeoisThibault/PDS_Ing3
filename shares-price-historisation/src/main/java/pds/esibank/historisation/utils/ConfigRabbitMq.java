package pds.esibank.historisation.utils;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by maria on 20/12/2017.
 */

@Configuration
@ComponentScan("pds.esibank.historisation")
public class ConfigRabbitMq {


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("esibank.inside.esiag.info");
        connectionFactory.setUsername("mariam");
        connectionFactory.setPassword("esibank");
        connectionFactory.setVirtualHost("mariam-mom");
        return connectionFactory;
    }

    /* Channel channel = connectionFactory().createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Hello World!";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();*/
}
