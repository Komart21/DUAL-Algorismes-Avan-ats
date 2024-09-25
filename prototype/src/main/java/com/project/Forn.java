package com.project;

import java.util.Objects;

public class Forn extends Electrodomestic {
    private int temperatura;
    private boolean autoneteja;

    public Forn(String nom, String color, double preu, String marca, String eficiencia, int temperatura, boolean autoneteja) {
        super(nom, color, preu, marca, eficiencia);
        this.temperatura = temperatura;
        this.autoneteja = autoneteja;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Forn forn = (Forn) o;
        return temperatura == forn.temperatura && autoneteja == forn.autoneteja;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), temperatura, autoneteja);
    }

    public String toString() {
        return "Forn{" + super.toString() +
                ", temperatura=" + temperatura +
                ", autoneteja=" + autoneteja +
                '}';
    }
}
