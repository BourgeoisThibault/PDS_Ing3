package pds.esibank.notificationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pds.esibank.notificationserver.utils.ListOfTokenGenerate;

import java.util.concurrent.Executor;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     21:41
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) throws Exception {

        ListOfTokenGenerate.initializeMap();

        CheckExpirationThread checkExpirationThread = new CheckExpirationThread();
        checkExpirationThread.start();

        // close the application context to shut down the custom ExecutorService
        SpringApplication.run(App.class, args);
    }

}