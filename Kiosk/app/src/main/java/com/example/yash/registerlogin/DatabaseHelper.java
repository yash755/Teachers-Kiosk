package com.example.yash.registerlogin;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DatabaseHelper  extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "StudentList.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "batch";
    public static final String COL_2 = "eno";
    public static final String COL_3 = "name";
    public static final String TABLE_NAME1 = "attendance_table";
    public static final String COL_4 = "date";
    public static final String COL_5 = "time";
    public static final String COL_6 = "eno";
    public static final String COL_7 = "status";







    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(batch String ,eno String Primary Key,name String )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
       // onCreate(db);

    }


    public void insertdata(String batch, String enno, String name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, batch);
        contentValues.put(COL_2, enno);
        contentValues.put(COL_3, name);

    final Cursor cursor = db.rawQuery("SELECT eno FROM student_table  ", null);


   //  if (cursor.getCount() == 0) {

     //    System.out.println("Hii" + cursor.getCount());
           db.insert(TABLE_NAME, null, contentValues);
       //   cursor.close();
     //}


    }


    public Cursor getInformation() {
        System.out.println("I am starting!!!!");
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(db);
        final Cursor c = db.rawQuery("SELECT eno FROM " + TABLE_NAME , null);
        if(c != null)
            System.out.println("I am not here!!!!" + "JJJJJ" + c.getCount());
        return c;


    }

    public Cursor getNames() {
        System.out.println("I am starting!!!!");
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(db);
        final Cursor c = db.rawQuery("SELECT name FROM " + TABLE_NAME , null);
        if(c != null)
            System.out.println("I am not here!!!!" + "JJJJJ" + c.getCount());
        return c;

    }

    public Cursor getStatus(String enno){

        SQLiteDatabase db = this.getWritableDatabase();
        Date now = new Date();
        String dateis = new SimpleDateFormat("ddMMyyyy").format(now);
        String temptable=TABLE_NAME1 + dateis;

        Cursor cr = db.rawQuery("SELECT status FROM " + temptable + " where eno = '" + enno + "'", null);

    //    System.out.println(cr.getCount());
    //    if( cr != null)
            return cr;

    //    return null;

    }

    public void onAttendanceInsert(String enno,String status){

        SQLiteDatabase db = this.getWritableDatabase();

        Date now = new Date();
        String dateis = new SimpleDateFormat("ddMMyyyy").format(now);
        String temptable=TABLE_NAME1 + dateis;

        final Cursor cr1 = db.rawQuery("SELECT eno FROM " + temptable + " where eno = '" + enno + "'", null);

        System.out.println(cr1.getCount());

        if(cr1.getCount() != 0){

            ContentValues values = new ContentValues();
            values.put(COL_7, status);
            db.update(temptable, values, COL_6+"="+enno, null);

            System.out.println("I am yes");

        } else{

            ContentValues contentValues = new ContentValues();
            Date now1 = new Date();
            String date = new SimpleDateFormat("dd-MM-yyyy").format(now1);

         /*   Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
            Date currentLocalTime = cal.getTime();
            DateFormat date12 = new SimpleDateFormat("HH:mm a");

            date12.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
            String localTime = date.format(String.valueOf(currentLocalTime));*/
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR);

           // long localTime= System.currentTimeMillis();



            contentValues.put(COL_4, date);
            contentValues.put(COL_5,hour);
            contentValues.put(COL_6, enno);
            contentValues.put(COL_7,status);




            db.insert(temptable, null, contentValues);


        }




    }

    public void createTable(){

        SQLiteDatabase db = this.getWritableDatabase();

        Date now = new Date();
        String date = new SimpleDateFormat("ddMMyyyy").format(now);
        String temp=TABLE_NAME1 + date;

        Cursor f = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name ='" + temp +"'",null);

        if(f.getCount() == 0) {
            db.execSQL("create table " + temp + "(date Date ,time DateTime,eno String Primary Key,status string)");
            System.out.println("Table Created");
        }
        else {
            System.out.println("Table Not Created");
        }




    }

    public Cursor getList() {
        System.out.println("I am starting!!!!");
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(db);
        final Cursor c = db.rawQuery("SELECT name FROM " + TABLE_NAME , null);
        if(c != null)
            System.out.println("I am not here!!!!" + "JJJJJ" + c.getCount());
        return c;


    }





}
