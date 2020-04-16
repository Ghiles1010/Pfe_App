package com.example.pfeapp.user_ui;

public class session_card {


    private int image;
    private String nom_session;
    private String type;
    private String id;




    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom_session() {
        return nom_session;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setNom_session(String nom_session) {
        this.nom_session = nom_session;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
