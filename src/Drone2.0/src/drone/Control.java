package drone;

import drone.tool.Status;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.UIManager;

/**
 * Class that deals with splitting incoming and outgoing data.
 * @author Alessandro Aloise
 */
public class Control extends Thread {

    /**
     * Input queue.
     */
    private Queue<String> commandsBuffer;
    
    /**
     * Dedicated queue for output of commands to be sent.
     */
    private Queue<String> commandsBufferOutputDrone;
    
    /**
     * Output for command history.
     */
    private Queue<String> commandsBufferOutputGraphics;

    /**
     * Variable that manages the thread.
     */
    private boolean runFlag = true;
    
    /**
     * Constructor method to which all queues are passed.
     * @param commandsBuffer Imput queue.
     * @param commandsBufferOutputDrone Dedicated queue for output of commands to be sent.
     * @param commandsBufferOutputGraphics Output for command history.
     */
    public Control(Queue<String> commandsBufferInput, Queue<String> commandsBufferOutputDrone, Queue<String> commandsBufferOutputGraphics) {
        this.commandsBuffer = commandsBufferInput;
        this.commandsBufferOutputDrone = commandsBufferOutputDrone;
        this.commandsBufferOutputGraphics = commandsBufferOutputGraphics;
    }

    /**
     * Run method that takes care of separating the primary list.
     */
    @Override
    public void run() {
        while (runFlag) {
            try {            
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                System.out.println("Ex"+ ex);
            }
            String command = commandsBuffer.poll();
            if (command != null) {
                commandsBufferOutputDrone.add(command);
                commandsBufferOutputGraphics.add(command);
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            //Queue creation
            LinkedList<String> commandsBufferInput=new LinkedList<>();
            LinkedList<String> commandsBufferOutputDrone = new LinkedList<>();
            LinkedList<String> commandsBufferOutputGraphics = new LinkedList<>();
            LinkedList<String> statuBufferData = new LinkedList<>();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);

            //Imput queue.
            Control control = new Control(commandsBufferInput, commandsBufferOutputDrone, commandsBufferOutputGraphics);
            LeapMotion leapMotion = new LeapMotion();
            leapMotion.setCommandsBufferInput(commandsBufferInput);
            KeyDispatcher dispatcher = new KeyDispatcher();
            dispatcher.setCommandBufferInput(commandsBufferInput);
            
            //output queue.
            mainFrame.commandPanel.setCommandsBufferOutputGraphics(commandsBufferOutputGraphics);
            DroneAction action = new DroneAction(commandsBufferOutputDrone);

            //Star Thread.
            action.start();
            Thread threadCommandPanel = new Thread(mainFrame.commandPanel);
            threadCommandPanel.start();
            control.start();
            Status status = new Status();
            status.start();
            status.setStatuBufferData(statuBufferData);
            mainFrame.mainPanel.setStatusBufferData(statuBufferData);
            Thread threadMainPanel = new Thread(mainFrame.mainPanel);
            threadMainPanel.start();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            System.out.println("Error:" + e);
        }
    }
}
