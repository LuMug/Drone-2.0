package graphics;

import java.awt.GridLayout;
import java.util.Queue;
import javax.swing.JPanel;

/**
 * Main panel of the application, collects or the 4 panel.
 *
 * @author Michea Colautti
 * @version 07.10.2021
 */
public class MainPanel extends JPanel implements Runnable {

    /**
     * Reference to "imagePanelFront".
     */
    private FrontPanel frontPanel;

    /**
     * Reference to "imagePanelLat".
     */
    private SidePanel sidePanel;

    /**
     * Reference to "imagePanelUp".
     */
    private UpPanel upPanel;

    /**
     * Reference to "imagePanelAlt".
     */
    private AltimeterPanel altimeterPanel;

    /**
     * Queue for history.
     */
    private static volatile Queue<String> statusBufferData;

    /**
     * Setter of the queue
     *
     * @param statusBufferData the queue
     */
    public void setStatusBufferData(Queue<String> statusBufferData) {
        this.statusBufferData = statusBufferData;
    }

    /**
     * Class contructor, allows to istanciate all four panel and the layout.
     */
    public MainPanel() {
        GridLayout MainPanelLayout = new GridLayout(2, 2);
        setLayout(MainPanelLayout);
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
     * This method, or Thread, allows you to update the various panels in the
     * frame. in the frame. After getting the values from the setters, it
     * executes all the right methods, passing the appropriate value.
     */
    public void run() {
        boolean in = true;
        while (in) {
            String status = statusBufferData.poll();
            if (status != null) {
                String id = status.substring(0, 4);

                switch (id) {
                    case "pit:": {
                        double pitch = Double.parseDouble(status.substring(4,
                                status.length()));
                        sidePanel.moving((int) pitch);
                        break;
                    }
                    case "rol:": {
                        double roll = Double.parseDouble(status.substring(4,
                                status.length()));
                        frontPanel.moving((int) roll);
                        break;
                    }
                    case "yaw:": {
                        double yaw = Double.parseDouble(status.substring(4,
                                status.length()));
                        upPanel.moving((int) yaw);
                        break;

                    }
                    case "alt:": {
                        double alt = Double.parseDouble(status.substring(4,
                                status.length()));
                        altimeterPanel.setAltitude(alt);
                        break;

                    }
                }
            }
        }
    }
}
