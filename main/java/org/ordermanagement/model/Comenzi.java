package org.ordermanagement.model;

import java.time.LocalDate;

public class Comenzi {
    private int id;
    private int idClient;
    private int idProdus;
    private String numeClient;
    private String numeProdus;
    private int cantitate;
    private LocalDate data;

    public Comenzi() {}
    public Comenzi(int id,int idClient,int idProdus,String numeClient,String numeProdus,int cantiate) {
        this.id = id;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.numeClient = numeClient;
        this.numeProdus = numeProdus;
        this.cantitate = cantiate;
        this.data = LocalDate.now();
    }

    // Add getters and setters here
    // ...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
