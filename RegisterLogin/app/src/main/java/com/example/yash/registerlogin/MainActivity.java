package com.example.yash.registerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button3;
    EditText editText,editText3;
    Userlocalstore userlocalstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText3 = (EditText) findViewById(R.id.editText3);

        button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(this);

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

        userlocalstore = new Userlocalstore(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                userlocalstore.clearUserdata();
                userlocalstore.setUserloggedIn(false);

                startActivity(new Intent(this,Login.class));
                break;
        }
    }
}
