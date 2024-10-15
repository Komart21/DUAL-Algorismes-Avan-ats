#!/bin/bash

# Compilar el proyecto con Maven
echo "Compilando el proyecto con Maven..."
mvn clean compile

echo "¿Qué archivo deseas ejecutar? (0 para exercici0, 1 para exercici1)"
read opcion

if [ "$opcion" == "0" ]; then
    mvn exec:java -Dexec.mainClass="com.project.exercici0"
elif [ "$opcion" == "1" ]; then
    # Ejecutar exercici1
    mvn exec:java -Dexec.mainClass="com.project.exercici1"
else
    echo "Opción no válida. Por favor, elige 0 o 1."
    exit 1
fi
