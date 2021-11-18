package drone.command;

import java.awt.Color;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

/**
 * Panel that takes care of printing the history of commands sent to the drone on the screen. 
 * The panel also allows you to record a series of commands and execute an already recorded sequence.
 * 
 * @author Alessandro Aloise
 * @version  11.11.2021
 */
public class CommandPanel extends javax.swing.JPanel implements Runnable {

    /**
     * Contains the class to run the sequence.
     */
    private Sequence SequenceRun;

    /**
     * Defines whether a sequence is started or not.
     */
    private boolean started = false;

    /**
     * It serves to select either the recording or the execution of the
     * sequence.
     */
    private boolean selecStatus = false;

    /**
     *
     */
    private boolean flagRec = false;

    /**
     * Folder where the file will be saved. 
     */
    private File directory = new File("SequenceDrone");
    
    /**
     * Queue for history.
     */
    private Queue<String> commandsBufferOutputGraphics;
    
    /**
     * Queue for the recorded sequence.
     */
    private Queue<String> sequence = new LinkedList<>();
    
    private Record record = new Record(sequence);
    private String name;

    /**
     * Method used to set the queue references.
     * @param commandsBufferOutputGraphics Tail Reference.
     */
    public void setCommandsBufferOutputGraphics(Queue<String> commandsBufferOutputGraphics) {
        this.commandsBufferOutputGraphics = commandsBufferOutputGraphics;
    }

    /**
     * Creates new form CommandPanel.
     */
    public CommandPanel() {
        initComponents();
        DefaultCaret caret = (DefaultCaret) commandsText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    /**
     * Method that takes care of adding the last commands sent to the pane.
     *
     * @param command da scrivere
     */
    public void refreshCommands(String command) {
        int result = command.compareTo("rc 0 0 0 0");
        if (!(result == 0) ) {
            commandsText.append(commandConversion(command) + "\n");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
            String command = commandsBufferOutputGraphics.poll();
            if (command != null) {
                refreshCommands(command);
                sequence.add(command);
            }
        }
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
        if (!flagRec) {
            sequence.clear();
            flagRec = !flagRec;
            recButtun.setText("STOP");

        } else {
            flagRec = !flagRec;
            saveFile();
            recButtun.setText("REC");
            record.sequenceWriter(name);

        }
        //keyColor();

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

    /**
     * Method that does the conversion from drone command to more understandable commands.
     * @param command Command to translate.
     * @return Converted string.
     */
    private String commandConversion(String command) {
        String infoCommand = "";
        String[] str = command.split(" ");
        switch (str[0]) {
            case "rc":
                if (Integer.parseInt(str[1]) < 0) {
                    infoCommand = "Left ";
                } else if (Integer.parseInt(str[1]) > 0) {
                    infoCommand = "Right ";
                }
                if (Integer.parseInt(str[2]) < 0) {
                    infoCommand += " Back ";
                } else if (Integer.parseInt(str[2]) > 0) {
                    infoCommand += " Forward ";
                }
                if (Integer.parseInt(str[3]) < 0) {
                    infoCommand += " Up ";
                } else if (Integer.parseInt(str[3]) > 0) {

                    infoCommand += " Down ";
                }
                if (Integer.parseInt(str[4]) == 70) {
                    infoCommand += " Spin right ";
                } else if (Integer.parseInt(str[4]) == -70) {

                    infoCommand += " Spin Left ";
                }
                break;
            default:
                infoCommand =command;
            }

        return infoCommand.trim();
    }

    /**
     * Method for choosing the file to execute.
     */
    private void chooseSequence() {
        JFileChooser open = new JFileChooser();
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
                SequenceRun = new Sequence(fileName);
                SequenceRun.start();
                started = true;
            }
        } catch (NullPointerException e) {
        }
    }

    /**
     * Method that deals with coloring the keys in a correct way.
     */
    private void keyColor() {
        if (selecStatus) {
            executeButton.setBackground(Color.GRAY);
            recButtun.setBackground(Color.WHITE);
        } else {
            executeButton.setBackground(Color.WHITE);
            recButtun.setBackground(Color.GRAY);
        }
        selecStatus = !selecStatus;
    }

    /**
     * A method of saving a file.
     */
    private void saveFile() {
        try {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(directory);
            FileNameExtensionFilter drn = new FileNameExtensionFilter("Sequence file (*.sequence)", "sequence");
            fileChooser.setDialogTitle("Specify a file to save");
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            }
        } catch (NullPointerException e) {

        }
    }
}
