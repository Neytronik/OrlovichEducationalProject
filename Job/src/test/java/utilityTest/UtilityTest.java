package utilityTest;


import org.junit.Test;

import static org.junit.Assert.*;
import static orlovich.utility.Libraries.*;

public class UtilityTest {


    @Test
    public void setRightMatrixInMaxSumAlgorithm() {
        int[][] rightMatrix =
                new int[][]{
                        {0, -45, 0, 10, 10, 10},
                        {0, -2, 0, 10, 10, 10},
                        {0, -3, 0, 10, 10, 10}
                };
        PointSubMatrix start = new PointSubMatrix(2, 0);
        PointSubMatrix end = new PointSubMatrix(5, 2);
        assertEquals(new SubMatrix(90, start, end), findSubMatrixAlgorithm(rightMatrix, false));

    }

    @Test
    public void setRightMatrixInMinSumAlgorithm() {
        int[][] rightMatrix =
                new int[][]{
                        {0, -45, 0, 10, 10, 10},
                        {0, -2, 0, 10, 10, 10},
                        {0, -3, 0, 10, 10, 10}
                };
        PointSubMatrix start = new PointSubMatrix(0, 0);
        PointSubMatrix end = new PointSubMatrix(1, 2);
        assertEquals(new SubMatrix(-50, start, end), findSubMatrixAlgorithm(rightMatrix, true));
    }

    @Test
    public void nullReferenceInValidateArray() {
        assertFalse(validateArray(null));
    }

    @Test
    public void zeroArrayIntInValidateArray() {
        int[][] zeroArray = new int[0][];
        assertFalse(validateArray(zeroArray));
    }

    @Test
    public void notRectangleArraysInValidateArray() {
        int[][] notRectangleArray =
                {
                        {1, 2, 3, 4},
                        {1, 2, 3},
                        {1, 2, 3, 4, 5}
                };
        assertFalse(validateArray(notRectangleArray));
    }


    @Test
    public void arrayLengthIsOneInValidateArray() {
        int[][] oneLength = new int[1][];
        assertTrue(validateArray(oneLength));
    }

    @Test
    public void rightRectangleArrayInValidateArray() {
        int[][] rightArray =
                {
                        {1, 2, 3, 4, 5, 6},
                        {1, 2, 3, 4, 5, 6},
                        {1, 2, 3, 4, 5, 6}
                };
        assertTrue(validateArray(rightArray));
    }


}
