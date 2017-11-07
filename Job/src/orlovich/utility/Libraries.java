package orlovich.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * <p> The {@code Libraries} class contains a chain
 * of dependent methods to find the maximum sum of submatrix
 * elements in the matrix.
 * <p>Reading methods allow you to perform calculations
 * both from a file and after manually entering an array of numbers
 * The result of the class is the use of the search
 * algorithm {@code majorAlgorithm} and return coordinate diagonal SubMatrix  </p>
 * <p>
 * <p>subMatrixCoordinate Example</p>
 * <p> ___________________________________</p>
 * <p>|__|__|xy||||||||||||||||__|__|__|__| inicialPoint</p>
 * <p>|__|__||||__|__|__|__||||__|__|__|__| coordinate diagonal SubMatrix</p>
 * <p>|__|__||||||||||||||||XY|__|__|__|__| endPoint</p>
 *
 * @author Orlovich Artem
 * @version 1.0
 */
public final class Libraries {

    /**
     *
     *
     *
     *
     *
     *
     */
    private static PointSubMatrix inicialPoint;
    private static PointSubMatrix endPoint;

    /**
     * Don't let anyone instantiate this class.
     */
    private Libraries() {
    }

    /**
     * <p> Accepts a two-dimensional matrix array </p>
     * <p> Returns "true" if the matrix is ​​suitable for calculating the submatrix. </p>
     * Cases:
     * <ul> <li> If the null reference
     * result is false.
     * <li> If the passed array has a length of zero
     * result is false.
     * <li> If the transferred array has different lengths of subarrays
     * result is false.
     * </ul>
     *
     * @param array is two-dimensional array of integer values ​​(int [] []).
     * @return boolean.
     */
    public static boolean validateArray(int[]... array) {
        if (array == null || array.length == 0) return false;
        if (array.length == 1) return true;
        else {
            int lengthFirstArray = array[0].length;
            for (int i = 1; i < array.length; i++) {
                if (array[i].length != lengthFirstArray)
                    return false;
            }
        }
        return true;
    }

    public static String majorAlgorithm(int[]... a) {
        /**
         * the field retains the largest sum of submatrix elements
         */
        int maxSum = 0;
        if (!validateArray(a)) return "This matrix isn't corectly! \nEnter the correct matrix size of M on N";

        /**
         * field M is vertical length of matrix
         */
        final int M = a.length;
        /**
         * field N is horisontal length of matrix
         */
        final int N = a[0].length;

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
                        if (currentSum > maxSum && ((startLine == line && startColumn == column) ||
                                !((startLine == 0 && line == M - 1) && (startColumn == 0 && column == N - 1)))) {
                            maxSum = currentSum;
                            inicialPoint = new PointSubMatrix(startColumn, startLine);
                            endPoint = new PointSubMatrix(column, line);
                        }
                    }
                }
            }
        }

        return String.valueOf("Submatrix having the greatest sum of elements is equal to: " + maxSum + "\n"
                + "with coordinates diagonal: " + inicialPoint + " and " + endPoint);
    }

    /**
     * <p>Nested class {@code PointSumMatrix} expresses
     * the abstraction of the two-dimensional
     * representation of the submatrix in the
     * plane with its coordinates</p>
     */
    public static final class PointSubMatrix {
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

    public static void readLineMatrix(Scanner scan, ArrayList<int[]> arrays) {
        String line;
        while (scan.hasNextLine()) {
            if ((line = scan.nextLine()).equals("")) {
                break;
            }
            int[] numArr;
            try {
                numArr = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                arrays.add(numArr);
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatExeption in line: " + line + "\n"
                        + "re-enter line");
            }
        }
    }

    public static int[][] scannerFileToArray(Scanner scan) {

        String path = scan.nextLine();
        File file = new File(path);
        ArrayList<int[]> arrays = new ArrayList<>();
        if (file.isFile()) {
            try {
                scan = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!!!");
            }
            readLineMatrix(scan, arrays);
        } else throw new IllegalArgumentException();
        return listToArray(arrays);
    }

    public static int[][] scannerDigitToArray(Scanner scan) {
        ArrayList<int[]> arrays = new ArrayList<>();
        readLineMatrix(scan, arrays);
        return listToArray(arrays);
    }


    private static int[][] listToArray(ArrayList<int[]> list) {
        int[][] arr = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}



