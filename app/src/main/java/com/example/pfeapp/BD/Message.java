package com.example.pfeapp.BD;

public class Message {


    private String id_client;
    private String id_service;
    private String time;
    private String text;
    private String id_sender;


    public Message() {
    }

    public Message(String id_client, String id_service, String time, String text, String id_sender) {
        this.id_client = id_client;
        this.id_service = id_service;
        this.time = time;
        this.text = text;
        this.id_sender = id_sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getId_sender() {
        return id_sender;
    }

    public void setId_sender(String id_sender) {
        this.id_sender = id_sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
