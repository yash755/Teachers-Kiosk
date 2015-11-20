package com.example.yash.registerlogin;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class yash1 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yash1);

        final DatabaseHelper db = new DatabaseHelper(yash1.this);
        db.createTable();

        Cursor cr = db.getInformation();
        System.out.print(cr.getCount());

        cr.moveToFirst();
        final ArrayList<String> enno = new ArrayList<>();
        while (!cr.isAfterLast()) {
            enno.add(cr.getString(cr.getColumnIndex("eno")));
            cr.moveToNext();

        }
        cr.close();

        DatabaseHelper db1 = new DatabaseHelper(yash1.this);
        Cursor cr1 = db1.getNames();

        cr1.moveToFirst();
        final ArrayList<String> names = new ArrayList<>();
        while (!cr1.isAfterLast()) {
            names.add(cr1.getString(cr1.getColumnIndex("name")));
            cr1.moveToNext();
            System.out.println(names);
            System.out.println("I am here!!!!");
        }
        cr1.close();


        System.out.println(names);
        System.out.println(enno);
        ListAdapter adpt = new Custom(this, enno, names);
        final ListView li = (ListView) findViewById(R.id.lv3);
        li.setAdapter(adpt);




        li.setOnTouchListener(new SwipeDetector());

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {


              //  arg0.getChildAt(position).setBackgroundColor(Color.BLUE);
             //   arg1.setBackgroundColor(Color.BLUE);
              //  Toast.makeText(yash1.this, "Clicked", Toast.LENGTH_SHORT).show();
                if (SwipeDetector.swipeDetected()) {
                  //
                   if (SwipeDetector.getAction() == SwipeDetector.Action.RL) {
                       arg1.setBackgroundColor(Color.RED);
                       db.onAttendanceInsert(enno.get(position), "a");

                    } else if (SwipeDetector.getAction() == SwipeDetector.Action.LR) {

                     // arg0.getChildAt(position).setBackgroundColor(Color.BLUE);
                       //arg1.setTranslationX(parseFloat("70dp"));
                     /*  if (save != -1 && save != position){
                           arg0.getChildAt(position).setBackgroundColor(Color.GREEN);
                       }
                       save = position;*/
                       arg1.setBackgroundColor(Color.BLUE);
                       db.onAttendanceInsert(enno.get(position),"p");
               //        db.onAttendanceInsert(names.get(position),"p");
                      // Toast.makeText(yash1.this, "PRESENT", Toast.LENGTH_SHORT).show();
                    }
                    else
                       Toast.makeText(yash1.this, "Wrong Press", Toast.LENGTH_SHORT).show();
                }
            }
        };

        li.setOnItemClickListener(listener);


    }




}
