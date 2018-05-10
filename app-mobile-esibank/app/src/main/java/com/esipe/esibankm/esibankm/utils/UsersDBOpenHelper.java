package com.esipe.esibankm.esibankm.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by usman on 07/05/2018.
 */

public class UsersDBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";

    public static final String id = "id";
    public static final String name = "name";

    public UsersDBOpenHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table users " +
                        "(id integer primary key, name VARCHAR)"
        );
//        insertUser(111001,"G. Giraud");
//        insertUser(111002,"A. Brenner");
//        insertUser(111003,"O. Michel");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public Cursor getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users where id="+id+"", null );
        return res;
    }

    public boolean insertUser (int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        db.insert("users", null, contentValues);
        return true;
    }


    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users", null );
        return res;
    }
}
