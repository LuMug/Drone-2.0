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
 * Class used to write log files.
 *
 * @version 11.03.2021
 * @author Alessandro Aloise
 */
public class Status extends Thread {

    private Queue<String> statusBufferData;
    private Queue<String> analyticsBufferData;

    Map<String, Double> status = new HashMap<>();

    /**
     * Log Instance.
     */
    Log log = new Log();
    
    /**
     * Data formatter.
     */
    DateFormat dateFormat;

    /**
     * Variable containing the current date.
     */
    Date data = new Date();

    public void setStatusBufferData(Queue<String> statusBufferData) {
        this.statusBufferData = statusBufferData;
    }
    
   public void setAnalyticsBufferData(Queue<String> analyticsBufferData) {
        this.analyticsBufferData = analyticsBufferData;
    }

    private boolean end = true;

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
                statusBufferData.add("pit:" + status.get("pitch").toString());
                statusBufferData.add("rol:" + status.get("roll").toString());
                statusBufferData.add("yaw:" + status.get("yaw").toString());
                statusBufferData.add("alt:" + status.get("h").toString());
                
                analyticsBufferData.add("<html>"+
                    "Pitch: " + status.get("pitch").toString()
                +"<br>"+"Roll: " + status.get("roll").toString()
                +"<br>"+"Yaw: " + status.get("yaw").toString()
                +"<br>"+"Altitude: " + status.get("h").toString()
                +"<br>"+"Position x: " + status.get("vgx").toString()
                +"<br>"+"Position y: " + status.get("vgy").toString()
                +"<br>"+"Position z: " + status.get("vgz").toString()
                +"<br>"+"Acceleration x: " + status.get("agx").toString()
                +"<br>"+"Acceleration y: " + status.get("agy").toString()
                +"<br>"+"Acceleration z: " + status.get("agz").toString()
                +"<br>"+"Lowest temperature: " + status.get("templ").toString()
                +"<br>"+"Highest temperature: " + status.get("temph").toString()
                +"<br>"+"Time of flight: " + status.get("tof").toString()
                +"<br>"+"Batteryt: " + status.get("bat").toString()
                +"<br>"+"Baro: " + status.get("baro").toString()
                +"<br>"+"Time of engine use : " + status.get("time").toString()+"</html>");
               

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
}
