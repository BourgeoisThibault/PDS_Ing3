package com.esipe.esibankm.esibankm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by usman on 13/05/2018.
 */

public class NotificationPop extends Activity {

    private int ID_NOTIF;
    private String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_notification_confirm);
        TextView title= findViewById(R.id.title_pop);
        TextView mess= findViewById(R.id.message_pop);
        TextView date= findViewById(R.id.date_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ID_NOTIF = getIntent().getIntExtra(NotificationActivity.ID_CLIENT,0);

        int w = dm.widthPixels;
        int h = dm.heightPixels;

        getWindow().setLayout((int)(w*.8),(int)(h*.5));

        title.setText(NotificationActivity.results.get(ID_NOTIF).getTitle().toString());
        mess.setText(NotificationActivity.results.get(ID_NOTIF).getMessage().toString());
        date.setText(NotificationActivity.results.get(ID_NOTIF).getDate().toString());


        Log.i("NotifPOP",String.valueOf(ID_NOTIF));
        String id = NotificationActivity.results.get(ID_NOTIF).getNotif_id();
        Log.i("NotifPOP","id est = : "+id);

        uid = NotificationActivity.results.get(ID_NOTIF).getNotif_id();
    }


    public void onDeleteNotification(View view) {
        if(NotificationActivity.deleteNotif(uid)){
            NotificationActivity.openAndQueryDatabase();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            Toast.makeText(getApplicationContext(),
                    "Notification supprimée avec succès !",
                    Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(),
                    "Erreur de suppression ! ",
                    Toast.LENGTH_SHORT).show();
    }
}
