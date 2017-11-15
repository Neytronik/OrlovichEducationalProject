package main.java.orlovich.console;


import main.java.orlovich.utility.MatrixGenerate;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

import static main.java.orlovich.utility.Libraries.*;

/**
 * <p> class providing a console dialog with the user and calling
 * appropriate methods for handling the class {@code Libraries} </p>
 * <p> Instances of the class are created using the {@code getDialog()} method
 * and the instance can start() the dialog </p>
 * <p> The user, in accordance with the instructions, enters commands from the keyboard </p>
 *
 * @author Orlovich Artem
 * @version 1.0
 * @see main.java.orlovich.utility.Libraries
 */
public final class ConsoleDialog {
    private static final Logger log = Logger.getLogger(ConsoleDialog.class);

    /**
     * <p>Metod returned new instance  {@code ConsoleDialog}</p>
     *
     * @return instance ConsoleDialog
     */
    public static ConsoleDialog getDialog() {
        return new ConsoleDialog();
    }

    /**
     * <p>Create a new instance using the method {@code getDialog()}.</p>
     */
    private ConsoleDialog() {
    }

    /**
     * <p>Metod is instanse class ConsoleDialog which starts the dialogue of finding the submatrix.</p>
     */
    public void start() {

        log.info("start session");
        System.out.println("To calculate the submatrix, enter\n" +
                "   \"P MIN\" or \"P MAX\"              -to calculate the submatrix from a file\n" +
                "   \"D MIN\" or \"D MAX\"              -to enter the matrix manually\n" +
                "   \"CRM\" or \"CreateMATRIX\"         -to create a rectangular matrix in file\n" +
                "   \"HELP\"                          -for a full list commands\n" +
                "   \"EXIT\"                          -to quit the aplication");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String scanCommand;
            boolean minimum;

            switch (scanCommand = scanner.nextLine().toUpperCase().trim()) {       //toUpperCase для исключения ошибок регистра при вводе

                case "P MIN":
                case "P MAX":
                    log.info("user input \"P\"");
                    System.out.println("enter the path to the matrix file in the form \\path_folder\\file_name.txt");
                    Scanner scanPath = new Scanner(System.in);
                    int[][] scanArray;
                    try {
                        scanArray = scannerFileToArray(scanPath);
                    } catch (NumberFormatException e) {
                        log.error("Incorrect line matrix");
                        System.out.println("Incorrect line in Matrix.");
                        continue;
                    } catch (IllegalArgumentException i) {
                        log.error("Incorrect path the file");
                        System.out.println("Incorrect path the file!!! Retry the operation...\n" +
                                "Enter HELP for a full list commands or EXIT to quit the aplication");
                        continue;
                    }
                    minimum = scanCommand.equals("P MIN");
                    SubMatrix subMatrixFile = findSubMatrixAlgorithm(scanArray, minimum);
                    outMessage(subMatrixFile);
                    log.info("operation \"P\" successful");
                    break;


                case "D MIN":
                case "D MAX":
                    log.info("user input \"D\"");
                    System.out.println("enter the numbers of matrix N1 N2 N3 ... Nn ->enter-> M1 M2 M3 ... Mn");
                    Scanner scan = new Scanner(System.in);
                    minimum = scanCommand.equals("D MIN");
                    SubMatrix subMatrix = findSubMatrixAlgorithm(scannerDigitToArray(scan), minimum);
                    outMessage(subMatrix);
                    log.info("operation \"D\" successful");
                    break;


                case "CRM":
                case "CREATEMATRIX":
                    log.info("begin create randMatrix");
                    System.out.println("enter the path to create the matrix file in the form \\path_folder");
                    Scanner forCreate = new Scanner(System.in);
                    MatrixGenerate mtrx = new MatrixGenerate(forCreate.nextLine());
                    int colomns = 0;
                    int rows = 0;
                    try {
                        System.out.println("enter quantity colomns ->enter-> ");
                        colomns = forCreate.nextInt();
                        System.out.println("enter quantity rows ->enter->");
                        rows = forCreate.nextInt();
                        if (!(colomns >= 0 && rows >= 0) || (rows == 0 && colomns == 0)) {
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException ime) {
                        log.error("incorrect input size of matrix");
                        System.out.println("incorrect size input!!!");
                        break;
                    }
                    if (colomns >= 0 && rows >= 0 && mtrx.generate(colomns, rows)) {
                        log.info("random matrix is create");
                        System.out.println("Matrix size of " + colomns + " on " + rows + " is created!!!");
                    } else {
                        log.info("writing file failed");
                        System.out.println("writing to file failed!!!");
                    }
                    break;
                case "":
                    System.out.println("Use command HELP");
                    break;

                case "HELP":
                    System.out.println("USAGE: " +
                            "This program only works with the correct rectangular matrix size M on N\n" +
                            "commands ending in MIN compute a minimum subMatrix or ending in MAX compute a maximum\n" +
                            "\nEnter:\n" +
                            "\"P MIN\" or \"P MAX\"         - to calculate the submatrix from a file\n" +
                            "                             enter the full file name in the form :\n" +
                            "                             \\full_path_folder\\file_name.txt\n\n" +

                            "\"D MIN\" or \"D MAX\"         - to enter the matrix manually\n" +
                            "                             enter the numbers of matrix\n" +
                            "                             N1 N2 N3 ... Nn ->enter->\n" +
                            "                             M1 M2 M3 ... Mn\n\n" +

                            "\"CRM\" or \"CreateMATRIX\"    - to create a rectangular matrix\n" +
                            "                             in a text file, then you need to enter\n" +
                            "                             the path of the desired directory\n\n" +

                            "\"EXIT\"                     - quit the aplication");
                    break;
                case "EXIT":
                    log.info("cancel session");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("command not supported");
            }
        }
    }

}

