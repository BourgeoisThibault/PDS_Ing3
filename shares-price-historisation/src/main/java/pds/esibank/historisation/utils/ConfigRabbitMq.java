package pds.esibank.historisation.utils;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maria on 20/12/2017.
 */

@Configuration
@ComponentScan("pds.esibank.historisation")
public class ConfigRabbitMq {

    final static String queueTest = "spring-boot";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("esibank.inside.esiag.info");
        connectionFactory.setUsername("mariam");
        connectionFactory.setPassword("esibank");
        connectionFactory.setVirtualHost("mariam-mom");
        return connectionFactory;
    }

    @Bean
    public Queue simpleQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl", 86400000);
        Queue queue = new Queue("MARIAM_QUEUE",true,false,false, args);
        return queue;
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey("MARIAM_QUEUE");
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
 
}
