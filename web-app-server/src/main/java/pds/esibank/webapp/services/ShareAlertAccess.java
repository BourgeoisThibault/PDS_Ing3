package pds.esibank.webapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pds.esibank.models.shareAlert.ShareDTO;


import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Abbdoulaye on 05/12/2017.
 */
public class ShareAlertAccess {
    private static Logger logger = LoggerFactory.getLogger(ShareAlertAccess.class);
    public static List<ShareDTO> getList() throws IOException {
        final String uri = "http://192.154.88.150:8080/dataaccess/shareAlert/allShareAlert";
        List<ShareDTO> shareAList = new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, ShareDTO.class));
        return shareAList;
    }
}
