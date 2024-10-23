package com.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DaoLlenguatge {
    private final String path = Main.llenguatgesPath;
    private ArrayList<ObjLlenguatge> llenguatgeList;

    public DaoLlenguatge() {
        llenguatgeList = getAll(); // Cargar los datos desde el archivo JSON
    }

    public void add(ObjLlenguatge llenguatge) {
        llenguatgeList.add(llenguatge);
        save();
    }

    public void update(int id, ObjLlenguatge llenguatge) {
        for (int i = 0; i < llenguatgeList.size(); i++) {
            if (llenguatgeList.get(i).getId() == id) {
                llenguatgeList.set(i, llenguatge);
                save();
                return;
            }
        }
    }

    public void delete(int id) {
        llenguatgeList.removeIf(llenguatge -> llenguatge.getId() == id);
        save();
    }

    public void print() {
        for (ObjLlenguatge llenguatge : llenguatgeList) {
            System.out.println("Llenguatge: " + llenguatge.getId() + " " + llenguatge.getNom() + ", " + llenguatge.getAny() + " - " + llenguatge.getDificultat() + "/" + llenguatge.getPopularitat());
        }
    }
    

    public void setNom(int id, String nom) {
        for (ObjLlenguatge llenguatge : llenguatgeList) {
            if (llenguatge.getId() == id) {
                llenguatge.setNom(nom);
                save();
                return;
            }
        }
    }

    public void setAny(int id, int any) {
        for (ObjLlenguatge llenguatge : llenguatgeList) {
            if (llenguatge.getId() == id) {
                llenguatge.setAny(any);
                save();
                return;
            }
        }
    }

    public void setDificultat(int id, String dificultat) {
        for (ObjLlenguatge llenguatge : llenguatgeList) {
            if (llenguatge.getId() == id) {
                llenguatge.setDificultat(dificultat);
                save();
                return;
            }
        }
    }

    public void setPopularitat(int id, int popularitat) {
        for (ObjLlenguatge llenguatge : llenguatgeList) {
            if (llenguatge.getId() == id) {
                llenguatge.setPopularitat(popularitat);
                save();
                return;
            }
        }
    }

    private ArrayList<ObjLlenguatge> getAll() {
        ArrayList<ObjLlenguatge> llenguatgeList = new ArrayList<>();
        try (Reader reader = new FileReader(path)) {
            Gson gson = new Gson();
            ObjLlenguatge[] llenguatgeArray = gson.fromJson(reader, ObjLlenguatge[].class);
            llenguatgeList = new ArrayList<>(java.util.Arrays.asList(llenguatgeArray));
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró: " + path);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return llenguatgeList;
    }

    private void save() {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new Gson();
            gson.toJson(llenguatgeList, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}


