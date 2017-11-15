package test.java.matrixGenerateTest;

import org.junit.Test;
import main.java.orlovich.utility.MatrixGenerate;

import static org.junit.Assert.*;

public class MatrixGenerateTest {
    MatrixGenerate mg;

    @Test
    public void notDirectoryForGenerate() {
        String isNotDirectory = "this_is_not_directory";
        mg = new MatrixGenerate(isNotDirectory);
        assertFalse(mg.generate(12, 10));
    }

}
