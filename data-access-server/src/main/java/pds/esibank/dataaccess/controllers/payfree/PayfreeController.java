package pds.esibank.dataaccess.controllers.payfree;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pds.esibank.dataaccess.entities.Account;
import pds.esibank.dataaccess.entities.Card;
import pds.esibank.dataaccess.entities.Customer;
import pds.esibank.dataaccess.entities.payfree.LinkCardToAccount;
import pds.esibank.dataaccess.entities.payfree.PfClient;
import pds.esibank.dataaccess.services.AccountService;
import pds.esibank.dataaccess.services.payfree.CardService;
import pds.esibank.dataaccess.services.payfree.LinkCardService;
import pds.esibank.dataaccess.services.payfree.PfClientService;
import pds.esibank.models.dab.CardDto;
import pds.esibank.models.payfree.PfClientDto;

import javax.ws.rs.core.MediaType;

/**
 * @author BOURGEOIS Thibault
 * Date     11/03/2018
 * Time     17:51
 */
@Controller
@RequestMapping("/payfree")
public class PayfreeController {

    @Autowired
    PfClientService pfClientService;
    @Autowired
    CardService cardService;
    @Autowired
    LinkCardService linkCardService;
    @Autowired
    AccountService accountService;

    DozerBeanMapper mapper = new DozerBeanMapper();

    @RequestMapping(value="/card/{cardNum}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getCard(@PathVariable String cardNum) {
        Card card = cardService.getOneCard(cardNum);
        if(card == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(mapper.map(card, CardDto.class), HttpStatus.OK);
    }

    @RequestMapping(value="/checktransaction", method= RequestMethod.GET)
    public ResponseEntity checkValidity(
            @RequestParam("card") String card,
            @RequestParam("amount") String amount) {
        LinkCardToAccount linkCardToAccount = linkCardService.getInfoByCard(card);
        if(linkCardToAccount == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        if(( linkCardToAccount.getAccount().getSold()- Float.parseFloat(amount))<0)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value="/validatingtransaction", method= RequestMethod.GET)
    public ResponseEntity confirmeRetrait(
            @RequestParam("card") String card,
            @RequestParam("amount") String amount) {
        LinkCardToAccount linkCardToAccount = linkCardService.getInfoByCard(card);
        if(linkCardToAccount == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        Account account = linkCardToAccount.getAccount();
        account.setSold(account.getSold()-Float.parseFloat(amount));

        accountService.updateAccount(account);

        return new ResponseEntity(account.getCustomer().getId_Customer().toString(), HttpStatus.OK);
    }

}
