package drone.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Class that records the command sequences in a file. 
 *
 * @author Alessandro Aloise
 * @version 25 marzo 2021
 */
public class Record {
   /**
     * Constant for the file path.
     */
    public static final String ROOT = "SequencesRecorded";

    /**
     * Variable for the Path file.
     */
    private Path file;

    /**
     * Method that deals with recording the sequence of commands.
     *
     * @param file File name.
     */
    public Record(String file) {
        this.file = Paths.get(ROOT + "/" + file + ".txt");
        try {
            Files.write(this.file, "".getBytes());
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    }

    /**
     * Method that deals with writing the sequence.
     *
     * @param sequence sequence to be written to the file.
     */
    public void sequenceWriter(String sequence) {
        try {
            Files.write(file, ((sequence + "\r\n")).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    } 
}
