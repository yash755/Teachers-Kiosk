package com.example.yash.registerlogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom extends ArrayAdapter<String> {

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

        return customView;
    }

}
