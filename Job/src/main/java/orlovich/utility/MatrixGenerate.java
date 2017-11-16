package orlovich.utility;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


/**
 * This class is designed to create a
 * text file with a rectangular matrix
 *
 * @author Orlovich Artem
 * @version 1.0
 */
public class MatrixGenerate {
    private static final Logger log = Logger.getLogger(MatrixGenerate.class);
    /**
     * {@code path} is directory for write file of Matrix
     */
    private String path;
    /**
     * In the generation, the Random object is used
     * with the initial grain of 47
     */
    private static final Random rand = new Random(47);

    /**
     * In constructor sets the directory of the created file
     *
     * @param path is directory
     */
    public MatrixGenerate(String path) {
        this.path = path;
    }

    /**
     * <p>The method generates a txt file with the specified number of rows (vertical)
     * and columns (horizontal) of integer values
     * ​​and returns true if the file is created and false
     * if an input error occurred during the creation</p>
     *
     * @param columns is length of line matrix
     * @param rows    is length of column matrix
     * @return true if file is created
     */
    public boolean generate(int columns, int rows) {
        if (new File(path).isDirectory()) {
            File file = new File(path + "\\randMatrix.txt");
            try (FileWriter writer = new FileWriter(file)) {
                StringBuilder sB = new StringBuilder();
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        sB.append(rand.nextInt(127) + " ");
                    }
                    sB.append(System.lineSeparator());
                }
                writer.write(sB.toString());
                writer.flush();
            } catch (IOException ex) {
                log.info("Error while writing a random matrix file",ex);
                return false;
            }
            return true;
        }
        return false;
    }
}
