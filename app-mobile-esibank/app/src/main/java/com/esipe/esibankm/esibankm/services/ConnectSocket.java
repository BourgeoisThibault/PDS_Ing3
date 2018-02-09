package com.esipe.esibankm.esibankm.services;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.esipe.esibankm.esibankm.models.MobileToken;
import com.esipe.esibankm.esibankm.utils.JsonUtils;
import com.esipe.esibankm.esibankm.utils.LoadProp;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import retrofit.RestAdapter;



/**
 * Created by Ance on 11/11/2017.
 */

public class ConnectSocket implements Runnable {
    private static final String FILE_NAME = "data.json";
    private static PrintWriter writer;
    private static BufferedInputStream reader;
    //private static final String SERVERIP = "push.esibank.inside.esiag.info"; //computer IP address for testing
    public static final int SERVERPORT = 2702;
    //private static final String SERVERIP = "172.16.0.11"; //computer IP address for testing
    //public static final int SERVERPORT = 9090;
    private Socket socket;
    public static boolean isRunning;
    private final Handler h;
    private Bundle bundle;
    private Context contextService;
    private MobileToken mobileToken;
    private final String TAG="ConnectSocket";

    public ConnectSocket(Handler handler, Context context) {
        h = handler;
        contextService = context;
        isRunning = true;
    }

    public void run() {
        mobileToken = JsonUtils.MobileTokenFromJson(JsonUtils.getData(contextService,FILE_NAME));
        if(isOnline()) {
            tokenPushNotifServer(mobileToken);
            sendMessageToService("connection_state_failed",mobileToken.getUid()+" "+LoadProp.getProperty("conn_success",contextService,"messages"));
        }
        else
            sendMessageToService("connection_state_failed",LoadProp.getProperty("conn_failed",contextService,"messages"));

        while (isRunning) {
            try {
                socket = createSocket(LoadProp.getProperty("server_ip",contextService,"url"), SERVERPORT);
                Log.i(TAG, "Socket created !!!");
                writer = new PrintWriter(socket.getOutputStream(), true);
                reader = new BufferedInputStream(socket.getInputStream());

                mobileToken = JsonUtils.MobileTokenFromJson(JsonUtils.getData(contextService,FILE_NAME));
                Log.i(TAG, "Token saved : UID "+mobileToken.getUid()+" Token "+mobileToken.getToken());

                Log.i(TAG, "Token before send : "+mobileToken.getToken());

                if (socket.isConnected()) {
                    send(String.valueOf(mobileToken.getToken()), writer);
                    while (isRunning) {
                        Log.i(TAG, "en attente du server...");
                        String accept = read(reader);
                        if (accept.equals("PING") && isRunning) {
                            send("PONG", writer);
                            Log.i(TAG, accept);
                        }else if (!accept.equals("PING"))
                            sendMessageToService("notification_content",accept);
                        else
                            Log.i(TAG, "en attente" + accept.toString());

                    }
                }
            } catch (SocketException sException) {
                try {
                    if (socket != null) {
                        socket.close();
                        Log.i(TAG, "Socket is closed  !");
                        sendMessageToService("connection_state_failed",LoadProp.getProperty("conn_retry",contextService,"messages"));
                        Log.i(TAG, "Tentative de connexion niv1...");
                        Thread.sleep(3000);
                    }

                } catch (InterruptedException ie) {
                    Log.i(TAG, "Echec de connexion...");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                try {
                    if (socket != null) {
                        socket.close();
                        Log.i(TAG, "Socket is closed  !");
                        sendMessageToService("connection_state_failed",LoadProp.getProperty("conn_retry",contextService,"messages"));
                        Log.i(TAG, "Tentative  de connexion niv 2..." + e);
                        Thread.sleep(3000);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                    Log.i(TAG, "Connection retry failed" + e);
                    Log.i(TAG, "Rerun thread run method..." + e);
                    run();
                }
            }
        }
    }



    public void tokenPushNotifServer(MobileToken mobileToken){
        EsibankService esibankService = new RestAdapter.Builder()
                .setEndpoint(LoadProp.getProperty("notif_server",contextService,"url"))
                .build()
                .create(EsibankService.class);
        mobileToken = esibankService.postToken(mobileToken);
        Log.i(TAG, "Token from server : " + mobileToken.getToken());
        try {
            JsonUtils.saveData(contextService, JsonUtils.objectToJson(mobileToken),FILE_NAME,Context.MODE_PRIVATE);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Log.i(TAG, "Erreur ecriture json");
        }
      //  mobileToken = JsonUtils.MobileTokenFromJson(JsonUtils.getData(contextService,FILE_NAME));
        //Log.i(TAG, "Token saved : UID "+mobileToken.getUid()+" Token "+mobileToken.getToken());
    }


    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.esibank.inside.esiag.info");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public Socket createSocket(String host,int port) throws IOException {
        Socket s = new Socket(host,port);
        return s;
    }

    public void sendMessageToService(String key,String content){

        bundle = new Bundle();
        bundle.putString(key, content);
        Message toDeliver = new Message();
        toDeliver.setData(bundle);
        h.sendMessage(toDeliver);
    }

    public String read(BufferedInputStream reader){
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        try {
            stream = reader.read(b);
            response = new String(b, 0, stream);
        } catch (Exception e) {
            if(socket!=null){
                try {
                    socket.close();
                    //si erreur ou coupure lors de la lecture, relancer la socket
                    this.run();
                } catch (Exception e1) {
                    Log.i(TAG,"ERROR READ MESSAGE"+e);
                }
            }
        }
        return response;
    }

    public boolean send(String msg,PrintWriter writer){
        writer.write(msg);
        //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
        try {
            writer.flush();
        }catch (Exception e){
            Log.i(TAG,"ERROR SEND MESSAGE !"+e);
            if(socket!=null){
                try {
                    socket.close();
                    //si erreur ou coupure lors de l'écriture, relancer la socket
                    this.run();
                } catch (Exception e1) {
                    Log.i(TAG,"ERROR SEND MESSAGE"+e);
                }
            }
        }

        return true;
    }



    public static void setIsRunning(boolean isRunning) {
        ConnectSocket.isRunning = isRunning;
    }

    public static boolean isAlive(){
        return Thread.currentThread().isAlive();
    }



}
