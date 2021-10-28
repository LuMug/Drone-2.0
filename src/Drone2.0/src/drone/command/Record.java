package drone.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

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

    Queue<String> sequence;

    /**
     * Variabile per scrivere sul file.
     */
    public static FileWriter fw;

    /**
     * Variabile per creare il file.
     */
    public static File file;

    /**
     * Method that deals with recording the sequence of commands.
     *
     * @param sequence
     */
    public Record(Queue<String> sequence) {
        this.sequence = sequence;
    }

    /**
     * Method that deals with writing the sequence.
     *
     * @param nomeFile
     */
    public void sequenceWriter(String nomeFile) {
        creationFile(nomeFile);
        try {
            for (int i = 0; i < sequence.size(); i++) {
                if (sequence.size() > 0) {
                    String command = sequence.remove() + "\r\n";
                    fw.write(command);
                }
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error file già esistente");
        }
    }

    public void creationFile(String nome) {
        try {
            String path = ROOT + "/" + nome + ".sequence";
            file = new File(path);
            file.createNewFile();
            fw = new FileWriter(file);
        } catch (IOException ex) {
        }
    }
}
