package drone.command;

import drone.Control;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * The Sequence class is used to record the movement sequences of a drone and 
 * write them into files.
 *
 * @author Gianni Grasso
 * @version 25.11.2021
 */
public class Sequence extends Thread {
    /**
     * Controller Instance.
     */
    Control controller;
    
    /**
     * A queue containing all the rows of a file
     */
    private static volatile LinkedList<String> commandBufferInput = new LinkedList<String>();

    /**
     * The contents of the file.
     */
    static String message;

    public void setMessage(LinkedList<String> sequence) {
        for (int i = 0; i < sequence.size(); i++) {
            message = message + "\n" + sequence.get(i);
        }
    }

    /**
     * Create the file if it does not exist.
     *
     * @param fileName the name of the file
     */
    public void createFile(String fileName) {
        try {
            File myObj = new File(fileName);

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Writes the file if possible.
     *
     * @param fileName the name of the file
     */
    public void writeFile(String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Read the file if possible.
     *
     * @param fileName the name of the file
     */
    public void readFile(String fileName) throws FileNotFoundException, IOException, InterruptedException {
        BufferedReader bufReader = new BufferedReader(new FileReader(fileName));

        String line = bufReader.readLine();
        while (line != null) {
            Thread.sleep(10);
            commandBufferInput.add(line);
            line = bufReader.readLine();
        }

        for (int i = 0; i < commandBufferInput.size(); i++) {

            System.out.println(commandBufferInput.get(i));
        }
        bufReader.close();
    }

    /**
     * Queue setter.
     * 
     * @param commandBufferInput the queue
     */
    public void setSequence(LinkedList<String> commandBufferInput) {
        this.commandBufferInput = commandBufferInput;
    }
}
