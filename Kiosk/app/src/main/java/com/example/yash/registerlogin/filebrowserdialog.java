package com.example.yash.registerlogin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by gaurav on 19/11/15.
 */
public class filebrowserdialog extends DialogFragment {
    public String[] mFileList;
    public File mPath = new File(Environment.getExternalStorageDirectory() + "//");
    public String mChosenFile;
    public static final String FTYPE = "";
    public static final int DIALOG_LOAD_FILE = 1000;

    public void loadFileList() {
        System.out.println("tgttty");

        try {
            System.out.println(mPath.mkdirs());
        }
        catch(SecurityException e) {
            System.out.println("inside security Exception");
            //Log.e(TAG, "unable to write on the sd card " + e.toString());
        }
        if(mPath.exists()) {

            mFileList = mPath.list();


        }
        else {
            mFileList= new String[0];
            System.out.println("2tgttty");

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        loadFileList();
        builder.setTitle("Select Files")
                .setPositiveButton("open", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setPositiveButton("select", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        String[] a= new String[4];
        a[0]="GAurav";
        a[1]="Shukla";
        a[2]="is";

        builder.setItems(mFileList,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mChosenFile = mFileList[which];
                //you can do stuff with the file here too
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }


}