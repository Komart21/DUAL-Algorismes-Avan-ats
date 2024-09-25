package com.project;

import java.util.Objects;

public class Rentadora extends Electrodomestic {
    private int revolucions;
    private int soroll;

    public Rentadora(String nom, String color, double preu, String marca, String eficiencia, int revolucions, int soroll) {
        super(nom, color, preu, marca, eficiencia);
        this.revolucions = revolucions;
        this.soroll = soroll;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Rentadora rentadora = (Rentadora) o;
        return revolucions == rentadora.revolucions && soroll == rentadora.soroll;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), revolucions, soroll);
    }

    public String toString() {
        return "Rentadora{" + super.toString() +
                ", revolucions=" + revolucions +
                ", soroll=" + soroll +
                '}';
    }
}
