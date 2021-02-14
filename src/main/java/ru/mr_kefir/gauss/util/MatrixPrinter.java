package ru.mr_kefir.gauss.util;

import ru.mr_kefir.gauss.domain.Determinant;
import ru.mr_kefir.gauss.domain.Matrix;

import java.io.PrintStream;

public final class MatrixPrinter {
    private static final int DIGITS_AFTER_DOR = 3;


    public static void printMatrix(Matrix matrix, PrintStream out) {
        for (int i = 0; i < matrix.getSizeI(); i++) {
            out.print("( ");
            printLine(matrix.getLine(i), out);
            out.println(" )");
        }
    }

    public static void printDeterminant(Determinant determinant, PrintStream out) {
        for (int i = 0; i < determinant.getMatrix().getSize(); i++) {
            out.print("| ");
            printLine(determinant.getMatrix().getLine(i), out);
            out.print(" |");

            if (i == determinant.getMatrix().getSize() / 2) {
                out.printf(" = %10." + DIGITS_AFTER_DOR + "f", determinant.getValue());
            }

            out.println();
        }
    }

    private static void printLine(double[] line, PrintStream out) {
        for (double value : line) {
            out.printf("%10." + DIGITS_AFTER_DOR + "f", value);
        }
    }
}
