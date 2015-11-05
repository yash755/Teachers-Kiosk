package com.example.yash.registerlogin;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button button;
    EditText editText,editText2;
    TextView textView,txt;
    String responseServer;

    Userlocalstore userlocalstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);

        textView =(TextView) findViewById(R.id.textView);
        txt = (TextView) findViewById(R.id.raw);

        button = (Button) findViewById(R.id.button);




        userlocalstore = new Userlocalstore(this);


        // check if you are connected or not
        if(isConnected()){
            Toast.makeText(getApplicationContext(), "You are Conncted", Toast.LENGTH_SHORT).show();
            button.setOnClickListener(this);
            textView.setOnClickListener(this);

        }
        else{
            Toast.makeText(getApplicationContext(), "You are Not Conncted", Toast.LENGTH_SHORT).show();
            button.setOnClickListener(this);
            textView.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                if(isConnected()) {
                    if(!validate())
                        Toast.makeText(getApplicationContext(), "Fill All Credentials", Toast.LENGTH_SHORT).show();
                    else {

                       /* AsyncT asyncT = new AsyncT();
                        asyncT.execute();*/

                      String username = editText.getText().toString();
                        String password = editText2.getText().toString();

                        User user = new User(username, password);

                        authenticate(user);

                       // userlocalstore.userData(user);
                       // userlocalstore.setUserloggedIn(true);

                      //  startActivity(new Intent(this, MainActivity.class));
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "You are Not Conncted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.textView:

                if(isConnected())
                startActivity(new Intent(this,Register.class));
                    else
                    Toast.makeText(getApplicationContext(), "You are Not Conncted", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    /*
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
    */

    private void authenticate(User user) {

        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchuserdatainbackground(user, new GetUserCallBack() {
            @Override
            public void done(User returneduser) {
                if(returneduser == null) {

                    showerrormessage();
                }
                else{
                    loguserin(returneduser);
                }
                }
            }
        );
    }

    private void showerrormessage(){
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(Login.this);
        dialogbuilder.setMessage("Worong Credentials");
        dialogbuilder.setPositiveButton("OKAY",null);
        dialogbuilder.show();
    }

    private void loguserin(User returneduser ){

        userlocalstore.userData(returneduser);
        userlocalstore.setUserloggedIn(true);

        startActivity(new Intent(this, MainActivity.class));

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


    private boolean validate(){
        if(editText.getText().toString().trim().equals(""))
            return false;
        else if(editText2.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }

    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost  httppost = new HttpPost("http://188.166.249.229:5000/login_action");

            try {

                JSONObject jsonobj = new JSONObject();


                jsonobj.put("user","13103485" );
                jsonobj.put("pass", "yash&9654195909");
                jsonobj.put("usertype", "S");
                jsonobj.put("date1", "24-02-1995");


                StringEntity se = new StringEntity( jsonobj.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

                System.out.println("I am here");
              //  post.setEntity(se);
               // response = client.execute(post);

               // List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
               // nameValuePairs.add(new BasicNameValuePair("req", jsonobj.toString()));

              //  Log.e("mainToPost", "mainToPost" + nameValuePairs.toString() + httppost.getURI());

                // Use UrlEncodedFormEntity to send in proper format which we need
               httppost.setEntity(se);

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                System.out.println("I am here");
                InputStream inputStream = response.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
                responseServer = str.getStringFromInputStream(inputStream);
                System.out.println(responseServer.toString());
                //Log.e("response", "response -----" + responseServer);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            txt.setText(responseServer);
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
