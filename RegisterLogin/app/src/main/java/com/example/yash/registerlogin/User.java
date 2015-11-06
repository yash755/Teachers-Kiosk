package com.example.yash.registerlogin;

public class User {
    String authkey,error,success,user,usertype,password;

 public User(String username,String password)
    {

        this.error    = "";
        this.success  = "";
        this.authkey  = "";
        this.usertype = "";
        this.user     = username;
        this.password = password;
    }

    public User(String error)
    {
        this.error    = error;
        this.success  = "";
        this.authkey  = "";
        this.usertype = "";
        this.user     = "";
        this.password = "";

    }

    public User(String name,String usertype,String authkey,String password){

        this.error    = "";
        this.success  = "";
        this.authkey  = authkey;
        this.usertype = usertype;
        this.user     = name;
        this.password = password;
    }

    public User(String username,String password,String authkey,String success,String usertype)
    {

        this.error    = null;
        this.success  = success;
        this.authkey  = authkey;
        this.usertype = usertype;
        this.user     = username;
        this.password = password;
    }



}
