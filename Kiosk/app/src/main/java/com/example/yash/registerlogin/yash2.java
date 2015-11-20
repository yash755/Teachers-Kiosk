package com.example.yash.registerlogin;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class yash2 extends ArrayAdapter<String> {

    ArrayList<String> list1 = new ArrayList<>();
    yash2(Context context, ArrayList<String> name)
    {
        super(context, R.layout.activity_yash2, name);
        list1 = name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.activity_yash2, parent, false);
        TextView t1 = (TextView)customView.findViewById(R.id.tv3);

        t1.setText(list1.get(position));
        return customView;
    }
}
