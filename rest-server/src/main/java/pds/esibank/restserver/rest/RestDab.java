package pds.esibank.restserver.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pds.esibank.models.dab.AccountDto;
import pds.esibank.models.dab.CardDto;

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



    public static AccountDto createAccount(AccountDto accountDto
    ){
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("id account : "+accountDto.getAccount_id());
        AccountDto a = restTemplate.postForObject(DATA_ACCESS_URI+"createAccount",accountDto,AccountDto.class);
        return a;
    }

    public static CardDto createCard(CardDto cardDto) {
        RestTemplate restTemplate = new RestTemplate();
        CardDto c = restTemplate.postForObject(DATA_ACCESS_URI+"createCard",cardDto,CardDto.class);
        return c;
    }

    public static String removeAccount(Long aLong) {
        RestTemplate restTemplate = new RestTemplate();
        String c = restTemplate.getForObject(DATA_ACCESS_URI+"deleteAccount/"+aLong,String.class);
        return c;
    }

    public static String removeLinkAccountToCard(String card_num) {
        RestTemplate restTemplate = new RestTemplate();
        String c = restTemplate.getForObject(DATA_ACCESS_URI+"deleteLink/"+card_num,String.class);
        return c;
    }


    public static String removeCard(Long aLong) {
        RestTemplate restTemplate = new RestTemplate();
        String c = restTemplate.getForObject(DATA_ACCESS_URI+"deleteCard/"+aLong,String.class);
        return c;
    }
}
