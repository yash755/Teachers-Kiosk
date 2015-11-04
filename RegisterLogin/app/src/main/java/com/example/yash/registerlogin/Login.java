package com.example.yash.registerlogin;

import android.content.Intent;
import android.os.Bundle;
 import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        button.setOnClickListener(this);
        textView.setOnClickListener(this);

        userlocalstore = new Userlocalstore(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:

                User user = new User(null,null);

                userlocalstore.userData(user);
                userlocalstore.setUserloggedIn(true);

              startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.textView:
                startActivity(new Intent(this,Register.class));
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
}
