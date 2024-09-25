package com.project;

import java.util.Objects;

public class Nevera extends Electrodomestic {
    private int frigories;
    private int soroll;

    public Nevera(String nom, String color, double preu, String marca, String eficiencia, int frigories, int soroll) {
        super(nom, color, preu, marca, eficiencia);
        this.frigories = frigories;
        this.soroll = soroll;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Nevera nevera = (Nevera) o;
        return frigories == nevera.frigories && soroll == nevera.soroll;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), frigories, soroll);
    }

    public String toString() {
        return "Nevera{" + super.toString() +
                ", frigories=" + frigories +
                ", soroll=" + soroll +
                '}';
    }
}
