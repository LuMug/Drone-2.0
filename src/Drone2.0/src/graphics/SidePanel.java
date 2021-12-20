package graphics;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Michea Colautti
 * @version 07.10.2021
 */
public class SidePanel extends Model {

    /**
     * Costruttore della classe. Permette di istanziare l'immagine.
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
