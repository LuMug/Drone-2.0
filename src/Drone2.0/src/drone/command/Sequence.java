package drone.command;

import drone.Drone;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class helpful in repeating the recorded sequences.
 * 
 * @author Alessandro Aloise
 * @version 25 febbraio 2021
 */
public class Sequence extends Thread {
    
    /**
     * Constant for the file path.
     */
    protected static final String ROOT = "SequenceDrone";
    
    
     /**
     * Constant for the file path.
     */
    protected static final int TIME_WAITING = 125;
    
    /**
     * Variable for the Path file.
     */
    protected Path file;
    
    
    /**
     * Drone Class Instance.
     */
    protected Drone drone;

    public Sequence(String file) {
        this.file = Paths.get(ROOT + "/" + file + ".sequence");
    }
    
    
    /**
     * Method that deals with repeating the sequence.
     */
    public void run() {
        try {
            if (Files.exists(file)) {
                List<String> lines = Files.readAllLines(file);
                for (String comando : lines) {
                    drone.sendCommand(comando);
                    Thread.sleep(TIME_WAITING);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error:"+ ex);
        } catch (InterruptedException ex) {
            System.out.println("Error:"+ ex);
        }
    }
}
