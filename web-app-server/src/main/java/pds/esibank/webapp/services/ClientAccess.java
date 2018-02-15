package pds.esibank.webapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pds.esibank.models.Clients.ClientDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Abbdoulaye on 08/02/2018.
 */
public class ClientAccess {
    private static Logger logger = LoggerFactory.getLogger(ClientAccess.class);
    public static List<ClientDTO> getList() throws IOException {
        final String uri = "http://192.154.88.161:8080/dataaccess/Clients/allClients";
        List<ClientDTO> clientList = new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, ClientDTO.class));
        return clientList;
    }
}
