package drone.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

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
    public static final String ROOT = "SequenceDrone";

    /**
     * Method that deals with recording the sequence of commands.
     *
     */
    public Record() {
    }

    /**
     * Method that deals with writing the sequence.
     *
     * @param sequence sequence to be written to the file.
     */
    public void sequenceWriter(List sequence, Path name) {
        for (int i = 0; i < sequence.size(); i++) {
            try {
                Files.write(name, ((sequence.get(i) + "\r\n")).getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error:" + e);
            }

        }
    }
}
