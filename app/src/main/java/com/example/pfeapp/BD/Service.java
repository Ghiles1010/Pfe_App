package com.example.pfeapp.BD;

import java.util.ArrayList;

class Service {


    private String IDservice;
    private String IDprestataire;
    private String nom;
    private String description;
    private String categorie;
    private String addresse;
    private String longitude;
    private String latitude;
    private String ville;
    private int dispo;
    private int nb_views;
    private float avg_stars;


    public String getIDservice() {
        return IDservice;
    }

    public void setIDservice(String IDservice) {
        this.IDservice = IDservice;
    }

    public String getIDprestataire() {
        return IDprestataire;
    }

    public void setIDprestataire(String IDprestataire) {
        this.IDprestataire = IDprestataire;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getDispo() {
        return dispo;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    public int getNb_views() {
        return nb_views;
    }

    public void setNb_views(int nb_views) {
        this.nb_views = nb_views;
    }

    public float getAvg_stars() {
        return avg_stars;
    }

    public void setAvg_stars(float avg_stars) {
        this.avg_stars = avg_stars;
    }

    private ArrayList<Client> ListeClients = new ArrayList<Client>();


}
