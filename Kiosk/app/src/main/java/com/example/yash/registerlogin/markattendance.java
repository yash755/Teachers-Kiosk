package com.example.yash.registerlogin;

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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class markattendance {

    ProgressDialog progressDialog;

    public markattendance(Context context){

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Working....");
        progressDialog.setMessage("Please Wait");


    }



    public void fetchuserdatainbackground(User user,GetUserCallBack userCallBack){
        progressDialog.show();
        new fetchuserdataasynctask(user,userCallBack).execute();
    }



    public class fetchuserdataasynctask extends AsyncTask<Void,Void,User> {

        User user;
        GetUserCallBack userCallBack;




        public fetchuserdataasynctask(User user,GetUserCallBack userCallBack){

            this.user = user;
            this.userCallBack = userCallBack;


        }

        @Override
        protected User doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://probase.anip.xyz:8080/login_action");
            User returneduser = null;

            try{

                JSONObject jsonobj = new JSONObject();

                System.out.println("I am here");
                jsonobj.put("user", user.user);
                jsonobj.put("pass", user.password);
                jsonobj.put("usertype",user.usertype);
                jsonobj.put("date1",user.date);

                String pass = (String) jsonobj.get("pass");

                StringEntity se = new StringEntity( jsonobj.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


                httppost.setEntity(se);


                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                ServerRequest.InputStreamToStringExample str = new ServerRequest.InputStreamToStringExample();
                String responseServer = str.getStringFromInputStream(inputStream);


                System.out.println(responseServer);


                JSONObject jsonobj1 = new JSONObject(responseServer);

                if(jsonobj1.length() == 1){

                    String error = (String) jsonobj1.get("error");
                    returneduser = new User(error);

                }
                else {

                    String username = (String) jsonobj1.get("user");
                    String authkey = (String) jsonobj1.get("authkey");
                    String success = (String) jsonobj1.get("success");
                    String usertype = (String) jsonobj1.get("usertype");
                    System.out.println(username + authkey + success + usertype + pass);

                    returneduser = new User(username,pass,authkey,success,usertype);


                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return returneduser;
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
