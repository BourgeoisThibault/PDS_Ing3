package com.esipe.esibankm.esibankm;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.esipe.esibankm.esibankm.models.MobileToken;
import com.esipe.esibankm.esibankm.models.NotificationModel;
import com.esipe.esibankm.esibankm.utils.JsonUtils;
import com.esipe.esibankm.esibankm.utils.NotificationDBOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends ListActivity {
    private static NotificationDBOpenHelper mydb;
    public static ArrayList<NotificationModel> results = new ArrayList<NotificationModel>();
    private NotificationAdapter notifAdapter;
    public static final String ID_CLIENT = "idClient";
    private static MobileToken mobileToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_notification);
//        ListView listContent = (ListView)findViewById(R.id.list_new);
        mobileToken = JsonUtils.MobileTokenFromJson(JsonUtils.getData(getApplicationContext(),"data.json"));
        mydb = new NotificationDBOpenHelper(this);
        Log.i("SQL LITE","OK DATABASE");

        openAndQueryDatabase();

        List<NotificationModel> notif = results;
        notifAdapter = new NotificationAdapter(getApplicationContext(),notif);
        //ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this,android.R.layout.simple_list_item_1,clients);
//        listContent.setAdapter(notifAdapter);
        setListAdapter(notifAdapter);
    }
    public static void openAndQueryDatabase() {
        Log.i("SQL LITE","openAndQueryDatabase READ !");
        results = new ArrayList<NotificationModel>();
        Cursor c = mydb.getNotif(Integer.valueOf(mobileToken.getUid()));
        if (c != null ) {
            Log.i("SQL LITE","openAndQueryDatabase diff null !");
            NotificationModel n = new NotificationModel();
            while(c.moveToNext()) {
                n.setMessage(c.getString(c.getColumnIndex("message")));
                n.setTitle(c.getString(c.getColumnIndex("title")));
                n.setDate(c.getString(c.getColumnIndex("date")));
                n.setNotif_id(c.getString(c.getColumnIndex("notif_id")));
                results.add(n);
            }
        }else
            Log.e("SQLLITE","ERREUR READ !");

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.e("Click","bouton READ !");

        Intent i = new Intent(NotificationActivity.this,NotificationPop.class);
        i.putExtra(ID_CLIENT,position);
        startActivity(i);
    }

    public static boolean deleteNotif(String id){
        return mydb.deleteNotif(id);
    }


    @Override
    protected void onResume() {
        openAndQueryDatabase();
        super.onResume();
    }
}
