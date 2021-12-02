/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drone;

import java.awt.Dimension;
import java.awt.KeyboardFocusManager;

/**
 *
 * 
 * @author gianni.grasso
 */
public class MainFrame extends javax.swing.JFrame {
    //private LinkedList<String> q1 = new LinkedList<String>();
    private KeyDispatcher kd;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        this.setMinimumSize(new Dimension(800, 400));
        initKeyListener();
    }

    private void initKeyListener() {
        kd = new KeyDispatcher();

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(kd);
    }
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commandPanel = new drone.command.CommandPanel();
        mainPanel = new graphics.MainPanel();
        toolBarPanel1 = new drone.tool.ToolBarPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drone2.0");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("Drone 2.0"); // NOI18N
        getContentPane().add(commandPanel, java.awt.BorderLayout.WEST);
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(toolBarPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public drone.command.CommandPanel commandPanel;
    public graphics.MainPanel mainPanel;
    private drone.tool.ToolBarPanel toolBarPanel1;
    // End of variables declaration//GEN-END:variables
}
