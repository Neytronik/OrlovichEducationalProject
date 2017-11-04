package orlovich.main;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Введите путь к файлу или прямоугольную матрицу через пробелы!!!");

        Scanner scanner = new Scanner(System.in);

        String pathOrDigits = scanner.nextLine();
        File file = new File(pathOrDigits);

        if (file.isDirectory()) {
            System.out.println("Please enter the full path to the matrix file in the format /path_Folder/file_Matrix.txt");
        } else if (file.isFile()) {
            // parsing file
        }



        System.out.println(file.isDirectory() +"  "+  file.isFile());

        while (scanner.hasNextInt()) {
            int i = scanner.nextInt();
//            String str = scanner.next();

//            System.out.print(str);
            System.out.println(i);
        }



    }
}
