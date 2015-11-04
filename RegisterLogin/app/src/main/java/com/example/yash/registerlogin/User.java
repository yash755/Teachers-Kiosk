package com.example.yash.registerlogin;

public class User {
    String username,name,password;


    public User(String username,String name,String password)
    {
        this.username = username;
        this.name     = name;
        this.password = password;
    }

    public User(String username,String password)
    {

        this.name     = "";
        this.username = username;
        this.password = password;
    }

}
