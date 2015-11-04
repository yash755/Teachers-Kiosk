package com.example.yash.registerlogin;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
 import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button   button;
    EditText editText,editText2;
    TextView textView;

    Userlocalstore userlocalstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        textView =(TextView) findViewById(R.id.textView);

        button = (Button) findViewById(R.id.button);




        userlocalstore = new Userlocalstore(this);


        // check if you are connected or not
        if(isConnected()){
            Toast.makeText(getApplicationContext(), "You are Conncted", Toast.LENGTH_SHORT).show();
            button.setOnClickListener(this);
            textView.setOnClickListener(this);

        }
        else{
            Toast.makeText(getApplicationContext(), "You are Not Conncted", Toast.LENGTH_SHORT).show();
            button.setOnClickListener(this);
            textView.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                if(isConnected()) {
                    if(!validate())
                        Toast.makeText(getApplicationContext(), "Fill All Credentials", Toast.LENGTH_SHORT).show();
                    else {

                        User user = new User(null, null);

                        userlocalstore.userData(user);
                        userlocalstore.setUserloggedIn(true);

                        startActivity(new Intent(this, MainActivity.class));
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "You are Not Conncted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.textView:

                if(isConnected())
                startActivity(new Intent(this,Register.class));
                    else
                    Toast.makeText(getApplicationContext(), "You are Not Conncted", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    /*
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    */


    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


    private boolean validate(){
        if(editText.getText().toString().trim().equals(""))
            return false;
        else if(editText2.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }
}
