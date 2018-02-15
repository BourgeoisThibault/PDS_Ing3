package pds.esibank.webapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pds.esibank.models.Clients.ClientDTO;
import pds.esibank.models.communication.CommunicationTypeDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Abbdoulaye on 09/02/2018.
 */
public class CommunicationTypeAccess {
    private static Logger logger = LoggerFactory.getLogger(CommunicationTypeAccess.class);
    public static List<CommunicationTypeDTO> getList() throws IOException {
        final String uri = "http://192.154.88.150:8080/dataaccess/communication/all";
        List<CommunicationTypeDTO> communicationTypeList = new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, CommunicationTypeDTO.class));
        return communicationTypeList;
    }
}
