package graphics;

import static graphics.Model.toBufferedImage;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Michea Colautti
 * @version 07.10.2021
 */
public class FrontPanel extends Model{

    /**
     * Costruttore della classe. Permette di istanziare l'immagine.
     */
    public FrontPanel() {
        //aggiunto riferimento a bin in class path e path di libreria
        ImageIcon icon;
        icon = new ImageIcon(getClass().getClassLoader().getResource("DroneFrontale.png"));
        Image image = icon.getImage();
        imageBig=toBufferedImage(image);
    }



    /**
     * Metodo per il movimento dell'immagine. Aggiorna il valore
     * dell'inclinazione, verificando che i valori registrati dal drone non
     * superino l'inclinazione massima permessa dalla costante
     * ImageModel.MAXDEG.
     *
     * @param rotate è l'inclinazione in gradi.
     */
    public void moving(int rotate) {
        if (rotate < 0) {

            if (rotate >= -MAXDEG) {
                rotDeg = -rotate;
                validate();
                repaint();
            }
        } else {
            if (rotate <= MAXDEG) {
                rotDeg = -1 * rotate;
                validate();
                repaint();
            }
        }
    }
}
