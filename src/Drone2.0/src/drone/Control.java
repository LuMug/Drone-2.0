package drone;

import drone.command.Sequence;
import drone.tool.analytics.AnalyticsPanel;
import drone.tool.Status;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.UIManager;


/**
 * The Control class is the control class and is
 * responsible for receiving and forwarding drone data.
 *
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
     *
     * @param commandsBufferInput Input queue.
     * @param commandsBufferOutputDrone Dedicated queue for output of commands
     * to be sent.
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
                System.out.println("Ex" + ex);
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
            LinkedList<String> commandsBufferInput = new LinkedList<>();
            LinkedList<String> commandsBufferOutputDrone = new LinkedList<>();
            LinkedList<String> commandsBufferOutputGraphics = new LinkedList<>();
            LinkedList<String> statusBufferData = new LinkedList<>();
            LinkedList<String> analyticsBufferData = new LinkedList<>();

            //DeclarationKeyDispatcher
            MainFrame mainFrame = new MainFrame();
            AnalyticsPanel analyticsPanel=new AnalyticsPanel();
            LeapMotion leapMotion = new LeapMotion();
            DroneAction action = new DroneAction(commandsBufferOutputDrone);
            KeyDispatcher dispatcher = new KeyDispatcher();
            Sequence sequence = new Sequence();
            Thread threadCommandPanel = new Thread(mainFrame.commandPanel);
            Thread threadMainPanel = new Thread(mainFrame.mainPanel);
            Status status = new Status();
            mainFrame.setVisible(true);
 
            //Input queue.
            Control control = new Control(commandsBufferInput, commandsBufferOutputDrone, commandsBufferOutputGraphics);
            leapMotion.setCommandsBufferInput(commandsBufferInput);
            dispatcher.setCommandBufferInput(commandsBufferInput);
            sequence.setSequence(commandsBufferInput);

            //output queue.
            mainFrame.commandPanel.setCommandsBufferOutputGraphics(commandsBufferOutputGraphics);
            status.setStatusBufferData(statusBufferData);
            status.setAnalyticsBufferData(analyticsBufferData);  
            mainFrame.mainPanel.setStatusBufferData(statusBufferData);
            analyticsPanel.setAnalyticsBufferData(analyticsBufferData);        
                    
            //Star Thread.
            action.start();
            threadCommandPanel.start();
            control.start();
            status.start();
            threadMainPanel.start();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            System.out.println("Error:" + e);
        }
    }
}
