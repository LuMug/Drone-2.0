package drone.command;

import drone.Drone;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author alesa
 */
public class CommandPanel extends javax.swing.JPanel {

    /**
     * Contiene l'istanza del drone.
     */
    protected Drone drone;
    
    
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

        buttonPanel.setLayout(new java.awt.GridLayout());

        recButtun.setBackground(new java.awt.Color(255, 255, 255));
        recButtun.setText("REC");
        recButtun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recButtunActionPerformed(evt);
            }
        });
        buttonPanel.add(recButtun);

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
        // TODO add your handling code here:
    }//GEN-LAST:event_recButtunActionPerformed

    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_executeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTextArea commandsText;
    private javax.swing.JToggleButton executeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton recButtun;
    // End of variables declaration//GEN-END:variables

    private String commandConversion(String command) {
         String[] str = command.split(" ");
        if (str[0].equals("0")) {
            return "avanti";
        }else{
            return "...";
        }
    }
}
