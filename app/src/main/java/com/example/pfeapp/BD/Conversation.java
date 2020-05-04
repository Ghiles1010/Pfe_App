package com.example.pfeapp.BD;

public class Conversation {


    private String id_client;
    private String id_service;
    private String time;
    private String last_message;
    private String nom_client;
    private String nom_service;
    private int messages_loaded;
    private int conversation_loaded;



    public Conversation() {
        this.messages_loaded=0;
        this.conversation_loaded=1;
    }


    public Conversation(String id_client, String id_service, String time, String last_message,String nom_client,String nom_service,int messages_loaded,int conversation_loaded) {
        this.id_client = id_client;
        this.id_service = id_service;
        this.time = time;
        this.last_message = last_message;
        this.nom_client=nom_client;
        this.nom_service=nom_service;
        this.messages_loaded=messages_loaded;
        this.conversation_loaded=conversation_loaded;
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

    public int getMessages_loaded() {
        return messages_loaded;
    }

    public void setMessages_loaded(int messages_loaded) {
        this.messages_loaded = messages_loaded;
    }

    public int getConversation_loaded() {
        return conversation_loaded;
    }

    public void setConversation_loaded(int conversation_loaded) {
        this.conversation_loaded = conversation_loaded;
    }
}
