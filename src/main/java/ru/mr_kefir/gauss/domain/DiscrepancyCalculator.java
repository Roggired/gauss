package ru.mr_kefir.gauss.domain;

public final class DiscrepancyCalculator {
    public static Matrix getDiscrepancy(Matrix slayMatrix, Matrix solution) {
        Matrix matrix = new Matrix(solution.getSizeI(), 1);

        for (int i = 0; i < solution.getSizeI(); i++) {
            matrix.placeValue(i, 0, calculateDiscrepancy(i, slayMatrix, solution));
        }

        return matrix;
    }

    private static double calculateDiscrepancy(int lineIndex, Matrix slayMatrix, Matrix solution) {
        double result = slayMatrix.get(lineIndex, slayMatrix.getSizeJ() - 1);

        for (int j = 0; j < solution.getSizeI(); j++) {
            result -= slayMatrix.get(lineIndex, j) * solution.get(j, 0);
        }

        return result;
    }
}
