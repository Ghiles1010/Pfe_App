package com.example.pfeapp.prest_ui;

public class Chat_card {

    private String text;
    private String user;

    public Chat_card() {
        this.user = "";
    }


    //////////////////////////////////////////////////////////

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
