package com.example.yash.registerlogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by yash on 5/11/15.
 */
public class ServerRequest {

    ProgressDialog progressDialog;

    public static final int Connection_Timeout = 1000*15;
    public static final String Server_Address = " http://172.16.68.6:8090/httpclient.html";

    public ServerRequest(Context context){

        System.out.println("I am here");
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Working....");
        System.out.println("I am here");
        progressDialog.setMessage("Please Wait");


    }

    public void storeuserdatainbackground(User user,GetUserCallBack userCallBack){

        progressDialog.show();
        new storeuserdataasynctask(user,userCallBack).execute();
    }

    public void fetchuserdatainbackground(User user,GetUserCallBack userCallBack){
        progressDialog.show();
        new fetchuserdataasynctask(user,userCallBack).execute();
    }

    public class storeuserdataasynctask extends AsyncTask<Void,Void,Void> {

        User user;
        GetUserCallBack userCallBack;

        public storeuserdataasynctask(User user,GetUserCallBack userCallBack){

            this.user = user;
            this.userCallBack = userCallBack;

        }


        @Override
        protected Void doInBackground(Void... params) {

          //  HttpClient httpclient = new DefaultHttpClient();
           // HttpPost  httppost = new HttpPost("http://188.166.249.229:5000/login_action");
          //  ArrayList<NameValuePair> datatosend = new ArrayList<>();
          //  datatosend.add(new BasicNameValuePair("username", user.username));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, Connection_Timeout);
            HttpConnectionParams.setSoTimeout(httpRequestParams, Connection_Timeout);

            HttpClient httpclient = new DefaultHttpClient((org.apache.http.params.HttpParams) httpRequestParams);
            HttpPost httppost = (HttpPost) new HttpPost(Server_Address);

            try{

                JSONObject jsonobj = new JSONObject();


                jsonobj.put("user",user.username);
                jsonobj.put("pass",user.password);
                jsonobj.put("usertype", "S");
                jsonobj.put("date1", "24-02-1995");


                StringEntity se = new StringEntity( jsonobj.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                HttpResponse response = httpclient.execute(httppost);
         //  post.setEntity(new URLEncoderFormEntity(dataToSend));
               // client.execute((HttpUriRequest) post);
            }catch (Exception e){
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            userCallBack.done(null);


            super.onPostExecute(aVoid);
        }
    }

    public class fetchuserdataasynctask extends AsyncTask<Void,Void,User> {

        User user;
        GetUserCallBack userCallBack;




        public fetchuserdataasynctask(User user,GetUserCallBack userCallBack){

            this.user = user;
            this.userCallBack = userCallBack;

            System.out.println("I am here");
            System.out.println(userCallBack);
            System.out.println(user.username);
            System.out.println(user.password);

        }

        @Override
/*        protected User doInBackground(Void... params) {

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, Connection_Timeout);
            HttpConnectionParams.setSoTimeout(httpRequestParams, Connection_Timeout);

            HttpClient httpclient = new DefaultHttpClient((org.apache.http.params.HttpParams) httpRequestParams);
            HttpPost httppost = (HttpPost) new HttpPost(Server_Address);
*/

                protected User doInBackground(Void... voids) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost  httppost = new HttpPost("http://188.166.249.229:5000/login_action");
                User returneduser = null;

            System.out.println("I am here");

            try{

                JSONObject jsonobj = new JSONObject();

                System.out.println("I am here");
                jsonobj.put("user",user.username);
                jsonobj.put("pass",user.password);
                jsonobj.put("usertype", "S");
                jsonobj.put("date1", "24-02-1995");

                StringEntity se = new StringEntity( jsonobj.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

                System.out.println("I am here");

                httppost.setEntity(se);


                HttpResponse response = httpclient.execute(httppost);
                System.out.println("I am here");
               InputStream inputStream = response.getEntity().getContent();
                Login.InputStreamToStringExample str = new Login.InputStreamToStringExample();
                String responseServer = str.getStringFromInputStream(inputStream);
                System.out.println(responseServer);



                System.out.println("I am here");
/*
                HttpEntity entity = (HttpEntity) response.getEntity();
                System.out.println("I am here");

                String result = EntityUtils.toString(entity);
                System.out.println("I am here");
                System.out.println(result.toString());*/

                JSONObject jsonobj1 = new JSONObject(responseServer);

                if(jsonobj1.length() == 0){
                   returneduser = null;
                }
                else {

                    String username = (String) jsonobj1.get("error");
                    System.out.println(username);

           //         String password = (String) jsonobj1.get("password");

                //    returneduser = new User(username,password);


                }


                //  post.setEntity(new URLEncoderFormEntity(dataToSend));
                // client.execute((HttpUriRequest) post);
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(User returneduser) {

            progressDialog.dismiss();
            userCallBack.done(returneduser);


            super.onPostExecute(returneduser);
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
        public static String getStringFromInputStream(InputStream is) {

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
