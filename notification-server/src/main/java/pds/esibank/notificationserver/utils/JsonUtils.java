package pds.esibank.notificationserver.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author BOURGEOIS Thibault
 * Date     11/11/2017
 * Time     01:05
 */
public class JsonUtils {
    public static <T> String objectToJson(T object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T objectFromJson(String json, Class<T> toClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, toClass);
    }
}
