package com.example.yash.registerlogin;

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

    Custom(Context context, ArrayList<String> name, ArrayList<String> name1)
    {
        super(context, R.layout.te, name);
        list1 = name;
        list2 = name1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.te, parent, false);
        TextView t1 = (TextView)customView.findViewById(R.id.te2);
        TextView t2 = (TextView)customView.findViewById(R.id.te3);

        t1.setText(list1.get(position));
        t2.setText(list2.get(position));


        Cursor cr =db.getStatus(t1.getText().toString());
        System.out.println(cr.getCount());

        if(cr.getCount() == 0)
            customView.setBackgroundColor(Color.WHITE);
            else if (cr.getCount() == 1) {
           // customView.setBackgroundColor(Color.RED);
            cr.moveToFirst();
            do{
                String data=cr.getString(cr.getColumnIndex("status"));
             if(data.equals("p"))
                 customView.setBackgroundColor(Color.BLUE);
                else
                 customView.setBackgroundColor(Color.RED);
            }while(cr.moveToNext());
         /*   ArrayList<String> list = new ArrayList<>();

            // String name = null;
            while (!cr.isAfterLast()) {
              //  name = cr.getString(cr.getColumnIndex("eno"));
                list.add(cr.getString(cr.getColumnIndex("eno")));
                cr.moveToNext();

            }
            cr.close();

            System.out.println(list);*/
    /*    final
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            list.add(cr.getString(cr.getColumnIndex("eno")));
            cr.moveToNext();

        }


        System.out.println(list);*/

        }

        return customView;
    }

}
