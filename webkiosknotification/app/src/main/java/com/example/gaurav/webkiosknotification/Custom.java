package com.example.gaurav.webkiosknotification;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom extends ArrayAdapter<String> {

    DatabaseHelper db = new DatabaseHelper(getContext());


    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();

    Custom(Context context, ArrayList<String> name,ArrayList<String> name1)
    {
        super(context, R.layout.list, name);
        list1 = name;
        list2 = name1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list, parent, false);
        TextView t1  = (TextView)customView.findViewById(R.id.tv5);

       t1.setText(list1.get(position));

        Cursor cr =db.getStatus(t1.getText().toString());
        System.out.println(cr.getCount());

        if(cr.getCount() == 0)
            customView.setBackgroundColor(Color.WHITE);
        else if (cr.getCount() == 1) {
            // customView.setBackgroundColor(Color.RED);
            cr.moveToFirst();
            do{
                String data=cr.getString(cr.getColumnIndex("value"));
                if(data.equals("yes"))
                    customView.setBackgroundColor(Color.YELLOW);
                else
                    customView.setBackgroundColor(Color.WHITE);
            }while(cr.moveToNext());


        }

        return customView;
    }

}
