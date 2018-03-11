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
import pds.esibank.dataaccess.entities.payfree.PfClient;
import pds.esibank.dataaccess.services.payfree.PfClientService;
import pds.esibank.models.payfree.PfClientDto;

import javax.ws.rs.core.MediaType;

/**
 * @author BOURGEOIS Thibault
 * Date     11/03/2018
 * Time     17:51
 */
@Controller
@RequestMapping("/payfree")
public class PfClientController {
    @Autowired
    PfClientService pfClientService;

    DozerBeanMapper mapper = new DozerBeanMapper();

    @RequestMapping(value="/{pseudo}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity getPfClientById(@PathVariable String pseudo) throws JsonProcessingException {
        PfClient pfClient = pfClientService.getOneClient(pseudo);
        if(pfClient== null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        PfClientDto pfClientDto = mapper.map(pfClient, PfClientDto.class);
        return new ResponseEntity(pfClientDto, HttpStatus.OK);

    }
}
