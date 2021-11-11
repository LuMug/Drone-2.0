package drone.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Classe che si occupa di log.
 *
 * @author Alessandro Aloise
 * @version 11.03.2021
 */
public class Log {

    /**
     * Variabile per scrivere sul file.
     */
    public static FileWriter fw;

    /**
     * Variabile per creare il file.
     */
    public static File file;
    
    /**
     * Nome della cartella
     */
    private File theDir = new File("Log");

    /**
     * Metodo che si occupa di creare il file di log. File di log nome file:
     * Log_data.text
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
     * Metodo che si ocucpa di scrivere dentro il file.
     *
     * @param testo da scrivere
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
     * Metodo che si occupa di chiudere il file dopo averci scritto.
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
