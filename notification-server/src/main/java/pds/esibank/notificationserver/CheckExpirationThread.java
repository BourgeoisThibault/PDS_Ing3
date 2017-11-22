package pds.esibank.notificationserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pds.esibank.notificationserver.controllers.TokenController;
import pds.esibank.notificationserver.utils.ListOfTokenGenerate;

/**
 * @author BOURGEOIS Thibault
 * Date     22/11/2017
 * Time     18:21
 */
public class CheckExpirationThread extends Thread {

    private final Logger logger = LoggerFactory.getLogger(CheckExpirationThread.class);

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                logger.error("Error sleep 5min");
            }

            ListOfTokenGenerate.deleteExpireToken();
        }

    }

}
