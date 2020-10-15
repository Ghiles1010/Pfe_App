package com.example.pfeapp.BD;

public class RDV {

    public static final int ACCEPTED=2;
    public static final int NOT_AVAILABLE=-1;
    public static final int REFUSED=-2;
    public static final int ASKED=1;
    public static final int AVAILABLE=0;


    private int nombre_demandes;
    private String id_client;
    private String id_service;
    private String time;
    private String description;
    int accepted;


    public RDV() {
        accepted=AVAILABLE;
    }

    public RDV(String id_client, String id_service, String time, String description, int accepted,int nb_d) {
        this.id_client = id_client;
        this.id_service = id_service;
        this.time = time;
        this.description = description;
        this.accepted=accepted;
        this.nombre_demandes=nb_d;
    }

    public int getNombre_demandes() {
        return nombre_demandes;
    }

    public void setNombre_demandes(int nombre_demandes) {
        this.nombre_demandes = nombre_demandes;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
