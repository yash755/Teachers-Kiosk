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
        speditor.putString("name",user.name);
        speditor.putString("password",user.password);
        speditor.putString("username",user.username);
        speditor.commit();
    }

    public User getloggedInUser(){
        String name = userLocalDatabase.getString("name", "");
        String username = userLocalDatabase.getString("username","");
        String password = userLocalDatabase.getString("password","");

        User storedUser = new User(username,name,password);
        return storedUser;

    }

    public void setUserloggedIn(boolean loggedIn){
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.putBoolean("loggedIn",loggedIn);
        speditor.commit();

    }

    public void clearUserdata(){
        SharedPreferences.Editor speditor = userLocalDatabase.edit();
        speditor.clear();

    }
}


