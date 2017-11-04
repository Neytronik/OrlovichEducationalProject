package orlovich.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Libraries {

    private static PointSubMatrix inicialPoint;
    private static PointSubMatrix endPoint;

    private Libraries() {
    }
//                subMatrixCoordinate Example
//          ___________________________________
//         |__|__|xy||||||||||||||||__|__|__|__| inicialPoint
//         |__|__||||__|__|__|__||||__|__|__|__| coordinate diagonal SubMatrix
//         |__|__||||||||||||||||XY|__|__|__|__| endPoint


    //проверка алгоритма
    public static void main(String[] args) {
        int[] a = {1, 2, 3, -4};
        int[][] b = {{1, 2, 3, -4}, {0, -7, 11, -9}, {0, 0, -29, 0}, {0, -8, 0}};
        int[][] c = {{1, 2, 3, -4}, {0, -7, 11, -9}, {0, 0, -29, 0}, {0, -8, 0, 9}};
        System.out.println(majorAlgorithm(a));
        System.out.println(majorAlgorithm(b));
        System.out.println(majorAlgorithm(c));
    }

    private static boolean validateArray(int[]... array) {
        if (array == null && array.length == 0) return false;       //throw new NullPointerException();
        if (array.length == 1) return true;
        int lengthFirstArray = array[0].length;
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i].length == array[i + 1].length) continue; // проверяется прямоугольность матрицы
            return false;
        }

        return true;
    }

    public static String majorAlgorithm(int[]... a) {
        int maxSum = 0;
        if (!validateArray(a)) return "This matrix isn't corectly";
        final int M = a.length;   //vertical
        final int N = a[0].length;  //horisontal size matrix

        for (int startLine = 0; startLine < M; startLine++) {
            for (int startColumn = 0; startColumn < N; startColumn++) {
                int[] cache = new int[N];
                for (int line = startLine; line < M; line++) {
                    int currentSum = 0;
                    int lineSum = 0;
                    for (int column = startColumn; column < N; column++) {
                        lineSum += a[line][column];
                        currentSum = lineSum + cache[column];
                        cache[column] = currentSum;
                        if (currentSum > maxSum) {
                            maxSum = currentSum;
                            inicialPoint = new PointSubMatrix(startColumn, startLine);
                            endPoint = new PointSubMatrix(column, line);
                        }
                    }
                }
            }
        }

        return String.valueOf("Submatrix having the greatest sum of elements is equal to: " + maxSum + "\n"
                + "with coordinates diagonal: " + inicialPoint + "and" + endPoint);
    }


    private static class PointSubMatrix {
        int x;
        int y;

        public PointSubMatrix(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[x=" + x + ", y=" + y + "]";
        }
    }

    public static void scannerFileToArray(Scanner scan) {

        ArrayList<int[]> arrays = new ArrayList<>();

        String path = scan.nextLine();
        File file = new File(path);

        if (file.isDirectory()) {
            System.out.println("Please enter the full path to the matrix file in the format /path_Folder/file_Matrix.txt");
        } else if (file.isFile()) {
            // parsing file
        } else System.out.println("Please enter the full path to the matrix file");

    }
}



