package ru.mr_kefir.gauss;

import ru.mr_kefir.gauss.domain.Determinant;
import ru.mr_kefir.gauss.domain.DiscrepancyCalculator;
import ru.mr_kefir.gauss.domain.GaussMethod;
import ru.mr_kefir.gauss.domain.Matrix;
import ru.mr_kefir.gauss.util.MatrixPrinter;
import ru.mr_kefir.gauss.util.MatrixReader;

import java.io.*;

public final class Scene {
    private final InputStream in;
    private final PrintStream out;


    public Scene(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }


    public void start() {
        printGreetings();

        Matrix slayMatrix = MatrixReader.read(in);
        printSlayMatrix(slayMatrix);

        Matrix coefficientsMatrix = slayMatrix.deleteColumn(slayMatrix.getSizeJ() - 1);
        printCoefficientsMatrix(coefficientsMatrix);

        Determinant determinant = new Determinant(coefficientsMatrix);
        printDeterminant(determinant);

        if (determinant.getValue() == 0) {
            printZeroDeterminantError();
            return;
        }

        GaussMethod gaussMethod = new GaussMethod(slayMatrix);
        printTriangleMatrix(gaussMethod.getTriangleMatrix());
        printSolutionMatrix(gaussMethod.getSolution());

        Matrix discrepancy = DiscrepancyCalculator.getDiscrepancy(slayMatrix, gaussMethod.getSolution());
        printDiscrepancyMatrix(discrepancy);
    }

    private void printGreetings() {
        out.println("This program solves SLAY with Gauss method.");
        out.println();
    }

    private void printSlayMatrix(Matrix slayMatrix) {
        out.println("SLAY matrix:");
        MatrixPrinter.printMatrix(slayMatrix, out);
        out.println();
    }

    private void printCoefficientsMatrix(Matrix coefficientsMatrix) {
        out.println("Coefficients matrix:");
        MatrixPrinter.printMatrix(coefficientsMatrix, out);
        out.println();
    }

    private void printDeterminant(Determinant determinant) {
        out.println("Coefficients matrix determinant:");
        MatrixPrinter.printDeterminant(determinant, out);
        out.println();
    }

    private void printTriangleMatrix(Matrix triangleMatrix) {
        out.println("Triangle matrix:");
        MatrixPrinter.printMatrix(triangleMatrix, out);
        out.println();
    }

    private void printSolutionMatrix(Matrix solutionMatrix) {
        out.println("Solution matrix:");
        MatrixPrinter.printMatrix(solutionMatrix, out);
        out.println();
    }

    private void printDiscrepancyMatrix(Matrix discrepancyMatrix) {
        out.println("Discrepancy matrix:");
        MatrixPrinter.printMatrix(discrepancyMatrix, out);
        out.println();
    }

    private void printZeroDeterminantError() {
        out.println("Gauss method can't be applied to SLAY which determinant of the coefficients matrix is zero.");
    }
}
