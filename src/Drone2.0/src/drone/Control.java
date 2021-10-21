package drone;

import drone.command.CommandPanel;
import graphics.MainPanel;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author Alessandro Aloise
 */
public class Control extends Thread{

    private final Queue<String> commandsBuffer;

    public Control(Queue<String> commandsBuffer) {
       this.commandsBuffer = commandsBuffer;
    }
   
    public void run() {
       String command =commandsBuffer.remove();
       
    }

    
    public static void main(String[] args) {
        try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             
            // Istanziamento grafico .
            JFrame mainFrame = new JFrame();
            mainFrame.setTitle("Drone2.0");
            mainFrame.setSize(512, 256);
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            MainPanel mainFrameGraphics = new MainPanel();
            CommandPanel commandPanel = new CommandPanel();
            mainFrame.getContentPane().add(commandPanel, java.awt.BorderLayout.WEST);
            mainFrame.getContentPane().add(mainFrameGraphics, java.awt.BorderLayout.CENTER);
            mainFrame.setVisible(true);
            
            //Coda degli imput.
            Queue<String> commandsBuffer = new ArrayDeque<>();
            Control control = new Control(commandsBuffer);
            DroneAction tello = new DroneAction();
             
            commandsBuffer.add("command");
            commandsBuffer.add("takeoff");
            commandsBuffer.add("land");

            tello.socket.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            System.out.println("Error:" + e);
        }
    }
}
