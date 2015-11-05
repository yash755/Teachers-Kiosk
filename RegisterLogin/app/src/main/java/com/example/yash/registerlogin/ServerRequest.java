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

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;

/**
 * Created by yash on 5/11/15.
 */
public class ServerRequest {

    ProgressDialog progressDialog;

    public static final int Connection_Timeout = 1000*15;
    public static final String Server_Address = " http://188.166.249.229:5000/login_action";

    public ServerRequest(Context context){

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Working....");
        progressDialog.setMessage("Please Wait");

    }

    public void storeuserdatainbackground(User user,GetUserCallBack userCallBack){

        progressDialog.show();
        new storeuserdataasynctask(user,userCallBack).execute();
    }

    public void fetchuserdatainbackground(User user,GetUserCallBack userCallBack){
        progressDialog.show();
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

            HttpClient client = new DefaultHttpClient((org.apache.http.params.HttpParams) httpRequestParams);
            HttpClient post = (HttpClient) new HttpPost(Server_Address);

            try{

                JSONObject jsonobj = new JSONObject();


                jsonobj.put("user","13103485" );
                jsonobj.put("pass", "yash&9654195909");
                jsonobj.put("usertype", "S");
                jsonobj.put("date1", "01-12-1994");


                StringEntity se = new StringEntity( jsonobj.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
         //  post.setEntity(new URLEncoderFormEntity(dataToSend));
                client.execute((HttpUriRequest) post);
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
}
