package com.project;

import java.util.concurrent.TimeUnit;

public class exercici0 {
    public static void main(String[] args) {
        try {
            Objecte objecte = Objecte.getInstance(); 
            
            // Definim arrays
            String[] noms = {"Joan", "Marta", "Àlex"};
            String[] cognoms = {"Pérez", "García", "Morales"};
            int[] edats = {25, 30, 35};
            
            // Creem instancies
            for (int i = 0; i < 3; i++) {
                iniciarContador(i); 
                objecte.setNom(noms[i]);
                objecte.setCognom(cognoms[i]);
                objecte.setEdat(edats[i]);

                TimeUnit.SECONDS.sleep(3);
            }

            // IMprimim dades de l'objecte
            for (int i = 0; i < 3; i++) {
                System.out.println(objecte);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void iniciarContador(int numero) throws InterruptedException {
        System.out.println("Iniciant: " + numero);
    }
}
