package com.example.pfeapp.BD;

public class Category {

    private int ID;

    private String categorie;
    private boolean isFinal;

    public Category(int ID, String categorie) {
        this.ID = ID;
        this.categorie = categorie;
    }


    public Category() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
