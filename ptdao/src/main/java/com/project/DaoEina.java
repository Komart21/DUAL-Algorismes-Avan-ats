package com.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DaoEina {
    private final String path = Main.einesPath;
    private ArrayList<ObjEina> einaList;

    public DaoEina() {
        einaList = getAll(); // Cargar los datos desde el archivo JSON
    }

    public void add(ObjEina eina) {
        einaList.add(eina);
        save();
    }

    public void update(int id, ObjEina eina) {
        for (int i = 0; i < einaList.size(); i++) {
            if (einaList.get(i).getId() == id) {
                einaList.set(i, eina);
                save();
                return;
            }
        }
    }

    public void delete(int id) {
        einaList.removeIf(eina -> eina.getId() == id);
        save();
    }

    public void print() {
        for (ObjEina eina : einaList) {
            System.out.println("Eina: " + eina.getId() + " " + eina.getNom() + ", " + eina.getAny() + " - " + eina.getLlenguatges());
        }
    }
    

    public void setNom(int id, String nom) {
        for (ObjEina eina : einaList) {
            if (eina.getId() == id) {
                eina.setNom(nom);
                save();
                return;
            }
        }
    }

    public void setAny(int id, int any) {
        for (ObjEina eina : einaList) {
            if (eina.getId() == id) {
                eina.setAny(any);
                save();
                return;
            }
        }
    }

    public void setLlenguatgesAdd(int id, int idLlenguatge) {
        for (ObjEina eina : einaList) {
            if (eina.getId() == id) {
                eina.getLlenguatges().add(idLlenguatge);
                save();
                return;
            }
        }
    }

    public void setLlenguatgesDelete(int id, int idLlenguatge) {
        for (ObjEina eina : einaList) {
            if (eina.getId() == id) {
                eina.getLlenguatges().remove((Integer) idLlenguatge);
                save();
                return;
            }
        }
    }

    private ArrayList<ObjEina> getAll() {
        ArrayList<ObjEina> einaList = new ArrayList<>();
        try (Reader reader = new FileReader(path)) {
            Gson gson = new Gson();
            ObjEina[] einaArray = gson.fromJson(reader, ObjEina[].class);
            einaList = new ArrayList<>(java.util.Arrays.asList(einaArray));
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró: " + path);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return einaList;
    }

    private void save() {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new Gson();
            gson.toJson(einaList, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}


