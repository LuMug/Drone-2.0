package drone.command;

import drone.Drone;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author alesa
 */
public class CommandPanel extends javax.swing.JPanel {

    /**
     * It contains the instance of the drone.
     */
    protected Drone drone;
    
    
    /**
     * Contains the class to run the sequence.
     */
    private Sequence SequenceRun;
    
    
    /**
     * Defines whether a sequence is started or not.
     */
    private boolean started = false;
    
    /**
     * It serves to select either the recording or the execution of the sequence.
     */
    private boolean selecStatus =false;
    
    
    /**
     * Creates new form CommandPanel.
     */
    public CommandPanel() {
        initComponents();
        DefaultCaret caret = (DefaultCaret) commandsText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }
    
    /**
     * Serve ad aggiornare comandi Panel.
     *
     * @param command da scrivere
     */
    public void refreshCommands(String command) {
        
        commandsText.append(commandConversion(command));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        commandsText = new javax.swing.JTextArea();
        buttonPanel = new javax.swing.JPanel();
        recButtun = new javax.swing.JToggleButton();
        executeButton = new javax.swing.JToggleButton();

        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        commandsText.setEditable(false);
        commandsText.setColumns(20);
        commandsText.setRows(5);
        jScrollPane1.setViewportView(commandsText);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.GridLayout(1, 0));

        recButtun.setBackground(new java.awt.Color(255, 255, 255));
        recButtun.setText("REC");
        recButtun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recButtunActionPerformed(evt);
            }
        });
        buttonPanel.add(recButtun);

        executeButton.setBackground(new java.awt.Color(255, 255, 255));
        executeButton.setText("EXECUTE");
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(executeButton);

        add(buttonPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void recButtunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recButtunActionPerformed
        keyColor();
        
    }//GEN-LAST:event_recButtunActionPerformed

    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
        keyColor();
        chooseSequence();
    }//GEN-LAST:event_executeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTextArea commandsText;
    private javax.swing.JToggleButton executeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton recButtun;
    // End of variables declaration//GEN-END:variables

    private String commandConversion(String command) {
        String infoCommand="";
        String[] str = command.split(" ");
        if (Integer.parseInt(str[0]) < 0) {
            infoCommand = "Sinistra";
        }else{
             infoCommand = "Destra";
        }
        if(Integer.parseInt(str[1]) < 0){
            infoCommand += " Indetro ";
        }else{
             infoCommand += " Avanti ";
        }
        if(Integer.parseInt(str[2]) < 0){
            infoCommand += " Su ";
        }else{
             infoCommand += " Giù ";
        }
        if(Integer.parseInt(str[3]) < 0){
            infoCommand += "Bardata sinistra ";
        }else{
             infoCommand += "Bardata destra";
        }
        return infoCommand;
    }

    private void chooseSequence() {
        JFileChooser open = new JFileChooser();
        File directory = new File("SequenceDrone");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        open.setCurrentDirectory(directory);
        FileNameExtensionFilter drn = new FileNameExtensionFilter("Sequence file (*.sequence)", "sequence");
        open.setFileFilter(drn);
        open.showDialog(null, "Esegui");
        try {
            String fileName = open.getSelectedFile().getName();
            if (!started) {
            SequenceRun = new Sequence(fileName, drone);
            SequenceRun.start();
            started = true;
        }
        } catch (NullPointerException e) {
            System.out.println("error:"+ e);
        }
    }
    
    private void keyColor(){
        if (selecStatus) {
            executeButton.setBackground(Color.GRAY);
            recButtun.setBackground(Color.WHITE);
        }else{
            executeButton.setBackground(Color.WHITE);
            recButtun.setBackground(Color.GRAY);
        }
        selecStatus = !selecStatus;
    }
    
}
