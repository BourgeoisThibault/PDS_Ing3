package com.esipe.esibankm.esibankm.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by usman on 13/05/2018.
 */

public class NotificationDBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "notifications.db";

    public static final String _id = "_id";
    public static final String title = "title";
    public static final String message = "message";
    public static final String date = "date";
    public static final String notif_id = "notif_id";

    public NotificationDBOpenHelper(Context context) {
        super(context, DATABASE_NAME , null, 10);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table notifications " +
                        "(_id integer, title VARCHAR, message VARCHAR, date VARCHAR, notif_id VARCHAR)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notifications");
        onCreate(db);
    }

    public Cursor getNotif(int _id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns ={"title","message","date","notif_id"};
//        Cursor res =  db.rawQuery( "select * from notifications where", null );
        Cursor res = db.query("notifications", columns, "_id=?", new String[] {String.valueOf(_id)}, null, null, null);
        Log.i("SQL LITE","GET CURSOR FROM NOTFI: "+_id);
        return res;
    }

    public boolean insertNotif (int _id, String title, String message, String notif_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", _id);
        contentValues.put("title", title);
        contentValues.put("message", message);
        contentValues.put("date", date);
        contentValues.put("notif_id", notif_id);

        db.insert("notifications", null, contentValues);
        Log.i("SQL LITE","OK INSERT NOTIF : "+message +" - Date : "+date + "NOTIF ID :"+notif_id);

        return true;
    }

    public boolean deleteNotif(String _id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("notifications",  "notif_id=?", new String[] { _id });
        Log.i("SQL LITE","delete CURSOR FROM NOTFI: "+_id);
        return true;
    }


}
