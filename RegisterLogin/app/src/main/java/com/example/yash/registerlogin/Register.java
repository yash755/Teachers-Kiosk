package com.example.yash.registerlogin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button button2;
    EditText editText,editText3,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText = (EditText) findViewById(R.id.editText);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText2 = (EditText) findViewById(R.id.editText2);

        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(this);

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
            case R.id.button2:

                String username = editText.getText().toString();
                String name     = editText3.getText().toString();
                String password = editText2.getText().toString();

                User registerData = new User(username,name,password);

                break;
        }


    }

}
