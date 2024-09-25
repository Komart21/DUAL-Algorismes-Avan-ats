package com.project;

import java.util.Objects;

public abstract class Electrodomestic implements Cloneable {
    private String nom;
    private String color;
    private double preu;
    private String marca;
    private String eficiencia;

    public Electrodomestic(String nom, String color, double preu, String marca, String eficiencia) {
        this.nom = nom;
        this.color = color;
        this.preu = preu;
        this.marca = marca;
        this.eficiencia = eficiencia;
    }

    // Getters y Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public double getPreu() { return preu; }
    public void setPreu(double preu) { this.preu = preu; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getEficiencia() { return eficiencia; }
    public void setEficiencia(String eficiencia) { this.eficiencia = eficiencia; }

    // Clonamos
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Sobrescribimos los equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Electrodomestic that = (Electrodomestic) o;
        return Double.compare(that.preu, preu) == 0 &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(color, that.color) &&
                Objects.equals(marca, that.marca) &&
                Objects.equals(eficiencia, that.eficiencia);
    }

    // Sobreecribimos el hasscode
    public int hashCode() {
        return Objects.hash(nom, color, preu, marca, eficiencia);
    }

    public String toString() {
        return "Electrodomestic{" +
                "nom='" + nom + '\'' +
                ", color='" + color + '\'' +
                ", preu=" + preu +
                ", marca='" + marca + '\'' +
                ", eficiencia='" + eficiencia + '\'' +
                '}';
    }
}
