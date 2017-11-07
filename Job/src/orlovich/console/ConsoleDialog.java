package orlovich.console;

import java.util.Scanner;

import static orlovich.utility.Libraries.*;

/**
 * <p> class providing a console dialog with the user and calling
 * the appropriate methods for processing the class {@code Libraries} </p>
 * <p> The class is a singleton and has a single method that
 * returns an instance of the class while in the constructor of the class
 * a dialog is being built. </p>
 * <p>The user entering commands from the keyboard comes to the desired
 * solution of the problem. </p>
 *
 * @author Orlovich Artem
 * @version 1.0
 * @see orlovich.utility.Libraries
 */
public final class ConsoleDialog {

    /**
     * Field the only instance of the console dialog
     */
    private final static ConsoleDialog cd = new ConsoleDialog();

    /**
     * Metod returned singleton {@code ConsoleDialog}
     *
     * @return instanse ConsoleDialog
     */
    public static ConsoleDialog getDialog() {
        return cd;
    }

    /**
     * A private constructor, along with a static instance,
     * makes this design single-use, it is possible
     * to use it again after restarting the program
     */
    private ConsoleDialog() {

        System.out.println("To calculate the submatrix, enter\n" +
                "   \"P\" or \"Path\"       to calculate the submatrix from a file\n" +
                "   \"D\" or \"Digit\"      to enter the matrix manually\n" +
                "   \"HELP\"              for a full list commands\n" +
                "   \"EXIT\"              to quit the aplication");

        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("USAGE: " +
                            "This program only works with the correct rectangular matrix size M on N\n\nEnter:\n" +
                            "\"P\" or \"PATH\"       - to calculate the submatrix from a file\n" +
                            "                          enter the full file name in the form :\n" +
                            "                         \\full_path_folder\\file_name.txt\n\n" +

                            "\"D\" or \"DIGIT\"       - to enter the matrix manually\n" +
                            "                          enter the numbers of matrix\n" +
                            "                          N1 N2 N3 ... Nn ->enter->\n" +
                            "                          M1 M2 M3 ... Mn\n\n" +

                            "\"EXIT\"               - quit the aplication");
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

