package pds.esibank.historisation;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pds.esibank.models.shares.Share;
import pds.esibank.models.shares.SharePrice;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {

	private static RabbitTemplate rabbitTemplate;

	@Autowired
	public DemoApplication(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		SharePrice sharePrice = new SharePrice();
		sharePrice.setChange(new BigDecimal(1111));
		sharePrice.setPrice(new BigDecimal(9999));
		sharePrice.setShare(new Share());
		rabbitTemplate.convertAndSend(sharePrice);
	}
}
