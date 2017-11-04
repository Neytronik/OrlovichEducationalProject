package orlovich.main;

import java.util.Scanner;

import static orlovich.utility.Libraries.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите \"P\" или \"Path\" чтобы вычислить подматрицу из файла" + "\n"
                + "\"D\" или \"Digit\" для ввода матрицы вручную " + "\n" + "для более подробной информации введите help" +
                "\n" + "для выхода введите exit");

        Scanner scanner = new Scanner(System.in);

        int[][] a = null;
        while (true) {
            switch (scanner.next().toUpperCase()) {       //toUpperCase для исключения ошибок регистра при вводе

                case "P":
                case "PATH":
                    System.out.println("enter the path to the matrix file in the form \\path_folder\\file_name.txt");
                    Scanner scanPath = new Scanner(System.in);
                    String subMatrixFile = majorAlgorithm(scannerFileToArray(scanPath));
                    System.out.println(subMatrixFile + "\n");
                    break;
                case "D":
                case "DIGIT":
                    System.out.println("enter the number of matrix N1 N2 N3 ... Nn");
                    Scanner scan = new Scanner(System.in);
                    String subMatrix = majorAlgorithm(scannerDigitToArray(scan));
                    System.out.println(subMatrix + "\n");
                    break;
                case "HELP":
                    System.out.println("подробно расписать что вводить");
                    break;
                case "EXIT":
                    System.exit(0);
                default:
                    System.out.println("command not supported");
            }
        }
    }
}
