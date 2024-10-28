package com.project;

import java.util.ArrayList;
import java.util.List;

public class Main{

    private double[] weights;
    private double bias;
    private double learningRate = 0.1;

    public Main(int inputSize) {
        weights = new double[inputSize];
        bias = 0.0;
        // Inicialización de los pesos aleatoriamente
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random() - 0.5;
        }
    }

    private int activate(double sum) {
        return sum >= 0 ? 1 : 0;
    }

    private int predict(int[] inputs) {
        double sum = bias;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * inputs[i];
        }
        return activate(sum);
    }

    public void train(int[][] inputData, int[] labels, int epochs) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < inputData.length; i++) {
                int prediction = predict(inputData[i]);
                int error = labels[i] - prediction;

                // Ajustar pesos y bias
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningRate * error * inputData[i][j];
                }
                bias += learningRate * error;
            }
        }
    }

    public double testAccuracy(int[][] inputData, int[] labels) {
        int correct = 0;
        for (int i = 0; i < inputData.length; i++) {
            int prediction = predict(inputData[i]);
            boolean isCorrect = (prediction == labels[i]);
            if (isCorrect) {
                correct++;
            }
        }
        return (correct / (double) inputData.length) * 100.0;
    }

    public static List<int[]> generateAllMatrices() {
        List<int[]> matrices = new ArrayList<>();

        for (int i = 0; i < 512; i++) {
            int[] matrix = new int[9];
            String binary = String.format("%9s", Integer.toBinaryString(i)).replace(' ', '0');

            for (int j = 0; j < 9; j++) {
                matrix[j] = binary.charAt(j) - '0';
            }

            matrices.add(matrix);
        }

        return matrices;
    }

    public static void main(String[] args) {
        List<int[]> matrices = generateAllMatrices();

        // Definir perceptrones
        Main perceptron0 = new Main(9); // Diagonal
        Main perceptron1 = new Main(9); // Vertical
        Main perceptron2 = new Main(9); // Horizontal

        // Definir las etiquetas para cada perceptrón
        int[] diagonalLabels = new int[512];
        int[] verticalLabels = new int[512];
        int[] horizontalLabels = new int[512];

        // Etiquetar las matrices
        for (int i = 0; i < matrices.size(); i++) {
            int[] matrix = matrices.get(i);

            // Diagonales
            int diagPrincipal = (matrix[0] == 1 ? 1 : 0) + (matrix[4] == 1 ? 1 : 0) + (matrix[8] == 1 ? 1 : 0);
            int diagSecundaria = (matrix[2] == 1 ? 1 : 0) + (matrix[4] == 1 ? 1 : 0) + (matrix[6] == 1 ? 1 : 0);
            
            diagonalLabels[i] = 0;
            if (diagPrincipal == 3 || diagSecundaria == 3) {
                diagonalLabels[i] = 1;
            }

            // Verticales
            int columna1 = (matrix[0] == 1 ? 1 : 0) + (matrix[3] == 1 ? 1 : 0) + (matrix[6] == 1 ? 1 : 0);
            int columna2 = (matrix[1] == 1 ? 1 : 0) + (matrix[4] == 1 ? 1 : 0) + (matrix[7] == 1 ? 1 : 0);
            int columna3 = (matrix[2] == 1 ? 1 : 0) + (matrix[5] == 1 ? 1 : 0) + (matrix[8] == 1 ? 1 : 0);

            verticalLabels[i] = 0;
            if (columna1 == 3 || columna2 == 3 || columna3 == 3) {
                verticalLabels[i] = 1;
            }

            // Horizontales
            int fila1 = (matrix[0] == 1 ? 1 : 0) + (matrix[1] == 1 ? 1 : 0) + (matrix[2] == 1 ? 1 : 0);
            int fila2 = (matrix[3] == 1 ? 1 : 0) + (matrix[4] == 1 ? 1 : 0) + (matrix[5] == 1 ? 1 : 0);
            int fila3 = (matrix[6] == 1 ? 1 : 0) + (matrix[7] == 1 ? 1 : 0) + (matrix[8] == 1 ? 1 : 0);

            horizontalLabels[i] = 0;
            if (fila1 == 3 || fila2 == 3 || fila3 == 3) {
                horizontalLabels[i] = 1;
            }
        }

        // Entrenar y probar con diferentes EPOCH
        int[][] inputData = matrices.toArray(new int[0][0]);
        for (int epochs : new int[]{1, 2, 5, 50, 300}) {
            System.out.println("EPOCH: " + epochs);
            
            perceptron0.train(inputData, diagonalLabels, epochs);
            double accuracy0 = perceptron0.testAccuracy(inputData, diagonalLabels);
            System.out.println("Perceptron 0 (Diagonal) Accuracy: " + accuracy0 + "%");

            perceptron1.train(inputData, verticalLabels, epochs);
            double accuracy1 = perceptron1.testAccuracy(inputData, verticalLabels);
            System.out.println("Perceptron 1 (Vertical) Accuracy: " + accuracy1 + "%");

            perceptron2.train(inputData, horizontalLabels, epochs);
            double accuracy2 = perceptron2.testAccuracy(inputData, horizontalLabels);
            System.out.println("Perceptron 2 (Horizontal) Accuracy: " + accuracy2 + "%");
        }
    }
}
