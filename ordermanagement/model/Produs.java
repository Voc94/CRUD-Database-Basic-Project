package org.ordermanagement.model;

import java.text.DecimalFormat;

public class Produs {
    private int id;
    private String nume;
    private float pret;
    private int stoc;
    public Produs(){

    }
    public Produs(int id, String nume, float pret, int stoc) {
        this.id = id;
        this.nume = nume;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String truncatedValue = decimalFormat.format(pret);
        this.pret = Float.parseFloat(truncatedValue);
        this.stoc = stoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }
    @Override
    public String toString() {
        return nume; // return the name of the client
    }
}
