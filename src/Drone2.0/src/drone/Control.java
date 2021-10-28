package drone;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import javax.swing.UIManager;

/**
 *
 * @author Alessandro Aloise
 */
public class Control extends Thread {

    private Queue<String> commandsBuffer;
    private Queue<String> commandsBufferOutputDrone;
    private Queue<String> commandsBufferOutputGraphics;

    public Control(Queue<String> commandsBuffer, Queue<String> commandsBufferOutputDrone, Queue<String> commandsBufferOutputGraphics) {
        this.commandsBuffer = commandsBuffer;
        this.commandsBufferOutputDrone = commandsBufferOutputDrone;
        this.commandsBufferOutputGraphics = commandsBufferOutputGraphics;
    }

    public void run() {
        try {
            String command = commandsBuffer.remove();
            commandsBufferOutputDrone.add(command);
            commandsBufferOutputGraphics.add(command);
        } catch (NoSuchElementException e) {
        }

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            //Creazione delle code
            Queue<String> commandsBufferInput = new ArrayDeque<>();
            Queue<String> commandsBufferOutputDrone = new ArrayDeque<>();
            Queue<String> commandsBufferOutputGraphics = new ArrayDeque<>();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);

            //Coda degli imput.
            Control control = new Control(commandsBufferInput, commandsBufferOutputDrone, commandsBufferOutputGraphics);
            mainFrame.setCommandBufferInput(commandsBufferInput);

            //coda degli output
            mainFrame.commandPanel.setCommandsBufferOutputGraphics(commandsBufferOutputGraphics);
            DroneAction action = new DroneAction(commandsBufferOutputDrone);

            //star Thread
            control.start();
            action.start();
            Thread threadCommandPanel = new Thread(mainFrame.commandPanel);
            threadCommandPanel.start();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            System.out.println("Error:" + e);
        }
    }
}
