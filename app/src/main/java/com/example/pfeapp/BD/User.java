package com.example.pfeapp.BD;

import java.io.Serializable;

public class User implements Serializable {


    private String email;
    private String psw;
    private String id;



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
