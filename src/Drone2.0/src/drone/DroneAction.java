package drone;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Queue;

/**
 * The drone class represents the abstract class of a drone and is responsible
 * for sending information from the client to it.
 *
 * @author Alessandro Aloise, Gianni Grasso
 * @version 07.10.2021
 */
public class DroneAction extends Thread {

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
    protected DatagramSocket socket;

    protected boolean firstSend = true;

    private Queue<String> commandsBufferOutputDrone;

    /**
     * The constructor of the DroneAction class with a list as a parameter.
     *
     * @param commandsBufferOutputDrone the queue of the dorne.
     */
    public DroneAction(Queue<String> commandsBufferOutputDrone) {
        try {
            this.commandsBufferOutputDrone = commandsBufferOutputDrone;
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
    }

    /**
     * The run method of the.
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
            }
            String command = commandsBufferOutputDrone.poll();
            if (command != null) {
                sendCommand(command);
            }
        }

    }

    /**
     * Send a command to the drone.
     *
     */
    public void sendCommand(String command) {
        try {
            if (firstSend) {
                String firstSendCommand = "command";
                byte[] data = firstSendCommand.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(DRONE_IP), COMMANDS_PORT);
                socket.send(packet);
                firstSend = false;
            }
            byte[] data = command.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(DRONE_IP), COMMANDS_PORT);
            socket.send(packet);

            DatagramPacket receivePacket = new DatagramPacket(new byte[256], new byte[256].length);
        } catch (SocketException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERRORE: " + ex.getMessage());
        }
    }
}
