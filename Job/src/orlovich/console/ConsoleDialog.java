package orlovich.console;

import java.util.Scanner;

import static orlovich.utility.Libraries.majorAlgorithm;
import static orlovich.utility.Libraries.scannerDigitToArray;
import static orlovich.utility.Libraries.scannerFileToArray;

public class ConsoleDialog {

    public ConsoleDialog() {
        System.out.println("Введите \"P\" или \"Path\" чтобы вычислить подматрицу из файла" + "\n"
                + "\"D\" или \"Digit\" для ввода матрицы вручную " + "\n" + "для более подробной информации введите help" +
                "\n" + "для выхода введите exit");

        Scanner scanner = new Scanner(System.in);

        int[][] a = null;
        while (true) {
            switch (scanner.nextLine().toUpperCase().trim()) {       //toUpperCase для исключения ошибок регистра при вводе
                case "P":
                case "PATH":
                    System.out.println("enter the path to the matrix file in the form \\path_folder\\file_name.txt");
                    Scanner scanPath = new Scanner(System.in);
                    int[][] scanArray;
                    try {
                        scanArray = scannerFileToArray(scanPath);
                    } catch (IllegalArgumentException i) {
                        System.out.println("Incorrect path the file!!! Retry the operation...\n" +
                                "Enter HELP for a full list commands or EXIT to quit the aplication");
                        continue;
                    }
                    String subMatrixFile = majorAlgorithm(scanArray);
                    System.out.println(subMatrixFile + "\n");
                    break;
                case "D":
                case "DIGIT":
                    System.out.println("enter the numbers of matrix N1 N2 N3 ... Nn ->enter-> M1 M2 M3 ... Mn");
                    Scanner scan = new Scanner(System.in);
                    String subMatrix = majorAlgorithm(scannerDigitToArray(scan));
                    System.out.println(subMatrix + "\n");
                    break;
                case "HELP":
                    System.out.println("USAGE: \n" +
                            "\"D\" or \"DIGIT\"       -                  \n" +
                            "\"P\" or \"PATH\"        -                  \n" +
                            "\"EXIT\"                 - quit the aplication" +
                            "подробно расписать что вводить");
                    break;
                case "":
                    System.out.println("Use command HELP");
                    break;
                case "EXIT":
                    System.exit(0);
                default:
                    System.out.println("command not supported");
            }
        }
    }
}

