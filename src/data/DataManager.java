package data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Zoo;

/**
 * The {@code DataManager} class provides methods to save and load the state
 * of a {@link Zoo} object to and from a file.
 *
 * <p>
 * This class uses serialization to persist the state of the zoo.
 * The data is stored in a file named "ZooData.dat" in the "src/data" directory.
 *
 */

public class DataManager {

    private static final String DATA_PATH = "src\\data";

    /**
     * Saves the state of the given {@link Zoo} object to a file.
     *
     * @param myZoo The {@link Zoo} object to save.
     * @return {@code true} if the state was successfully saved.
     * @throws FileNotFoundException If the file cannot be created or opened.
     * @throws IOException           If an I/O error occurs during the save
     *                               operation.
     */
    public static boolean saveState(Zoo myZoo) throws FileNotFoundException, IOException {

        String fileName = "ZooData";
        String extension = ".dat";

        File dataBase = new File(DATA_PATH + "\\" + fileName + extension);

        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(dataBase));

        writer.writeObject(myZoo);

        writer.flush();
        writer.close();

        return true;

    }

    /**
     * Loads the state of the {@link Zoo} object from a file.
     *
     * @return The {@link Zoo} object loaded from the file.
     * @throws EOFException           If the end of the file is reached
     *                                unexpectedly.
     * @throws FileNotFoundException  If the file does not exist.
     * @throws IOException            If an I/O error occurs during the load
     *                                operation.
     * @throws ClassNotFoundException If the class of the serialized object cannot
     *                                be found.
     */
    public static Zoo loadState() throws EOFException, FileNotFoundException, IOException, ClassNotFoundException {

        String fileName = "ZooData";
        String extension = ".dat";

        File dataBase = new File(DATA_PATH + "\\" + fileName + extension);

        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(dataBase));

        Zoo myZoo = (Zoo) reader.readObject();
        reader.close();

        return myZoo;

    }

}
