package com.esipe.esibankm.esibankm.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.esipe.esibankm.esibankm.services.LocalService;

/**
 * Created by Usman ABID BUTT on 26/11/2017.
 */

public class AutoStartService extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent launch = new Intent(context,LocalService.class);
        context.startService(launch);
        Log.i("Autostart", "Service started");

    }
}
