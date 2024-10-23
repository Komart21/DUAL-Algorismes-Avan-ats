package com.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DaoSoftware {
    private final String path = Main.softwarePath;
    private ArrayList<ObjSoftware> softwareList;

    public DaoSoftware() {
        softwareList = getAll(); 
    }

    public void add(ObjSoftware software) {
        softwareList.add(software);
        save();
    }

    public void update(int id, ObjSoftware software) {
        for (int i = 0; i < softwareList.size(); i++) {
            if (softwareList.get(i).getId() == id) {
                softwareList.set(i, software);
                save();
                return;
            }
        }
    }

    public void delete(int id) {
        softwareList.removeIf(software -> software.getId() == id);
        save();
    }

    public void print() {
        for (ObjSoftware software : softwareList) {
            System.out.println("Software: " + software.getId() + " " + software.getNom() + ", " + software.getAny() + " - " + software.getLlenguatges());
        }
    }
    

    public void setNom(int id, String nom) {
        for (ObjSoftware software : softwareList) {
            if (software.getId() == id) {
                software.setNom(nom);
                save();
                return;
            }
        }
    }

    public void setAny(int id, int any) {
        for (ObjSoftware software : softwareList) {
            if (software.getId() == id) {
                software.setAny(any);
                save();
                return;
            }
        }
    }

    public void setLlenguatgesAdd(int id, int idLlenguatge) {
        for (ObjSoftware software : softwareList) {
            if (software.getId() == id) {
                software.getLlenguatges().add(idLlenguatge);
                save();
                return;
            }
        }
    }

    public void setLlenguatgesDelete(int id, int idLlenguatge) {
        for (ObjSoftware software : softwareList) {
            if (software.getId() == id) {
                software.getLlenguatges().remove((Integer) idLlenguatge);
                save();
                return;
            }
        }
    }

    private ArrayList<ObjSoftware> getAll() {
        ArrayList<ObjSoftware> softwareList = new ArrayList<>();
        try (Reader reader = new FileReader(path)) {
            Gson gson = new Gson();
            ObjSoftware[] softwareArray = gson.fromJson(reader, ObjSoftware[].class);
            softwareList = new ArrayList<>(java.util.Arrays.asList(softwareArray));
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró: " + path);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return softwareList;
    }

    private void save() {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new Gson();
            gson.toJson(softwareList, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
