package drone.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *The Record class is used to record the movement sequences
 *of a drone and write them into files.
 * 
 * @author Gianni Grasso
 * @version 25.11.2021
 */
public class Record extends Thread{
    /**
     * The contents of the file.
     */
    static String message;
    
    public void setMessage(LinkedList<String> sequence){
        for (int i=0; i<sequence.size(); i++){
            message = message + ";" + sequence.get(i);
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
    public void writeFile(String fileName){
        try {  
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }  
    }
}