package com.example.pfeapp.BD;

import java.io.Serializable;

public class User implements Serializable {


    private String email;
    private String psw;
    private String id;
    private String name;
    private String surname;


    public User(String id,String email, String psw, String userName, String name, String surname) {
        this.email = email;
        this.psw = psw;
        this.id = id;
        this.name = name;
        this.surname = surname;
        UserName = userName;
    }


    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    private String UserName;





    /////////////////////////////////////////////////////////////////////////
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
