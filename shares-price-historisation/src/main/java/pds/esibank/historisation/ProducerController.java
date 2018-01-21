package pds.esibank.historisation;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pds.esibank.models.shares.SharePrice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria on 14/01/2018.
 */

@RestController
public class ProducerController {

    private static RabbitTemplate rabbitTemplate;
    private ConnectionDataAccess dataAccess1 = new ConnectionDataAccess();
    private List<SharePrice> sharePricetab = new ArrayList<SharePrice>();

    @Autowired
    public ProducerController(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;

    }

    public ProducerController() {
    }

    @RequestMapping(path = "/Producer", method = RequestMethod.GET)
    public String getTest(){
        sharePricetab= dataAccess1.getSharePrice();
        if(!sharePricetab.isEmpty()){
            for (int i=0 ; i<sharePricetab.size() ; i++)
                rabbitTemplate.convertAndSend(sharePricetab.get(i));
            return "<h1>Il ya des messages à traiter</h1>";

        } else{

            return "<h1>Il n'y a aucun message à traiter</h1>";
        }

    }
}
