package pds.esibank.payfreeclient.config;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import pds.esibank.models.elastik.CltInfo;

import java.util.Date;

/**
 * @author BOURGEOIS Thibault
 * Date     16/05/2018
 * Time     13:51
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
