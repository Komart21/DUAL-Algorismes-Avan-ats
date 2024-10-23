package com.project;

import java.util.ArrayList;

public class ObjSoftware {
    private int id;
    private String nom;
    private int any;
    private ArrayList<Integer> llenguatges;

    public ObjSoftware(int id, String nom, int any, ArrayList<Integer> llenguatges) {
        this.id = id;
        this.nom = nom;
        this.any = any;
        this.llenguatges = llenguatges;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public int getAny() { return any; }
    public void setAny(int any) { this.any = any; }
    public ArrayList<Integer> getLlenguatges() { return llenguatges; }
    public void setLlenguatges(ArrayList<Integer> llenguatges) { this.llenguatges = llenguatges; }
}

