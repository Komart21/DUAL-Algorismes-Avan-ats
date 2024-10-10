package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Entregues {
    private ArrayList<Producte> productes;
    private PropertyChangeSupport support;

    public Entregues() {
        productes = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    public ArrayList<Producte> getProductes() {
        return productes;
    }

    public void addProducte(Producte producte) {
        productes.add(producte);
        System.out.println("🚚 [Entregues]: S'ha afegit el producte amb id " + producte.getId() + " a la llista d'entregues.");
        support.firePropertyChange("entreguesAdd", null, producte);
    }

    public void removeProducte(int id) {
        Producte producte = findProducteById(id);
        if (producte != null) {
            productes.remove(producte);
            System.out.println("✅ [Entregues]: S'ha entregat el producte amb id " + producte.getId() + ".");
            support.firePropertyChange("entreguesRemove", producte, null);
        }
    }

    private Producte findProducteById(int id) {
        for (Producte producte : productes) {
            if (producte.getId() == id) {
                return producte;
            }
        }
        return null;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public String toString() {
        return "🚚 [Entregues] Productes: " + productes;
    }
}
