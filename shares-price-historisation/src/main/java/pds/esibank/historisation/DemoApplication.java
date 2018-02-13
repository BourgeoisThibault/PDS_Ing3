package pds.esibank.historisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) throws ParseException, IOException, TimeoutException {

		SpringApplication.run(DemoApplication.class, args);

		QueueListener listener = new QueueListener();
		listener.start();

		/*ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("mariam");
		factory.setPassword("esibank");
		factory.setVirtualHost("mariam-mom");
		factory.setHost("esibank.inside.esiag.info");


		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();


		Map<String, Object> argu = new HashMap<String, Object>();
		channel.queueDeclare(QUEUE_NAME, true, false, false, argu);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);*/

	}
}

