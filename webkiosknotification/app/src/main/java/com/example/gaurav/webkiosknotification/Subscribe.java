package com.example.gaurav.webkiosknotification;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pushbots.push.Pushbots;

import java.util.ArrayList;

public class Subscribe extends AppCompatActivity {


    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Pushbots.sharedInstance().init(this);

        db = new DatabaseHelper(this);

   /*     FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cr = db.yesvalue();

                int i =2;
                cr.moveToFirst();

                String[] tags = new String[20];
                tags[0] = "13103485";
                tags[1] = "B2";

                while (!cr.isAfterLast()) {
                    tags[i] = cr.getString(cr.getColumnIndex("tag")).toString();
                    i++;
                    cr.moveToNext();
                }

                cr.close();

                subscribe(tags);
                Toast.makeText(Subscribe.this, "Subscribed!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);

            }
        });*/



        Cursor cr = db.getList();

        cr.moveToFirst();
        final ArrayList<String> notifylist = new ArrayList<>();
        while (!cr.isAfterLast()) {
            notifylist.add(cr.getString(cr.getColumnIndex("tag")));
            cr.moveToNext();

        }




        cr.moveToFirst();
        final ArrayList<String> value = new ArrayList<>();
        while (!cr.isAfterLast()) {
            value.add(cr.getString(cr.getColumnIndex("value")));
            cr.moveToNext();

        }



        cr.close();


        ListAdapter adpt = new Custom(this, notifylist, value);
        final ListView li = (ListView) findViewById(R.id.lv);
        li.setAdapter(adpt);

        li.setOnTouchListener(new SwipeDetector());

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                if (SwipeDetector.swipeDetected()) {

                    if (SwipeDetector.getAction() == SwipeDetector.Action.LR) {
                        arg1.setBackgroundColor(Color.YELLOW);
                        db.onValueUpdate(notifylist.get(position), "yes");
                        Pushbots.sharedInstance().tag(notifylist.get(position));
                    }
                    else if (SwipeDetector.getAction() == SwipeDetector.Action.RL) {
                        arg1.setBackgroundColor(Color.WHITE);
                        db.onValueUpdate(notifylist.get(position), "no");
                        Pushbots.sharedInstance().untag(notifylist.get(position));

                    }
                }
                else
                    Toast.makeText(Subscribe.this, "Wrong Press", Toast.LENGTH_SHORT).show();

            }
        };

        li.setOnItemClickListener(listener);


    }


}
