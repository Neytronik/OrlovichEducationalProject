package test.utilityTest;


import org.junit.Test;

import static org.junit.Assert.*;
import static orlovich.utility.Libraries.*;

public class UtilityTest {


    @Test
    public void setRight_matrix_in_maxSumAlgorithm() {
        int[][] right_matrix =
                new int[][]{
                        {0, -45, 0, 10, 10, 10},
                        {0,  -2, 0, 10, 10, 10},
                        {0,  -3, 0, 10, 10, 10}
                };
        assertEquals("Submatrix having the greatest sum of elements is equal to: " + 90 + "\n"
                + "with coordinates diagonal: [x=2, y=0] and [x=5, y=2]", sumAlgorithm(right_matrix,false));
    }

    @Test
    public void setRight_matrix_in_minSumAlgorithm() {
        int[][] right_matrix =
                new int[][]{
                        {0, -45, 0, 10, 10, 10},
                        {0,  -2, 0, 10, 10, 10},
                        {0,  -3, 0, 10, 10, 10}
                };
        assertEquals("Submatrix having the minimal sum of elements is equal to: " + -50 + "\n"
                + "with coordinates diagonal: [x=0, y=0] and [x=1, y=2]",sumAlgorithm(right_matrix,true));
    }

    @Test
    public void null_referense_in_ValidateArray() {
        assertFalse(validateArray(null));
    }

    @Test
    public void zero_array_int_in_ValidateArray() {
        int[][] zero_array = new int[0][];
        assertFalse(validateArray(zero_array));
    }

    @Test
    public void not_rechtangle_arrays_in_ValidateArray() {
        int[][] not_rechtangle_array =
                {
                        {1, 2, 3, 4},
                        {1, 2, 3},
                        {1, 2, 3, 4, 5}
                };
        assertFalse(validateArray(not_rechtangle_array));
    }


    @Test
    public void array_length_is_one_in_ValidateArray() {
        int[][] one_length = new int[1][];
        assertTrue(validateArray(one_length));
    }

    @Test
    public void right_rechtangle_array_in_ValidateArray() {
        int[][] right_array =
                {
                        {1, 2, 3, 4, 5, 6},
                        {1, 2, 3, 4, 5, 6},
                        {1, 2, 3, 4, 5, 6}
                };
        assertTrue(validateArray(right_array));
    }


}
