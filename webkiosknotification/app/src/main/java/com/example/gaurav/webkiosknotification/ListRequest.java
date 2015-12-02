package com.example.gaurav.webkiosknotification;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ListRequest{

    ProgressDialog progressDialog;

 public ListRequest(Context context){


        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Fetching List");
        progressDialog.setMessage("Working...");


        }


public void fetchuserdatainbackground(GetUserCallBAck getUserCallBAck){
        progressDialog.show();
        new fetchuserdataasynctask(getUserCallBAck).execute();
        }


public class fetchuserdataasynctask extends AsyncTask<Void, Void, JSONArray> {

    GetUserCallBAck getUserCallBAck;

    public fetchuserdataasynctask(GetUserCallBAck getUserCallBAck){

        this.getUserCallBAck = getUserCallBAck;
    }

    @Override
    protected JSONArray doInBackground(Void... params) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://probase.anip.xyz:8080/tags");

        JSONArray jsonArray = null;
        try {

            JSONObject jsonobj = new JSONObject();

            jsonobj.put("teachercode","ram");

            StringEntity se = new StringEntity(jsonobj.toString());
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


            httppost.setEntity(se);


            HttpResponse response = httpclient.execute(httppost);
            InputStream inputStream = response.getEntity().getContent();
            ListRequest.InputStreamToStringExample str = new ListRequest.InputStreamToStringExample();
            String responseServer = str.getStringFromInputStream(inputStream);

            System.out.println(responseServer + "yuy");
           // System.out.println("I am here" + "hjh" + "hjh77");
            JSONObject jsonobj1 = new JSONObject(responseServer);
            System.out.println(jsonobj1 + "lll");
              jsonArray = jsonobj1.getJSONArray("tags");
          //  System.out.println(jsonArray.toString() + "jkjkjk");

            return jsonArray;

        } catch (Exception e) {
            e.printStackTrace();
        }



        return jsonArray;
    }



    @Override
    protected void onPostExecute(JSONArray jsonArray) {


        progressDialog.dismiss();

        getUserCallBAck.done(jsonArray);
        super.onPostExecute(jsonArray);

    }
}

public static class InputStreamToStringExample {

    public static void main(String[] args) throws IOException {

        // intilize an InputStream
        InputStream is =
                new ByteArrayInputStream("file content..blah blah".getBytes());

        String result = getStringFromInputStream(is);

        System.out.println(result);
        System.out.println("Done");

    }

    // convert InputStream to String
    static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}







}

