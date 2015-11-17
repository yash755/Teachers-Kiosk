package com.example.yash.registerlogin;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper  extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "StudentList.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "batch";
    public static final String COL_2 = "eno";
    public static final String COL_3 = "name";


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




}
