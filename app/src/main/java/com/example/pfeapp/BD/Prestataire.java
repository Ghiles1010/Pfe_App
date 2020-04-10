package com.example.pfeapp.BD;

public class Prestataire extends Utilisateur {



    private String id_prestataire;
    private String id_user;


    public Prestataire() {
    }

    public Prestataire(String id_prestataire, String id_user) {
        this.id_prestataire = id_prestataire;
        this.id_user = id_user;
    }

    public String getId_prestataire() {
        return id_prestataire;
    }

    public void setId_prestataire(String id_prestataire) {
        this.id_prestataire = id_prestataire;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
