package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * This panel is the fourth component of the main interface where the drone'
 * state is shown: In this panel it's rappresented the altitude of the drone
 * @author Michea Colautti
 * @version 07.10.2021
 */
public class UpPanel extends Model {

    /**
     * I gradi di rotazione dell'immagine.
     */
    private int deg;


    /**
     * Dimensione del drone;
     */
    private int droneS;

    /**
     * Costruttore della classe. Permette di istanziare l'immagine.
     */
    public UpPanel() {
        //aggiunto riferimento a bin in class path e path di libreria
        ImageIcon icon;
        icon = new ImageIcon(getClass().getClassLoader().getResource("DroneSuperiore.png"));
        Image img = icon.getImage();
        imageBig = toBufferedImage(img);
    }

    /**
     * Metodo che mi peremtte di disegnare le componenti. Non eseguo un
     * controllo sui gradi massimi poichè quest'immagine è libera di muoversi su
     * 360 gradi.
     */
    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth(), getHeight());

        panelH = getHeight();
        panelW = getWidth();

        if (panelW >= panelH) {
            droneS = panelH;

        } else {
            droneS = panelW;
        }

        int droneHypo = (int) Math.sqrt(Math.pow(droneS, 2) + Math.pow(droneS, 2));

        /* Ho la dim massima del drone (la diagonale), ora devo trovare la
        differenza di dimensione tra quest'ultima e il lato del drone, per
        poi ridimensionare il drone di conseguenza.
         */
        droneS = droneS - (droneHypo - droneS);

        if (imageBig != null) {
            image = resizeImage(imageBig, droneS, droneS);

            int x = (getWidth() - image.getWidth()) / 2;
            int y = (getHeight() - image.getHeight()) / 2;

            if (deg != 0) {
                rotatedImage = rotate(image, deg);

                g.drawImage(
                        rotatedImage,
                        x - (int) (rotatedImage.getWidth() / 6.5),
                        y - (int) (rotatedImage.getHeight() / 6.5),
                        this
                );
            } else {
                g.drawImage(image, x, y, this);
            }
        }
    }
    
    public void moving(int yaw){
        deg=yaw;
        validate();
        repaint();
    }
}
