package com.example.yash.registerlogin;


import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;

public class FileBrowser extends AppCompatActivity {
    private static final String TAG ="Loadlist" ;
    //In an Activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_browser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //loadFileList();
        System.out.println("tgttty");
        filebrowserdialog fl =new filebrowserdialog();
        //fl.loadFileList();
        fl.show(getSupportFragmentManager(),"");

    }

}


