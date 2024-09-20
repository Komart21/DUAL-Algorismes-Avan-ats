package com.project;

public class Objecte {
    private static Objecte instance;
    private String nom;
    private String cognom;
    private int edat;

    // Constructor privat
    private Objecte() {
        this.nom = "";
        this.cognom = "";
        this.edat = 0;
    }

    public static synchronized Objecte getInstance() {
        if (instance == null) {
            instance = new Objecte();
        }
        return instance;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Cognom: " + cognom + ", Edat: " + edat;
    }
}
