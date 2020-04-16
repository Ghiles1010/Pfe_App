package com.example.pfeapp.BD;

public class Conversation {


    private String id_client;
    private String id_service;
    private String time;
    private String last_message;
    private String nom_client;
    private String nom_service;

    public Conversation() {
    }


    public Conversation(String id_client, String id_service, String time, String last_message,String nom_client,String nom_service) {
        this.id_client = id_client;
        this.id_service = id_service;
        this.time = time;
        this.last_message = last_message;
        this.nom_client=nom_client;
        this.nom_service=nom_service;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getNom_service() {
        return nom_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getId_service() {
        return id_service;
    }

    public void setId_service(String id_service) {
        this.id_service = id_service;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
