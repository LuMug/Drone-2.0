package graphics;

import java.awt.GridLayout;
import java.util.Queue;
import javax.swing.JPanel;

/**
 *
 * @author Michea Colautti
 * @version 07.10.2021
 */
public class MainPanel extends JPanel implements Runnable {

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
     * Queue for history.
     */
    private static volatile Queue<String> statusBufferData;

    public void setStatusBufferData(Queue<String> statusBufferData) {
        this.statusBufferData = statusBufferData;
    }

    /**
     * Metodo costruttore.
     */
    public MainPanel() {
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
     * Questo metodo, o Thread, permette di aggiornare i vari pannelli presenti
     * nel frame. Dopo aver ottenuto i valori dai setter, esegue i metodi
     * predisposti per il movimento passando il valore adeguato.
     */
    public void run() {
        while (true) {    
            String status = statusBufferData.poll();
            if (status != null) {
                String id = status.substring(0, 4);

                switch (id) {
                    case "pit:" -> {
                        double pitch = Double.parseDouble(status.substring(4,
                                status.length()));
                        sidePanel.moving((int)pitch);
                    }
                    case "rol:" -> {
                        double roll = Double.parseDouble(status.substring(4,
                                status.length()));
                        frontPanel.moving((int)roll);
                    }
                    case "yaw:" -> {
                        double yaw = Double.parseDouble(status.substring(4,
                                status.length()));
                        upPanel.moving((int)yaw);

                    }
                    case "alt:" -> {
                        double alt = Double.parseDouble(status.substring(4,
                                status.length()));
                        altimeterPanel.setAltitude(alt);
                    }
                }
            }
        }
    }
}
