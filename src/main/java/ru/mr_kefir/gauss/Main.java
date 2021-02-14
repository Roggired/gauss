package ru.mr_kefir.gauss;

import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            Scene scene = new Scene(System.in, System.out);
            scene.start();
            return;
        }

        if (args.length == 1) {
            InputStream in = new FileInputStream(args[0]);
            PrintStream out = new PrintStream(new FileOutputStream(args[0] + "_output"));

            Scene scene = new Scene(in, out);
            scene.start();
            return;
        }

        System.out.println("Error. Please, pass 1 argument (file name) in case of getting data from a file or no arguments if you wanna enter data by stdin.");
    }
}
