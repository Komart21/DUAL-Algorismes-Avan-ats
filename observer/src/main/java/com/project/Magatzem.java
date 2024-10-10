package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Magatzem {
    private ArrayList<Producte> productes;
    private int capacitat;
    private PropertyChangeSupport support;

    public Magatzem() {
        productes = new ArrayList<>();
        capacitat = 10;
        support = new PropertyChangeSupport(this);
    }

    public ArrayList<Producte> getProductes() {
        return productes;
    }

    public void addProducte(Producte producte) {
        productes.add(producte);
        capacitat--;
        System.out.println("🔵 [Magatzem]: S'ha afegit el producte amb id " + producte.getId() + " al magatzem. Capacitat restant: " + capacitat);
        support.firePropertyChange("magatzemAdd", null, producte);
    }

    public void removeProducte(int id) {
        Producte producte = findProducteById(id);
        if (producte != null) {
            productes.remove(producte);
            capacitat++;
            System.out.println("🔴 [Magatzem]: S'ha esborrat el producte amb id " + producte.getId() + " del magatzem. Capacitat restant: " + capacitat);
            support.firePropertyChange("magatzemRemove", producte, null);
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

    @Override
    public String toString() {
        return "📦 [Magatzem] Productes: " + productes + ". Capacitat restant: " + capacitat;
    }
}
