package com.project;

import java.lang.reflect.Constructor;
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

            // Creem instancies
            for (int i = 0; i < 3; i++) {
                iniciarContador(i);

                // Utilizem la instancia reflexion
                Objecte objecte = getNewDestroyedInstance();

                objecte.setNom(noms[i]);
                objecte.setCognom(cognoms[i]);
                objecte.setEdat(edats[i]);

                // Agreguem la llista
                instancies.add(objecte);

                TimeUnit.SECONDS.sleep(3);
            }

            // Mostrem dades
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

    // Instacia hackejada amb singleton
    private static Objecte getNewDestroyedInstance() throws Exception {
        // Obtenim el contructor privat
        Constructor<Objecte> constructor = Objecte.class.getDeclaredConstructor();
        constructor.setAccessible(true); 
        return constructor.newInstance(); 
    }
}
