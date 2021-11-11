package drone.tool;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

/**
 * Classe che si occupa di scrivere un file di log.
 *
 * @version 11.03.2021
 * @author Alessandro Aloise
 */
public class Status extends Thread {

    private Queue<String> statuBufferData;

    Map<String, Double> status = new HashMap<>();

    /**
     * Istanza della classe di log.
     */
    Log log = new Log();
    /**
     * Data formatter.
     */
    DateFormat dateFormat;

    /**
     * Variabile per prendere la data.
     */
    Date data = new Date();

    public void setStatuBufferData(Queue<String> statuBufferData) {
        this.statuBufferData = statuBufferData;
    }

    private boolean end = true;

    /**
     * Metodo run della thread.
     */
    public void run() {
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.ITALY);
        try {
            log.creazioneCarterlla();
            log.creazioneFile();
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }

        DatagramSocket socket;
        byte[] buf = new byte[256];
        try {
            socket = new DatagramSocket(8890);
            while (end) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                socket.receive(packet);
                if (!(received.equals(""))) {
                    try {
                        String[] values = received.split(";");
                        for (String value : values) {
                            String[] pair = value.split(":");
                            status.put(pair[0], Double.parseDouble(pair[1]));

                        }
                    } catch (Exception ex) {
                        
                    }
                }
                statuBufferData.add("pit:" + status.get("pitch").toString());
                statuBufferData.add("rol:" + status.get("roll").toString());
                statuBufferData.add("yaw:" + status.get("yaw").toString());
                statuBufferData.add("alt:" + status.get("h").toString());
                Thread.sleep(100);
                String info = received.substring(0, received.length()-4);
                String finale = dateFormat.format(data) + " " + info;
                try {
                    log.scritturaFile(finale);
                } catch (Exception ex) {
                    System.out.println("Error:" + ex);
                }
            }
        } catch (SocketException ex) {
            System.out.println("Error:" + ex);
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error:" + ex);
        }
    }

    /**
     * Method that returns the battery value.
     */
    public String getbatteria() {
        return status.get("bat").toString();
    }
}
