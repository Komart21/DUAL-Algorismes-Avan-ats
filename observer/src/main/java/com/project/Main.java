package com.project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main {
    public static void main(String[] args) {

        // Crear 5 productes
        Producte p0 = new Producte(0, "Llibre");
        Producte p1 = new Producte(1, "Boli");
        Producte p2 = new Producte(2, "Rotulador");
        Producte p3 = new Producte(3, "Carpeta");
        Producte p4 = new Producte(4, "Motxilla");

        Magatzem magatzem = new Magatzem();
        Entregues entregues = new Entregues();

        // PropertyChangeListener para escuchar los cambios
        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if (propertyName.equals("producteId")) {
                    System.out.println("🆔 [Producte]: Ha canviat l'id de '" + evt.getOldValue() + "' a '" + evt.getNewValue() + "'");
                } else if (propertyName.equals("producteName")) {
                    System.out.println("✏️ [Producte]: Ha canviat el nom de '" + evt.getOldValue() + "' a '" + evt.getNewValue() + "'");
                }
            }
        };

        // Añadir listener a los productos
        p0.addPropertyChangeListener(listener);
        p1.addPropertyChangeListener(listener);
        p2.addPropertyChangeListener(listener);
        p3.addPropertyChangeListener(listener);
        p4.addPropertyChangeListener(listener);

        // Cambios en los productos
        p0.setId(5); // Cambio de id
        p0.setNom("Llibreta"); 
        p1.setNom("Bolígraf"); 

        // Añadir productos al magatzem
        magatzem.addProducte(p0);
        magatzem.addProducte(p1);
        magatzem.addProducte(p2);
        magatzem.addProducte(p3);
        magatzem.addProducte(p4);

        // Eliminar productos del magatzem
        magatzem.removeProducte(2); 
        magatzem.removeProducte(3); 
        magatzem.removeProducte(4); 

        // Mover productos a entregues
        entregues.addProducte(p2);
        entregues.addProducte(p3);

        // Eliminar productos de entregues (entregar)
        entregues.removeProducte(2);
        entregues.removeProducte(3);

        // Mostrar contenido del magatzem y entregues
        System.out.println("\n" + magatzem);
        System.out.println(entregues);
    }
}
