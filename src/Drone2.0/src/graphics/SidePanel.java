package graphics;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * This panel is the second component of the main interface where the drone'
 * state is shown: This panel shows the drone seen from the side. 
 * The image tilts according to its roll.
 *
 * @author Michea Colautti
 * @version 22.12.2021
 */
public class SidePanel extends Model {

    /**
     * Constructor of the class. Allows to instantiate the image.
     */
    public SidePanel() {
        //aggiunto riferimento a bin in class path e path di libreria
        ImageIcon icon;
        icon = new ImageIcon(getClass().getClassLoader().getResource("DroneLaterale.png"));
        Image image = icon.getImage();
        imageBig = toBufferedImage(image);
    }

    /**
     * Metodo per il movimento dell'immagine. Aggiorna il valore
     * dell'inclinazione.
     *
     * @param rotate è l'inclinazione in gradi.
     */
    public void moving(int rotate) {
        if (rotDeg < 0) {
            rotDeg = rotate;
            validate();
            repaint();

        } else {
            rotDeg = rotate;
            validate();
            repaint();

        }
    }
}
