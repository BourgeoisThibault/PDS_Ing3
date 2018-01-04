package com.esipe.esibankm.esibankm.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by usman on 04/01/2018.
 */

public class LoadProp {
    private static final String TAG="LoadProp";

    public static String getProperty(String key,Context context, String filename) {
        Properties properties = new Properties();;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(filename+".properties");
            properties.load(inputStream);
            Log.i(TAG, properties.getProperty(key));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);

    }
}
