package com.esipe.esibankm.esibankm.utils;

import android.content.Context;
import android.util.Log;

import com.esipe.esibankm.esibankm.models.MobileToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * Created by Usman ABID BUTT on 25/11/2017.
 */


public class JsonUtils {

    public static void saveData(Context context, String mJsonResponse, String filename, int contextMode) {
        try {
            FileOutputStream fou = context.openFileOutput(filename, contextMode);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
            outputStreamWriter.write(mJsonResponse);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String getData(Context context, String filename) {
        try {
            File f = new File(context.getFilesDir().getPath() + "/" + filename);
            //check whether file exists
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;
        }
    }

    public static <T> String objectToJson(T object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static MobileToken MobileTokenFromJson(String json) {

        Gson gson = new Gson();
        MobileToken m = gson.fromJson(json, MobileToken.class);
        return m;
    }

    public static <T> T objectFromJson(String json, Class<T> toClass){
        Gson gson = new Gson();
        return gson.fromJson(json, toClass);
    }


}
