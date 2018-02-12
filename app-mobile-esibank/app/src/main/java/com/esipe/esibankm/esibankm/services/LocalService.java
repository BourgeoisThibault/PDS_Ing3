
package com.esipe.esibankm.esibankm.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.esipe.esibankm.esibankm.AccountsActivity;
import com.esipe.esibankm.esibankm.HomeActivity;
import com.esipe.esibankm.esibankm.R;
import com.esipe.esibankm.esibankm.TransactionActivity;
import com.esipe.esibankm.esibankm.models.NotificationModel;
import com.esipe.esibankm.esibankm.utils.JsonUtils;


import java.util.Random;
import java.util.Timer;


/**
 * Created by Usman ABID BUTT  on 10/11/2017.
 */

public class LocalService extends Service {
    private static final java.lang.String NOTIFICATION_CONTENT = "notification_content";
    private static final java.lang.String CONNECTION_STATE = "connection_state_failed";

    private NotificationManager mNM;
    private Timer timer = new Timer();
    private final String TAG="LocalService";
    private Handler mainHandler;
    private Handler h;
    private Random random;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService","I am in onStartCommand");
        //initialise random for the notification
        random = new Random();

        mainHandler = new Handler(Looper.getMainLooper());
        ConnectSocket.setIsRunning(true);
        h = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                if(msg.getData().containsKey(NOTIFICATION_CONTENT)) {
                    String result = bundle.getString(NOTIFICATION_CONTENT);
                    showNotification(result);
                }
                else{
                    String result = bundle.getString(CONNECTION_STATE);
                    Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();

                }
            }
        };
        Runnable connect = new ConnectSocket(h,getApplicationContext());
        Thread socketThread = new Thread(connect);
        socketThread.start();
        Log.i(TAG, "Thread started!!!");
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        mNM.cancel(R.string.local_service_started);
        _shutdownService();
    }

//    /**
//     * Show a notification while this service is running.
//     */
    public void showNotification(String content) {
        NotificationModel notif = JsonUtils.objectFromJson(content,NotificationModel.class);
        Log.i("Notification",notif.toString());

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent targetIntent = switchTargetNotification(notif.getTarget());

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(notif!=null){
            Notification builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_person_black_24dp)
                            .setContentTitle(notif.getTitle())
                            .setContentText(notif.getMessage())
                            .setGroup("alert_account")
                            .setGroupSummary(true)
                            .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                            .setLights(Color.RED, 3000, 3000)
                            .setSound(alarmSound)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setContentIntent(contentIntent)
                            .build();
            int NOTIFICATION_ID = random.nextInt(9999 - 1000) + 1000;
            Log.i("Notification","I am in on showNotification");


            mNM.notify(NOTIFICATION_ID, builder);
        }

    }

    public Intent switchTargetNotification(String target) {
        Log.i("target",target);
        Intent targetIntent =  new Intent(this, HomeActivity.class);
        if(target.equals("accounts")){
            targetIntent = new Intent(this, AccountsActivity.class);
        }else if(target.equals("transfer")){
            targetIntent = new Intent(this, TransactionActivity.class);
        }//
        //default target
        return targetIntent;
    }

    public void _shutdownService() {
        if (timer != null) timer.cancel();
        ConnectSocket.setIsRunning(false);
        Log.i(getClass().getSimpleName(), "Thread is dead !");
    }


}
