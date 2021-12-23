package drone.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Class that deals with log management.
 *
 * @author Alessandro Aloise
 * @version 11.03.2021
 */
public class Log {

    /**
     * Variable for writing files.
     */
    public static FileWriter fw;

    /**
     * Variable for creating files.
     */
    public static File file;
    
    /**
     * The path to the file.
     */
    private File theDir = new File("Log");

    /**
     * Create the log file according to the following structure: Log_[data].text
     */
    public void creazioneFile() {
        try {
            Date data = new Date();
            DateFormat dateFormat;
            dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ITALY);
            String path = "log/Log_" + dateFormat.format(data).replace(' ', '_') + ".txt";
            file = new File(path);
            file.createNewFile();
            fw = new FileWriter(file);
        } catch (IOException ex) {
            System.out.println("Error file già esistente");
        }
    }

    /**
     * Method that performs file writing.
     *
     * @param testo the text to be written
     */
    public void scritturaFile(String testo) {
        try {
            fw.write(testo + '\n');
            fw.flush();

        } catch (IOException e) {
            System.out.println("Error: stringa non valida");
        }
    }

    /**
     * Method that closes the file after writing in it.
     */
    public void chiusuraFile() {
        try {
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error: stringa non valida");
        }
    }

    public void creazioneCarterlla() {
        if (!theDir.exists()) {
            try {
                theDir.mkdir();
            } catch (SecurityException ex) {
                System.out.println("Error:"+ ex);
            }
        }
    }

}
