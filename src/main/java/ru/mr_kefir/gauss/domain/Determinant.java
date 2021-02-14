package ru.mr_kefir.gauss.domain;

public final class Determinant {
    private final Matrix matrix;
    private double value;

    public Determinant(Matrix matrix) {
        if (!matrix.isSquare()) {
            throw new IllegalArgumentException("Determinant can be calculated only for square matrix.");
        }

        this.matrix = matrix;

        if (matrix.getSize() == 1) {
            value = matrix.get(0, 0);
            return;
        }

        if (matrix.getSize() == 2) {
            value = calculateDeterminantFor2Size(matrix);
            return;
        }

        for (int j = 0; j < matrix.getSize(); j++) {
            Matrix minorMatrix = matrix.getMinorMatrix(0, j);
            Determinant minor = new Determinant(minorMatrix);

            value += Math.pow(-1, j) * minor.getValue() * matrix.get(0, j);
        }
    }


    public double getValue() {
        return value;
    }

    public Matrix getMatrix() {
        return matrix;
    }


    private double calculateDeterminantFor2Size(Matrix matrix) {
        return matrix.get(0, 0) * matrix.get(1, 1) - matrix.get(0, 1) * matrix.get(1, 0);
    }
}
