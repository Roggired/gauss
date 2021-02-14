package ru.mr_kefir.gauss.domain;

public final class Matrix{
    private static final int MAX_LINE_SIZE = 20;


    private final double[][] array;


    public Matrix(int sizeI, int sizeJ) {
        validateSize(sizeI);
        validateSize(sizeJ);

        this.array = new double[sizeI][sizeJ];
    }


    public void placeValue(int i, int j, double value) {
        array[i][j] = value;
    }

    /**
     * Note! This method must be called only on square matrix.
     */
    public int getSize() {
        return array.length;
    }

    public boolean isSquare() {
        return array.length == array[0].length;
    }

    public int getSizeI() {
        return array.length;
    }

    public int getSizeJ() {
        return array[0].length;
    }

    public double[] getLine(int i) {
        return array[i];
    }

    public double get(int i, int j) {
        return array[i][j];
    }

    public Matrix getCopy() {
        Matrix matrix = new Matrix(getSizeI(), getSizeJ());

        for (int i = 0; i < getSizeI(); i++) {
            for (int j = 0; j < getSizeJ(); j++) {
                matrix.placeValue(i, j, get(i, j));
            }
        }

        return matrix;
    }

    /**
     * Deduct yLineIndex of the matrix multiplied by ratio from xLineIndex.
     */
    public void deductLines(int xLineIndex, int yLineIndex, double ratio) {
        validateRatio(ratio);

        double[] xLine = array[xLineIndex];
        double[] yLine = array[yLineIndex];

        for (int i = 0; i < xLine.length; i++) {
            xLine[i] -= yLine[i] * ratio;
        }
    }

    public void swapLines(int xLineIndex, int yLineIndex) {
        double[] xLine = array[xLineIndex];
        double[] yLine = array[yLineIndex];

        double temp;
        for (int i = 0; i < xLine.length; i++) {
            temp = xLine[i];
            xLine[i] = yLine[i];
            yLine[i] = temp;
        }
    }

    /**
     * Returns a new matrix which is created by deleting lineIndex line and columnIndex column from the matrix.
     */
    public Matrix getMinorMatrix(int lineIndex, int columnIndex) {
        if (array.length == 1 || array[0].length == 1) {
            throw new RuntimeException("The matrix is line or column. Can't get a minor matrix.");
        }

        if (lineIndex < 0 || lineIndex > array.length) {
            throw new IllegalArgumentException("Illegal lineIndex.");
        }

        if (columnIndex < 0 || columnIndex > array[0].length) {
            throw new IllegalArgumentException("Illegal columnIndex.");
        }

        Matrix minorMatrix = deleteLine(lineIndex);
        minorMatrix = minorMatrix.deleteColumn(columnIndex);

        return minorMatrix;
    }


    public Matrix deleteLine(int lineIndex) {
        Matrix matrix = new Matrix(array.length - 1, array[0].length);

        for (int i = 0; i < array.length; i++) {
            if (i < lineIndex) {
                copyLine(matrix, i, i);
            }

            if (i > lineIndex) {
                copyLine(matrix, i, i - 1);
            }
        }

        return matrix;
    }

    private void copyLine(Matrix matrix, int from, int to) {
        for (int j = 0; j < array[0].length; j++) {
            matrix.placeValue(to, j, array[from][j]);
        }
    }

    public Matrix deleteColumn(int columnIndex) {
        Matrix matrix = new Matrix(array.length, array[0].length - 1);

        for (int i = 0; i < array.length; i++) {
            copyLineWithoutElement(matrix, i, columnIndex);
        }

        return matrix;
    }

    private void copyLineWithoutElement(Matrix matrix, int lineIndex, int elementIndex) {
        for (int j = 0; j < array[0].length; j++) {
            if (j < elementIndex) {
                matrix.placeValue(lineIndex, j, array[lineIndex][j]);
            }

            if (j > elementIndex) {
                matrix.placeValue(lineIndex, j - 1, array[lineIndex][j]);
            }
        }
    }

    private void validateSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Matrix sizes should be positive.");
        }

        if (size > MAX_LINE_SIZE) {
            throw new IllegalArgumentException("Matrix sizes should be less than 1000.");
        }
    }

    private void validateRatio(double ratio) {
        if (ratio == 0.0) {
            throw new IllegalArgumentException("Ratio cannot be 0.");
        }
    }
}
