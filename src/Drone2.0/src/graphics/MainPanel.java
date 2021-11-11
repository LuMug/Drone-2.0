package graphics;

import java.awt.GridLayout;
import java.util.Queue;
import javax.swing.JPanel;

/**
 *
 * @author Michea Colautti
 * @version 07.10.2021
 */
public class MainPanel extends JPanel implements Runnable{
  
    
    /**
     * Il riferimento al pannello "imagePanelFront".
     */
    private FrontPanel frontPanel;
    
    /**
     * Il riferimento al pannello "imagePanelLat".
     */
    private SidePanel sidePanel;
    
    /**
     * Il riferimento al pannello "imagePanelUp".
     */
    private UpPanel upPanel;
    
    /**
     * Il riferimento al pannello "imagePanelAlt".
     */
    private AltimeterPanel altimeterPanel;

    /**
     * Valore del beccheggio.
     */
    private static volatile int pitch;
    
    /**
     * Valore dell'imbardata.
     */
    private static volatile int yaw;
    
    /**
     * Valore del rollio.
     */
    private static volatile int roll;
    
    /**
     * Valore dell'altitudine.
     */
    private static volatile int alt;

    /**
     * Queue for history.
     */
    private Queue<String> statusBufferData;

    
    public void setStatusBufferData(Queue<String> statusBufferData) {
        this.statusBufferData = statusBufferData;
    }
    
    
    /**
     * Metodo costruttore.
     */
    public MainPanel() {
        initComponents();
    }

    
     /**
     * Questo metodo inizializza tutti i componenti:
     * Imposta la dimensione del Frame, istanzia e aggiunge i pannelli 
     * al frame 
     */
    private void initComponents() {
        GridLayout ImageFrameLayout = new GridLayout(2, 2);
        setLayout(ImageFrameLayout);
        frontPanel = new FrontPanel();
        sidePanel = new SidePanel();
        upPanel = new UpPanel();
        altimeterPanel = new AltimeterPanel();
        add(frontPanel);
        add(sidePanel);
        add(upPanel);
        add(altimeterPanel);
    }
    


    /**
     * Setter dell'altitudine, richiamato da DronePk.Status
     * @param statAlt è il valore dell'altitudine.
     */
    public void setAlt(int statAlt) {
        alt = statAlt;
    }

    /**
     * Setter del beccheggio, richiamato da DronePk.Status
     * @param statPitch è il valore del beccheggio.
     */
    public void setPitch(int statPitch) {
        pitch = statPitch;
    }

    /**
     * Setter del rolllio, richiamato da DronePk.Status
     * @param statRoll è il valore del rollio.
     */
    public void setRoll(int statRoll) {
        roll = statRoll;
    }
    
    /**
     * Setter dell'imbardata, richiamato da DronePk.Status
     * @param statYaw è il valore dell'imbardata.
     */
    public void setYaw(int statYaw) {
        yaw = statYaw;
    }

    /**
     * Questo metodo, o Thread, permette di aggiornare i vari pannelli
     * presenti nel frame.
     * Dopo aver ottenuto i valori dai setter, esegue i metodi
     * predisposti per il movimento passando il valore adeguato.
     */
    public void run() {
        
        while(true){
            
            String status = statusBufferData.poll();
            String id=status.substring(0, 4);
            if(id.equals("pit:")){
                
                
            
            }else if(id.equals("rol:")){
                
            
            }else if(id.equals("yaw:")){
        
            }else if(id.equals("alt:")){
            
            
            }
                

            frontPanel.moving(roll);
            sidePanel.moving(pitch);
            altimeterPanel.setAltitude(alt);
            upPanel.deg = yaw;
            upPanel.validate();
            upPanel.repaint(); 
        }
    }
    
}

