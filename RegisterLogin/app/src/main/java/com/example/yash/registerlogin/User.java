package com.example.yash.registerlogin;

public class User {
    String authkey,error,success,user,usertype,password,date;

    public User(String username,String password,String usertype,String date)
    {

        this.error    = "";
        this.success  = "";
        this.authkey  = "";
        this.usertype = usertype;
        this.date     = date;
        this.user     = username;
        this.password = password;
    }

    public User(String error)
    {
        this.date     ="";
        this.error    = error;
        this.success  = "";
        this.authkey  = "";
        this.usertype = "";
        this.user     = "";
        this.password = "";

    }



    public User(String username,String password,String authkey,String success,String usertype)
    {

        this.error    = null;
        this.success  = success;
        this.authkey  = authkey;
        this.usertype = usertype;
        this.user     = username;
        this.password = password;
        this.date     = "";
    }



}

