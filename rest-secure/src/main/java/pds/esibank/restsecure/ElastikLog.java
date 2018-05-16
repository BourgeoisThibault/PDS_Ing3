package pds.esibank.restsecure;

import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;

/**
 * @author BOURGEOIS Thibault
 * Date     16/05/2018
 * Time     17:34
 */

public class ElastikLog {

    public static Boolean sendLog(String logType, Object object){
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForEntity(logType, gson.toJson(object), String.class);
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }finally {
            return true;
        }
    }
}
