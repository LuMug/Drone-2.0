/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drone;

import java.util.Queue;

/**
 *
 * @author alesa
 */
public class MainFrame extends javax.swing.JFrame {
    Queue<String> commandBufferInput;

    public void setCommandBufferInput(Queue<String> commandBufferInput) {
        this.commandBufferInput = commandBufferInput;
    }
    
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commandPanel = new drone.command.CommandPanel();
        mainPanel = new graphics.MainPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drone2.0");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("Drone 2.0"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        getContentPane().add(commandPanel, java.awt.BorderLayout.WEST);
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getExtendedKeyCode() == 87) {
            System.out.println("w");
            commandBufferInput.add("rc 0 70 0 0");
        }
        if (evt.getExtendedKeyCode() == 65) {
            commandBufferInput.add("rc -70 0 0 0");
            System.out.println("a");
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
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        commandBufferInput.add("rc 0 0 0 0");
    }//GEN-LAST:event_formKeyReleased

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
       
    }//GEN-LAST:event_formKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public drone.command.CommandPanel commandPanel;
    private graphics.MainPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
