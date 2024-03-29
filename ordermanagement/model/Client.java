package org.ordermanagement.model;

public class Client {
    private int id;
    private String nume;
    private String prenume;
    private String adresa;
    private int varsta;
    private String email;

    public Client() {
    }

    public Client(int id, String nume, String prenume, String adresa, int varsta, String email) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.varsta = varsta;
        this.email = email;
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

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nume; // return the name of the client
    }
}
