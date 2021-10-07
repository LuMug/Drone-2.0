package drone;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *The drone class represents the abstract class of a drone
 *and is responsible for sending information from the client to it.
 * 
 * @author Alessandro Aloise, Gianni Grasso
 * @version 07.10.2021
 */
public class Drone extends Thread {
    
    /**
     * The ip address of the drone.
     */
    protected static final String DRONE_IP = "192.168.10.1";

    /**
     * The listening port of the drone commands.
     */
    protected static final int COMMANDS_PORT = 8889;
    
    
    /**
     * The client's communication port.
     */
    protected int clientPort;
    
    /**
     * The socket for the communication of the drone commands
     */
    protected DatagramSocket socket;
    
    /**
     * The data packet sent to the drone.
     */
    protected DatagramPacket packet;
    
    /**
     * The message received from the drone.
     */
    private String messageReceived;
    
    
    /**
     * Metodo costruttore personalizzato con 1 parametro.
     */
    public Drone() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
        clientPort = socket.getLocalPort();
        
        //status.start();
    }
    
    
    /**
     * Send a command to the drone.
     */
    public void sendCommand(String messageToSend) {
        try {
            byte[] data = messageToSend.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(DRONE_IP), COMMANDS_PORT);
            //Riprendere da qui
            
            socket.send(packet);
        } catch (SocketException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
    }
}
