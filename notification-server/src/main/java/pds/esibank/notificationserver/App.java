package pds.esibank.notificationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executor;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     21:41
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) throws Exception {
        // close the application context to shut down the custom ExecutorService
        SpringApplication.run(App.class, args);
    }

}