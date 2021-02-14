package ru.mr_kefir.gauss.util;

import ru.mr_kefir.gauss.domain.Matrix;

import java.io.InputStream;
import java.util.Scanner;

public final class MatrixReader {
    public static Matrix read(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream)) {
            int sizeI = readSize(scanner);
            int sizeJ = readSize(scanner);

            Matrix matrix = new Matrix(sizeI, sizeJ);
            readMatrix(matrix, scanner);

            return matrix;
        }
    }


    private static int readSize(Scanner scanner) {
        return scanner.nextInt();
    }

    private static void readMatrix(Matrix matrix, Scanner scanner) {
        for (int i = 0; i < matrix.getSizeI(); i++) {
            for (int j = 0; j < matrix.getSizeJ(); j++) {
                matrix.placeValue(i, j, scanner.nextDouble());
            }
        }
    }
}
