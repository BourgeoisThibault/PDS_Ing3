package pds.esibank.notificationserver;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BOURGEOIS Thibault
 * Date     20/11/2017
 * Time     21:53
 */
@Configuration
@ComponentScan("pds.esibank.notificationserver")
public class RabbitMqConfig {

    private static final String SIMPLE_MESSAGE_QUEUE = "test.queue.testttlday";

    @Bean
    public ConnectionFactory connectionFactory() {
        //CachingConnectionFactory connectionFactory = new CachingConnectionFactory("esibank.inside.esiag.info");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.154.88.166");
        connectionFactory.setUsername("esibank");
        connectionFactory.setPassword("esibankpds");
        connectionFactory.setVirtualHost("esibank-mom");
        return connectionFactory;
    }

    @Bean
    public Queue simpleQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl", 86400000);
        Queue queue = new Queue(SIMPLE_MESSAGE_QUEUE,true,false,false, args);
        return queue;
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(SIMPLE_MESSAGE_QUEUE);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

}