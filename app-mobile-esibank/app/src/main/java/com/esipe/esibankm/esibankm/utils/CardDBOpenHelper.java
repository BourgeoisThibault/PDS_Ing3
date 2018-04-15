package com.esipe.esibankm.esibankm.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by usman on 15/04/2018.
 */

public class CardDBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "card.db";

    public static final String id = "id";
    public static final String card_num = "card_num";
    public static final String pin = "pin";
    public static final String name = "name";

    private HashMap hp;


    public CardDBOpenHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table card " +
                        "(id integer primary key, card_num VARCHAR,pin VARCHAR)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS card");
        onCreate(db);
    }

    public boolean insertCard (int id, String card_num, String pin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("card_num", card_num);
        contentValues.put("pin", pin);

        db.insert("card", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from card where id="+id+"", null );
        return res;
    }

//    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }


    public Integer deleteCard (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("card",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

}
