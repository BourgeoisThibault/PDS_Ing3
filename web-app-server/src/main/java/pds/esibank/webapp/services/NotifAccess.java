package pds.esibank.webapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pds.esibank.models.notifClient.ClientNotifDTO;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
public class NotifAccess {
    private static Logger logger = LoggerFactory.getLogger(ShareAlertAccess.class);
    public static List<ClientNotifDTO> getList() throws IOException {
        final String uri = "http://192.154.88.150:8080/dataaccess/notif/all";
        List<ClientNotifDTO> notifClientList = new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, ClientNotifDTO.class));
        return notifClientList;
    }
}
