package pds.esibank.restserver.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pds.esibank.models.dab.AccountDto;

import java.util.List;

@Component
public class RestDab {

    public static String DATA_ACCESS_URI;


    @Value("${data_access_account.uri}")
    public void setDATA_ACCESS_URI(String DATA_ACCESS_URI) {
        this.DATA_ACCESS_URI = DATA_ACCESS_URI;
    }

    public static List<AccountDto> getAccountsByCardId(long card_id) {

        final String uri = DATA_ACCESS_URI+"accountsByCardId/{card_id}";

        RestTemplate restTemplate = new RestTemplate();
        List<AccountDto> list = restTemplate.getForObject(uri,List.class,card_id);
        return list;
    }

}
