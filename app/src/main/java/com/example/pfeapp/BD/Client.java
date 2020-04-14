package com.example.pfeapp.BD;

public class Client extends User {

    String id_client;
    String id_user;


    public Client() {
    }

    public Client(String id_client, String id_user) {
        this.id_client = id_client;
        this.id_user = id_user;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
