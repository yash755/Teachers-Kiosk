package com.example.yash.registerlogin;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.Handler;

public class AttendanceFetch{

    ProgressDialog progressDialog;

    public AttendanceFetch(Context context){


        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Working....");
        progressDialog.setMessage("Please Wait");


    }


    public void fetchuserdatainbackground(User user, GetDataCallBack getDataCallBack){
        progressDialog.show();
        new fetchuserdataasynctask(user,getDataCallBack).execute();
    }


    public class fetchuserdataasynctask extends AsyncTask<Void, Void, JSONArray> {
        User user;
        GetDataCallBack getDataCallBack;

        public fetchuserdataasynctask(User user, GetDataCallBack getDataCallBack){

            this.user = user;
            this.getDataCallBack = getDataCallBack;
        }

        @Override
        protected JSONArray doInBackground(Void... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://probase.anip.xyz:8080/students/all/list");

            JSONArray jsonArray = null;
            try {

                JSONObject jsonobj = new JSONObject();

                System.out.println("I am here");
                jsonobj.put("user", user.user);

                StringEntity se = new StringEntity(jsonobj.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


                httppost.setEntity(se);


                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                ServerRequest.InputStreamToStringExample str = new ServerRequest.InputStreamToStringExample();
                String responseServer = str.getStringFromInputStream(inputStream);

             JSONObject jsonobj1 = new JSONObject(responseServer);
                jsonArray = jsonobj1.getJSONArray("students");
                System.out.println(jsonArray);

                return jsonArray;

            } catch (Exception e) {
                e.printStackTrace();
            }



            return jsonArray;
        }



        @Override
        protected void onPostExecute(JSONArray jsonArray) {


            progressDialog.dismiss();
        //    super.onPostExecute(jsonArray);



         /*   if (jsonArray.length() > 0) {
                System.out.println(jsonArray.length());
                MainActivity.writeJSONArray(jsonArray, 1);
            }
            else {
                System.out.println(jsonArray.length());
                MainActivity.writeJSONArray(jsonArray, 0);
            }*/
            getDataCallBack.done(jsonArray);
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

