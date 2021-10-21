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
public class Control {

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
            
            Drone tello = new Drone();

            tello.sendCommand("command");
            tello.sendCommand("takeoff");
            tello.sendCommand("land");

            tello.socket.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            System.out.println("Error:" + e);
        }
    }
}
