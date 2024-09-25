#!/bin/bash

# Compilar el código fuente
javac -d out src/main/java/com/project/*.java

# Ejecutar la clase Main
java -cp out com.project.Main
