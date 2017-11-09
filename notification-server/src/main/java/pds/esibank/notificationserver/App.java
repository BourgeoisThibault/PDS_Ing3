package pds.esibank.notificationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import pds.esibank.notificationserver.websocket.MainServerSocket;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	public static void main(String[] args) {

		// Launch socker server
		MainServerSocket mainServerSocket = new MainServerSocket();
		mainServerSocket.start();


		// Launch REST app
		SpringApplication.run(App.class, args);
	}

}
