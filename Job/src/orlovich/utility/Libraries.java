package orlovich.utility;

//дописать доки в двух методах сканнеров и применить минимальную сумму

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * <p> The {@code Libraries} class contains a chain
 * of dependent methods to find the maximum or minimum sum of submatrix
 * elements in the matrix.
 * <p>Reading methods allow you to perform calculations
 * both from a file and after manually entering an array of numbers
 * The result of the class is the use of the search
 * algorithm {@code maxSumAlgorithm} or {@code minSumAlgorithm}  and return coordinate diagonal SubMatrix  </p>
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
     * The field {@code inicialPoint} is first point of diadonal subMatrix
     */
    private static PointSubMatrix inicialPoint;
    /**
     * The field {@code endPoint} is second point of diadonal subMatrix
     */
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

    /**
     * <p> The main algorithm for finding the maximum sum of the submatrix
     * takes a two-dimensional matrix as an array of arrays of integers
     * <p> The validity of the incoming array is verified by the method {@link #validateArray(int[]...)}</p>
     * <p> If the array is valid, then after evaluation the method returns
     * a string containing the maximum sum and the coordinates of the submatrix</p>
     *
     * @param a is varargs arrays of integers (forExample int[][])
     * @return String : maxSum and coordinates of subMatrix
     */
    public static String maxSumAlgorithm(int[]... a) {
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
     * <p> The main algorithm for finding the minimum sum of the submatrix
     * takes a two-dimensional matrix as an array of arrays of integers
     * <p> The validity of the incoming array is verified by the method{@link #validateArray(int[]...)}</p>
     * <p> If the array is valid, then after evaluation the method returns
     * a string containing the minimum sum and the coordinates of the submatrix</p>
     *
     * @param a is varargs arrays of integers (forExample int[][])
     * @return String : minSum and coordinates of subMatrix
     */
    public static String minSumAlgorithm(int[]... a) {
        /**
         * the field retains the largest sum of submatrix elements
         */
        int minSum = 0;
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
                        if (currentSum < minSum && ((startLine == line && startColumn == column) ||
                                !((startLine == 0 && line == M - 1) && (startColumn == 0 && column == N - 1)))) {
                            minSum = currentSum;
                            inicialPoint = new PointSubMatrix(startColumn, startLine);
                            endPoint = new PointSubMatrix(column, line);
                        }
                    }
                }
            }
        }

        return String.valueOf("Submatrix having the minimal sum of elements is equal to: " + minSum + "\n"
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

        public String toString() {
            return "[x=" + x + ", y=" + y + "]";
        }
    }

    /**
     * <p>This method reads and parses the string to integers and
     * writes this string to the collection of integer arrays passed in the parameters.</p>
     * <p>If the reading occurs from the file, if there are illegal characters
     * or an empty string, the Exception NumberFormatException is thrown</p>
     *
     * @param scan   is {@code java.util.Scanner}
     * @param arrays is {@code List<int[]>}
     * @throws NumberFormatException if read line are illegal characters
     *                               or an empty string
     */
    private static void readLineMatrix(Scanner scan, List<int[]> arrays, boolean readFile) {
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
                if (readFile) throw new NumberFormatException();
            }
        }
    }


    /**
     * @param scan is {@code java.util.Scanner;}
     * @return int[][] is listToArray(List<int[]> list)
     * @throws IllegalArgumentException if file is not exist
     */
    public static int[][] scannerFileToArray(Scanner scan) {

        String path = scan.nextLine();
        List<int[]> arrays = new ArrayList<>();
        File file = new File(path);
        if (file.isFile()) {
            synchronized (file) {
                try {
                    scan = new Scanner(file);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found!!!");
                }
                readLineMatrix(scan, arrays, true);  //throw NumberFormatExeption
                scan.close();
            }
        } else throw new IllegalArgumentException();
        return listToArray(arrays);
    }

    /**
     * @param scan is {@code java.util.Scanner;}
     * @return int[][] is listToArray(List<int[]> list)
     */
    public static int[][] scannerDigitToArray(Scanner scan) {
        List<int[]> arrays = new ArrayList<>();
        readLineMatrix(scan, arrays, false);
        scan.close();
        return listToArray(arrays);
    }


    /**
     * private metod for correctly connecting a collection
     * of integer arrays to the main working algorithm
     *
     * @param list<int[]> is array line of integers
     * @return int[][] array of arrays integers (two-dimensional array)
     */
    private static int[][] listToArray(List<int[]> list) {
        int[][] arr = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}



