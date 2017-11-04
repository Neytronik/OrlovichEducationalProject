package orlovich.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите \"P\" или \"Path\" чтобы вычислить подматрицу из файла" + "\n"
                + "\"D\" или \"Digit\" для ввода матрицы вручную "+ "\n" + "для более подробной информации введите help"+
                "\n" + "для выхода введите exit");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (scanner.next().toUpperCase()) {       //toUpperCase для исключения ошибок регистра при вводе
                case "P":
                case "PATH":
                    System.out.println("Тут нужны проверки и дальнейший парсинг и вывод в массив");
                    // вызвать метод что то типо scannerFileToArray(Scanner scan)
                    break;
                case "D":
                case "DIGIT":
                    System.out.println("тут надо бы заносить каждую строку в новый массив");
                    // вызвать метод что то типо scannerDigitToArray(Scanner scan)
                    break;
                case "HELP":
                    System.out.println("подробно расписать что вводить");
                    break;
                case "EXIT":
                    System.exit(0);
            }
        }







//        String pathOrDigits = scanner.nextLine();
//        File file = new File(pathOrDigits);

//        if (file.isDirectory()) {
//            System.out.println("Please enter the full path to the matrix file in the format /path_Folder/file_Matrix.txt");
//        } else if (file.isFile()) {
//            // parsing file
//        }
//
//
//        System.out.println(file.isDirectory() + "  " + file.isFile());

//        while (scanner.hasNextInt()) {
//            int i = scanner.nextInt();
////            String str = scanner.next();
//
////            System.out.print(str);
//            System.out.println(i);
//        }


    }
}
