package drone;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *The drone class represents the abstract class of a drone
 *and is responsible for sending information from the client to it.
 * 
 * @author Alessandro Aloise, Gianni Grasso
 * @version 07.10.2021
 */
public class Drone {
    /**
     * The ip address of the drone.
     */
    private static final String DRONE_IP = "192.168.10.1";

    /**
     * The listening port of the drone commands.
     */
    private static final int COMMANDS_PORT = 8889;
    
    /**
     * The socket for the communication of the drone commands
     */
    private DatagramSocket socket;
    
    /**
     * The last command sent to the done.
     */
    protected String sentCommand;
    
        
    /**
     * Constructor method without parameters.
     */
    public Drone() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
    }
    
    
    /**
     * Send a command to the drone.
     * 
     * @param messageToSend the command to send to the drone
     */
    public void sendCommand(String messageToSend) {
        try {
            sentCommand = messageToSend;
            
            byte[] data = messageToSend.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(DRONE_IP), COMMANDS_PORT);
            socket.send(packet);
            
            DatagramPacket receivePacket = new DatagramPacket(new byte[256], new byte[256].length);
            socket.receive(receivePacket);
            
            String responseSentence = new String(receivePacket.getData());
            System.out.println(responseSentence);
        } catch (SocketException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
    }

    
    public static void main(String[] args) throws InterruptedException, UnknownHostException{
        Drone tello = new Drone();
        
        tello.sendCommand("command");
        tello.sendCommand("dsfd");
        
        tello.socket.close();
    }
}