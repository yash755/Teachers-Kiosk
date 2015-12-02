package com.example.gaurav.webkiosknotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pushbots.push.Pushbots;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private void subscribe(String[] tags){
    Pushbots.sharedInstance().init(this);
    for (int i=0;i<tags.length;i++){
        Pushbots.sharedInstance().tag(tags[i]);
    }


}

    DatabaseHelper db;
    Button subscribe;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);
        count = db.getcount();


        if (db.getcount() == 0) {

            ListRequest listrequest = new ListRequest(this);
            listrequest.fetchuserdatainbackground(new GetUserCallBAck() {
                @Override
                public void done(JSONArray jsonArray) {

                    if (jsonArray.length() > 0) {

                        System.out.println(jsonArray + "Length");
                        System.out.println(jsonArray.length() + "Length");
                        inserthere(jsonArray);

                    } else
                        Toast.makeText(getApplicationContext(), "Try Later!!!", Toast.LENGTH_SHORT).show();

                }
            });


        }

        subscribe = (Button)findViewById(R.id.subscribe);

        subscribe.setOnClickListener(this);
    }

    public void inserthere(JSONArray jsonArray){

        DatabaseHelper list_db = new DatabaseHelper(this);

        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                JSONObject object = jsonArray.getJSONObject(i);
                System.out.println(object + "object");
                String tags = (String) object.get("tag");
                list_db.insertdata(tags);

            } catch (JSONException e) {
                Log.e("SAMPLE", "error getting result " + i, e);
            }
        }
        Toast.makeText(getApplicationContext(), "TimeTable updated!!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        // startActivity(new Intent(this, AttendanceListActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        startActivity(new Intent(this,Subscribe.class));


    }
}
