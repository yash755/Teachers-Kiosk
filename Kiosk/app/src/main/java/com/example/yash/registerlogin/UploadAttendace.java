package com.example.yash.registerlogin;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class UploadAttendace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_attendace);


        DatabaseHelper db = new DatabaseHelper(this);
        db.getList();

        Cursor cr = db.getInformation();
        System.out.print(cr.getCount());

        cr.moveToFirst();
        final ArrayList<String> list = new ArrayList<>();
        while (!cr.isAfterLast()) {
            list.add(cr.getString(cr.getColumnIndex("eno")));
            cr.moveToNext();

        }
        cr.close();
    }

}
