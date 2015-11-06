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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button3;
    TextView textView;
    Userlocalstore userlocalstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(this);


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

                startActivity(new Intent(this,Login.class));
                break;
        }
    }
}
