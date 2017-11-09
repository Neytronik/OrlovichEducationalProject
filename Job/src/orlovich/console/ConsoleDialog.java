package orlovich.console;

//пересмотреть метод старт()
import java.util.Scanner;

import static orlovich.utility.Libraries.*;

/**
 * <p> class providing a console dialog with the user and calling
 * appropriate methods for handling the class {@code Libraries} </p>
 * <p> Instances of the class are created using the {@code getDialog()} method
 * and the instance can start() the dialog </p>
 * <p> The user, in accordance with the instructions, enters commands from the keyboard </p>
 *
 * @author Orlovich Artem
 * @version 1.0
 * @see orlovich.utility.Libraries
 */
public final class ConsoleDialog {

    /**
     * <p>Metod returned new instanse  {@code ConsoleDialog}</p>
     *
     * @return instanse ConsoleDialog
     */
    public static ConsoleDialog getDialog() {
        return new ConsoleDialog();
    }

    /**
     * Create a new instance using the method {@code getDialog()}
     */
    private ConsoleDialog() {
    }

    /**
     * Metod is instanse class ConsoleDialog which starts the dialogue of finding the submatrix
     */
    public void start() {

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
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect line in Matrix. ");
                        continue;

                    } catch (IllegalArgumentException i) {
                        System.out.println("Incorrect path the file!!! Retry the operation...\n" +
                                "Enter HELP for a full list commands or EXIT to quit the aplication");
                        continue;
                    }

                    String subMatrixFile = maxSumAlgorithm(scanArray);
                    System.out.println(subMatrixFile + "\n");
                    break;
                case "D":
                case "DIGIT":
                    System.out.println("enter the numbers of matrix N1 N2 N3 ... Nn ->enter-> M1 M2 M3 ... Mn");
                    Scanner scan = new Scanner(System.in);
                    String subMatrix = maxSumAlgorithm(scannerDigitToArray(scan));
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
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("command not supported");
            }
        }
    }

}

