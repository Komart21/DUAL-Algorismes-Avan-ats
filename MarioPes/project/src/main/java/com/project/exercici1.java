package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class exercici1 {
    public static void main(String[] args) {
        try {
            List<Objecte> instancies = new ArrayList<>();

            String[] noms = {"Joan", "Marta", "Àlex"};
            String[] cognoms = {"Pérez", "García", "Morales"};
            int[] edats = {25, 30, 35};

            // Solo hay una instancia del Singleton
            Objecte objecte = Objecte.getInstance();

            for (int i = 0; i < 3; i++) {
                iniciarContador(i);

                objecte.setNom(noms[i]);
                objecte.setCognom(cognoms[i]);
                objecte.setEdat(edats[i]);

                TimeUnit.SECONDS.sleep(3);

                instancies.add(objecte);
            }

            for (Objecte obj : instancies) {
                System.out.println(obj);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void iniciarContador(int numero) throws InterruptedException {
        System.out.println("Iniciant: " + numero);
    }
}
