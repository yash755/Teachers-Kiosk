package com.example.gaurav.webkiosknotification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "NotifyList.db";
    public static final String TABLE_NAME = "notify_table";
    public static final String COL_1 = "tag";
    public static final String COL_2 = "value";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(tag String ,value String )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        // onCreate(db);

    }

 public void insertdata(String tag) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, tag);

        System.out.println(tag + "tag1");
        contentValues.put(COL_2,"no");

       db.insert(TABLE_NAME, null, contentValues);

 }

    public Cursor getList() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        return c;

    }

    public int getcount(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        return c.getCount();

    }

    public void onValueUpdate(String tag,String status){

        SQLiteDatabase db = this.getWritableDatabase();

       // Cursor cr1 = db.rawQuery("SELECT tag FROM notify_table where tag = '" + tag + "'", null);


            ContentValues values = new ContentValues();
            values.put(COL_2, status);
            db.update(TABLE_NAME, values, COL_1+"="+ "'" + tag + "'", null);

            System.out.println("I am yes");



    }

    public Cursor getStatus(String tag){

        SQLiteDatabase db = this.getWritableDatabase();
       Cursor cr = db.rawQuery("SELECT value FROM notify_table where tag = " + "'" + tag + "'", null);

        //    System.out.println(cr.getCount());
        //    if( cr != null)
        return cr;

        //    return null;

    }

    public Cursor yesvalue(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("SELECT tag FROM notify_table where value = 'yes' ", null);

        //    System.out.println(cr.getCount());
        //    if( cr != null)
        return cr;

        //    return null;

    }



}
