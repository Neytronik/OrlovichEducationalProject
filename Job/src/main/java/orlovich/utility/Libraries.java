package orlovich.utility;


import org.apache.log4j.Logger;

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
 * algorithm {@code findSubMatrixAlgorithm} or {@code minSumAlgorithm}  and return coordinate diagonal SubMatrix  </p>
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
     * <p>Logger</p>
     */
    private static final Logger log = Logger.getLogger(Libraries.class);

    /**
     * Don't let anyone instantiate this class.
     */
    private Libraries() {
    }

    /**
     * <p>Nested class {@code PointSumMatrix} expresses
     * the abstraction of the two-dimensional
     * representation of the subMatrix in the
     * plane with its coordinates</p>
     */
    public static final class PointSubMatrix {
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PointSubMatrix that = (PointSubMatrix) o;

            if (x != that.x) return false;
            return y == that.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        public PointSubMatrix(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public PointSubMatrix(PointSubMatrix p) {
            this.x = p.x;
            this.y = p.y;
        }

        public String toString() {
            return "[x=" + x + ", y=" + y + ']';
        }

    }

    /**
     * Nested class contains information about the sum and coordinates the subMatrix
     */
    public static final class SubMatrix {
        private int sumMatrixElement;

        PointSubMatrix initial;
        PointSubMatrix end;

        public SubMatrix(int sumMatrixElement, PointSubMatrix initial, PointSubMatrix end) {
            this.sumMatrixElement = sumMatrixElement;
            this.initial = initial;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SubMatrix subMatrix = (SubMatrix) o;

            if (sumMatrixElement != subMatrix.sumMatrixElement) return false;
            if (!initial.equals(subMatrix.initial)) return false;
            return end.equals(subMatrix.end);
        }

        @Override
        public int hashCode() {
            int result = sumMatrixElement;
            result = 31 * result + initial.hashCode();
            result = 31 * result + end.hashCode();
            return result;
        }

        public int getSumMatrixElement() {
            return sumMatrixElement;
        }

        public PointSubMatrix getinicialPoint() {
            return new PointSubMatrix(initial);
        }

        public PointSubMatrix getEndPoint() {
            return new PointSubMatrix(end);
        }

        @Override
        public String toString() {
            return "subMatrix{" +
                    "sumElement = " + sumMatrixElement +
                    ", with coordinates diagonal: begin " + initial +
                    ", end=" + end +
                    '}';
        }
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
    public static boolean validateArray(int[][] array) {
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
     * <p> The run algorithm for finding the maximum sum of the submatrix
     * takes a two-dimensional matrix as an array of arrays of integers
     * <p> The validity of the incoming array is verified by the method {@link #validateArray(int[]...)}</p>
     * <p> If the array is valid, then after evaluation the method returns
     * a instance subMatrix containing the maximum sum and the coordinates of the submatrix</p>
     *
     * @param array   is two-dimension arrays of integers (forExample int[][])
     * @param minimum is a boolean with two possible parameters "true" for finding the minimal sum
     *                and "false" for finding the maximum sum
     * @return SubMatrix
     */
    public static SubMatrix findSubMatrixAlgorithm(int[][] array, boolean minimum) {

        if (!validateArray(array)) {
            log.info("non-validate array");
            return null;
        }

        int minOrMaxSum = array[0][0];
        final int M = array.length;
        final int N = array[0].length;
        PointSubMatrix inicialPoint = new PointSubMatrix(0, 0);
        PointSubMatrix endPoint = new PointSubMatrix(0, 0);
        SubMatrix subMatrix = new SubMatrix(minOrMaxSum, inicialPoint, endPoint);


        for (int startLine = 0; startLine < M; startLine++) {
            for (int startColumn = 0; startColumn < N; startColumn++) {
                int[] cache = new int[N];
                for (int line = startLine; line < M; line++) {
                    int currentSum = 0;
                    int lineSum = 0;
                    for (int column = startColumn; column < N; column++) {
                        lineSum += array[line][column];
                        currentSum = lineSum + cache[column];
                        cache[column] = currentSum;

                        if (!minimum && (currentSum > minOrMaxSum) && ((startLine == line && startColumn == column) ||
                                !((startLine == 0 && line == M - 1) && (startColumn == 0 && column == N - 1)))) {

                            minOrMaxSum = currentSum;                                       //maximal sum
                            inicialPoint = new PointSubMatrix(startColumn, startLine);
                            endPoint = new PointSubMatrix(column, line);
                            subMatrix = new SubMatrix(minOrMaxSum, inicialPoint, endPoint);

                        } else if (minimum && (currentSum < minOrMaxSum) && ((startLine == line && startColumn == column) ||
                                !((startLine == 0 && line == M - 1) && (startColumn == 0 && column == N - 1)))) {

                            minOrMaxSum = currentSum;                                       //minimal sum
                            inicialPoint = new PointSubMatrix(startColumn, startLine);
                            endPoint = new PointSubMatrix(column, line);
                            subMatrix = new SubMatrix(minOrMaxSum, inicialPoint, endPoint);
                        }
                    }
                }
            }
        }

        return subMatrix;
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
            if ((line = scan.nextLine().trim()).equals("")) {
                break;
            }
            int[] numArr;
            try {
                numArr = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                arrays.add(numArr);
            } catch (NumberFormatException e) {
                log.info("user incorrect manually input number format", e);
                System.out.println("NumberFormatExeption in line: " + line + "\n"
                        + "re-enter line");
                if (readFile) throw new NumberFormatException();
            }
        }
    }

    /**
     * <p>The method is used when reading the matrix from a text file.
     * the input parameter Scanner with which it is read</p>
     * <p>If the path to the file is incorrectly thrown, throws an IllegalArgumentException. </p>
     * <p>Can throw an NumberFormatException in the case of non-integer matrix values ​​in
     * the file when the readLineMatrix method is called
     * </p>
     *
     * @param scan is {@code java.util.Scanner;}
     * @return int[][] is listToArray(List<int[]> list)
     * @throws IllegalArgumentException if file is not exist
     * @throws NumberFormatException    if in file is
     *                                  non-integer
     */
    public static int[][] scannerFileToArray(Scanner scan) {

        String path = scan.nextLine();
        List<int[]> arrays = new ArrayList<>();
        File file = new File(path);
        if (file.isFile()) {

            try {
                scan = new Scanner(file);
            } catch (FileNotFoundException e) {
                log.error("file matrix not found");
                System.out.println("File not found!!!");
            }
            log.info("reading file is begin");
            readLineMatrix(scan, arrays, true);  //throws NumberFormatExeption
            log.info("reading is complete");
            scan.close();

        } else {
            log.error("scanning is not file");
            throw new IllegalArgumentException();
        }

        return listToArray(arrays);
    }

    /**
     * <p>The method is used when reading the input matrix,
     * the input parameter Scanner with which to read. </p>
     * <p>Can throw an exception in the case of non-integer matrix values ​​and empty lines when calling the readLineMatrix</p>
     *
     * @param scan is {@code java.util.Scanner;}
     * @return int[][] is listToArray(List<int[]> list)
     * @throws NumberFormatException if enter non-integer
     */
    public static int[][] scannerDigitToArray(Scanner scan) {
        List<int[]> arrays = new ArrayList<>();
        readLineMatrix(scan, arrays, false);
        return listToArray(arrays);
    }

    /**
     * <p>Private method for correctly connecting a collection
     * of integer arrays to the run working algorithm</p>
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

    /**
     * <p>Displays information about subMatrix</p>
     *
     * @param subMatrix
     */
    public static void outMessage(SubMatrix subMatrix) {
        if (subMatrix == null) {
            log.error("input incorrect matrix");
            System.out.println("This matrix isn't corectly! \nEnter the correct matrix size of M on N\n");
        } else
            System.out.println(subMatrix + "\n");
    }
}



