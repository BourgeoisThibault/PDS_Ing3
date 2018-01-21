package pds.esibank.historisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class DemoApplication {

	/*private static RabbitTemplate rabbitTemplate;

	@Autowired
	public DemoApplication(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}*/

	public static void main(String[] args) throws ParseException, IOException {

		SpringApplication.run(DemoApplication.class, args);

		//mycomment

		/*SharePrice sharePrice = new SharePrice();
		sharePrice.setChange(new BigDecimal(1111));
		sharePrice.setPrice(new BigDecimal(9999));
		sharePrice.setShare(new Share());
		rabbitTemplate.convertAndSend(sharePrice);*/
	}
}
