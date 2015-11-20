/*package com.example.yash.registerlogin;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AttendanceListActivity extends ListActivity {*/

    //public FancyAdapter mFancyAdapter;
/*
    @Override
   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_list);

        DatabaseHelper db = new DatabaseHelper(AttendanceListActivity.this);

        Cursor cr = db.getInformation();
        System.out.print(cr.getCount());

        cr.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while (!cr.isAfterLast()) {
            names.add(cr.getString(cr.getColumnIndex("eno")));
            cr.moveToNext();
            System.out.println(names);
            System.out.println("I am here!!!!");
        }
        cr.close();
        String[] kames = names.toArray(new String[names.size()]);


    //    ListAdapter adpt = new Custom(this, kames);
        ListView lis = (ListView) findViewById(R.id.listView);
        lis.setAdapter(adpt);

//        mFancyAdapter = new FancyAdapter(kames);
        //      setListAdapter(mFancyAdapter);


        ListView lv = (ListView) findViewById(R.id.listView);

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(AttendanceListActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
/*
    private class FancyAdapter extends BaseAdapter {

        private String[] mData;

        public FancyAdapter(String[] data) {
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.length;
        }

        @Override
        public String getItem(int position) {
            return mData[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView result;

            if (convertView == null) {
                result = (TextView) getLayoutInflater().inflate(R.layout.text_item, parent, false);

            } else {
                result = (TextView) convertView;

            }

            final String cheese = getItem(position);
            result.setText(cheese);

            return result;
        }
    }
}*/
