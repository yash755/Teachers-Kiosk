package com.example.yash.registerlogin;

import android.content.Context;
import android.content.SharedPreferences;

public class Userlocalstore{

    public static final String SP_Name = "userDetails";
    SharedPreferences userLocalDatabase;

    public Userlocalstore(Context context)
    {
        userLocalDatabase = context.getSharedPreferences(SP_Name,0);
    }

    public void userData(User user)
    {
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putString("authkey",user.authkey);
        speditor.putString("usertype",user.usertype);
        speditor.putString("password",user.password);
        speditor.putString("user",user.user);
        speditor.commit();
    }

    public User getloggedInUser(){
        String name = userLocalDatabase.getString("user", "");
        String usertype = userLocalDatabase.getString("usertype","");
        String authkey = userLocalDatabase.getString("authkey","");
        String password = userLocalDatabase.getString("password","");

        User storedUser = new User(name,usertype,authkey,password);
        return storedUser;

    }

    public void setUserloggedIn(boolean loggedIn){
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putBoolean("loggedIn",loggedIn);
        speditor.commit();

    }

    public boolean getuserloggedIn(){

        if(userLocalDatabase.getBoolean("loggedIn",false) == true)
        return true;
        else
        return false;
    }

    public void clearUserdata(){
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.clear();

    }
}


