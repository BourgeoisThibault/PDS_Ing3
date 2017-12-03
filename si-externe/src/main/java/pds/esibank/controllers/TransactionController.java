package pds.esibank.controllers;

import org.apache.log4j.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by jeremy on 03/12/2017.
 */

@RestController
@RequestMapping(value="/transaction")
public class TransactionController {

    private final Logger logger = Logger.getLogger(TransactionController.class);

    @Bean
    public FilterRegistrationBean registration(HiddenHttpMethodFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value="/si-externe", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    public void acceptData(HttpServletRequest request) throws Exception {
        logger.info("Received request from : "+ request.getServerName());

        Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");

        PrintWriter file = new PrintWriter(new FileWriter("TransactionReceived.xml", true));
        String line = (s.hasNext() ? s.next() : "");
        file.write(line);
        logger.info("File created");

        file.close();
    }
}
