package com.example.yash.registerlogin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   DatabaseHelper teacher_db;


    Button button3,button2;
    TextView textView;
    Userlocalstore userlocalstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button3 = (Button) findViewById(R.id.button3);
        button2 = (Button) findViewById(R.id.button2);

        button3.setOnClickListener(this);
        button2.setOnClickListener(this);


        userlocalstore = new Userlocalstore(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        if(authenticate() == true)
        {
            displayUserdetails();
        }
        else
            startActivity(new Intent(this,Login.class));



    }

    private boolean authenticate(){
        return userlocalstore.getuserloggedIn();
    }

    public void displayUserdetails(){

        User user = userlocalstore.getloggedInUser();

        textView.setText("Hello" + user.user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                userlocalstore.clearUserdata();
                userlocalstore.setUserloggedIn(false);
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.button2:
                teacher_db = new DatabaseHelper(this);
                if (isConnected()) {
                    fetch();
                } else {
                    Toast.makeText(getApplicationContext(), "You are Not Conncted!!!", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(), "Hello!!!", Toast.LENGTH_SHORT).show();

                break;

        }

    }

    private void fetch(){
        User user = userlocalstore.getloggedInUser();
        AttendanceFetch attendanceFetch = new AttendanceFetch(this);
        attendanceFetch.fetchuserdatainbackground(user, new GetDataCallBack() {
                    @Override
                    public void done(JSONArray jsonArray) {


                        if (jsonArray.length() > 0) {

                            System.out.println(jsonArray.length());
                            System.out.println(jsonArray);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                try {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String batch = (String) object.get("batch");
                                    String eno = (String) object.get("eno");
                                    String name = (String) object.get("name");
                                    teacher_db.insertdata(batch, eno, name);
                                } catch (JSONException e) {
                                    Log.e("SAMPLE", "error getting result " + i, e);
                                }

                            }
                            Toast.makeText(getApplicationContext(), "Data is successfully updated!!!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Sorry try after sometime!!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );



    }



    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }



}

