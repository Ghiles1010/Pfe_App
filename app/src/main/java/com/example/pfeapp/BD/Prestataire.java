package com.example.pfeapp.BD;

import java.util.ArrayList;

public class Prestataire extends Utilisateur {



    private  int IDprestataire;
    private ArrayList<Service> services=new ArrayList<Service>();





    public int getIDprestataire() {
        return IDprestataire;
    }

    public void setIDprestataire(int IDprestataire) {
        this.IDprestataire = IDprestataire;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

















}
