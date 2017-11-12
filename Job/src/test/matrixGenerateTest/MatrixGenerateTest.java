package test.matrixGenerateTest;

import org.junit.Test;
import orlovich.utility.MatrixGenerate;

import static org.junit.Assert.*;

public class MatrixGenerateTest {
    MatrixGenerate mg ;
    @Test
    public void not_directory_for_generate() {
        String not_dyrectory = "this_is_not_directory";
        mg = new MatrixGenerate(not_dyrectory);
        assertFalse(mg.generate(12, 10));
    }

}
