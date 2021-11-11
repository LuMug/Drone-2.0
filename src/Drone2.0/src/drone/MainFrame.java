/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drone;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;

/**
 *
 * @author alesa
 */
public class MainFrame extends javax.swing.JFrame implements KeyListener {

    Queue<String> commandBufferInput;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        this.setMinimumSize(new Dimension(800, 400));

        this.requestFocus();
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    public void setCommandBufferInput(Queue<String> commandBufferInput) {
        this.commandBufferInput = commandBufferInput;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commandPanel = new drone.command.CommandPanel();
        mainPanel = new graphics.MainPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drone2.0");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("Drone 2.0"); // NOI18N
        getContentPane().add(commandPanel, java.awt.BorderLayout.WEST);
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public drone.command.CommandPanel commandPanel;
    public graphics.MainPanel mainPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getExtendedKeyCode() == 87) {
            commandBufferInput.add("rc 0 70 0 0");
        }
        if (evt.getExtendedKeyCode() == 65) {
            commandBufferInput.add("rc -70 0 0 0");
        }
        if (evt.getExtendedKeyCode() == 83) {
            commandBufferInput.add("rc 0 -70 0 0");
        }
        if (evt.getExtendedKeyCode() == 68) {
            commandBufferInput.add("rc 70 0 0 0");
        }
        if (evt.getExtendedKeyCode() == 37) {
            commandBufferInput.add("rc 0 0 0 -70");
        }
        if (evt.getExtendedKeyCode() == 39) {
            commandBufferInput.add("rc 0 0 0 70");
        }
        if (evt.getExtendedKeyCode() == 40) {
            commandBufferInput.add("rc 0 0 -79 0");
        }
        if (evt.getExtendedKeyCode() == 38) {
            commandBufferInput.add("rc 0 0 70 0");
        }
        if (evt.getExtendedKeyCode() == 32) {
            commandBufferInput.add("rc 0 0 0 0");
        }
        if (evt.getExtendedKeyCode() == 84) {
            commandBufferInput.add("takeoff");
        }
        if (evt.getExtendedKeyCode() == 76) {
            commandBufferInput.add("land");
        }
        if (evt.getExtendedKeyCode() == 13) {
            commandBufferInput.add("emergency");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        commandBufferInput.add("rc 0 0 0 0");
    }
}
