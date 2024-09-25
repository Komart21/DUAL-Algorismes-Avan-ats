package com.project;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Electrodomestic> electrodomestics = new ArrayList<>();

        // Afegir Rentadores
        electrodomestics.add(new Rentadora("Rentadora A", "Blanc", 500, "Marca1", "A++", 1200, 60));
        electrodomestics.add(new Rentadora("Rentadora B", "Negre", 600, "Marca2", "A+", 1400, 70));

        // Afegir Neveres
        electrodomestics.add(new Nevera("Nevera A", "Blanc", 800, "Marca3", "A++", 300, 50));
        electrodomestics.add(new Nevera("Nevera B", "Negre", 900, "Marca4", "A+", 400, 55));

        // Afegir Forns
        electrodomestics.add(new Forn("Forn A", "Blanc", 700, "Marca5", "A++", 200, true));
        electrodomestics.add(new Forn("Forn B", "Negre", 800, "Marca6", "A+", 250, false));

        // Clonar lista
        List<Electrodomestic> clones = new ArrayList<>();
        for (Electrodomestic e : electrodomestics) {
            try {
                clones.add((Electrodomestic) e.clone());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        }

        // Comparar lista original con original
        System.out.println("\nComparant llista original amb ella mateixa:");
        for (int i = 0; i < electrodomestics.size(); i++) {
            Electrodomestic original = electrodomestics.get(i);
            System.out.println(original.toString());
            System.out.println("Igual? " + (original == original) + ", Dades iguals? " + original.equals(original));
        }

        // Comparar lista original con clon
        System.out.println("\nComparant llista original amb els clons:");
        for (int i = 0; i < electrodomestics.size(); i++) {
            Electrodomestic original = electrodomestics.get(i);
            Electrodomestic clone = clones.get(i);
            System.out.println(original.toString());
            System.out.println("Igual? " + (original == clone) + ", Dades iguals? " + original.equals(clone));
        }
    }
}
