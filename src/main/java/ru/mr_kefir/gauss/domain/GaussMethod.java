package ru.mr_kefir.gauss.domain;

public final class GaussMethod {
    private final Matrix triangleMatrix;
    private final Matrix solution;


    public GaussMethod(Matrix slayMatrix) {
        triangleMatrix = slayMatrix.getCopy();
        straightRun();

        solution = new Matrix(slayMatrix.getSizeI(), 1);
        reverseRun();
    }


    public Matrix getTriangleMatrix() {
        return triangleMatrix;
    }

    public Matrix getSolution() {
        return solution;
    }


    private void straightRun() {
        for (int i = 0; i < triangleMatrix.getSizeI(); i++) {
            double factor = triangleMatrix.get(i, i);

            if (factor == 0) {
                int targetLine = findLineWithFirstNotZeroFactor(i);

                triangleMatrix.swapLines(i, targetLine);

                factor = triangleMatrix.get(i, i);
            }

            for (int k = i + 1; k < triangleMatrix.getSizeI(); k++) {
                double ratio = triangleMatrix.get(k, i) / factor;

                if (ratio == 0) {
                    continue;
                }

                triangleMatrix.deductLines(k, i, ratio);
            }
        }
    }

    private int findLineWithFirstNotZeroFactor(int columnIndex) {
        for (int i = 0; i < triangleMatrix.getSizeI(); i++) {
            if (triangleMatrix.get(i, columnIndex) != 0) {
                return i;
            }
        }

        throw new RuntimeException("No not zero factors in the column " + columnIndex + ". Probably, determinant must be 0.");
    }

    private void reverseRun() {
        for (int i = triangleMatrix.getSizeI() - 1; i >= 0; i--) {
            double freeMember = calculateFreeMember(i);
            solution.placeValue(i, 0, freeMember / triangleMatrix.get(i, i));
        }
    }

    private double calculateFreeMember(int lineIndex) {
        double freeMember = triangleMatrix.get(lineIndex, triangleMatrix.getSizeJ() - 1);

        if (lineIndex < triangleMatrix.getSizeI() - 1) {
            for (int j = triangleMatrix.getSizeJ() - 2; j >= triangleMatrix.getSizeJ() - 2 - (triangleMatrix.getSizeI() - 1 - lineIndex); j--) {
                freeMember -= triangleMatrix.get(lineIndex, j) * solution.get(j, 0);
            }
        }

        return freeMember;
    }
}
