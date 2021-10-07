package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Michea Colautti
 * @version 07.10.2021
 */
public class UpPanel extends Model {
    

    /**
     * I gradi di rotazione dell'immagine.
     */
    public int deg;
    
    private ImageIcon icon;

    /**
     * Costruttore della classe. Permette di istanziare l'immagine.
     */
    public UpPanel() {
        //aggiunto riferimento a bin in class path e path di libreria
        ImageIcon icon;
        icon = new ImageIcon(getClass().getClassLoader().getResource("DroneSuperiore.png"));
        Image image = icon.getImage();
        imageBig=toBufferedImage(image);
    }

    @Override
    /**
     * Metodo che mi peremtte di disegnare le componenti. Non eseguo un
     * controllo sui gradi massimi poichè quest'immagine è libera di muoversi su
     * 360 gradi.
     */
    public void paintComponent(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        panelH = getHeight();
        panelW = getWidth();
        if (panelW > panelH) {
            panelW = panelH;

        } else {
            panelH = panelW;
        }

        if (imageBig != null) {
            image = resize(imageBig, panelW, panelH);
            int x = (this.getWidth() - image.getWidth()) / 2;
            int y = (this.getHeight() - image.getHeight()) / 2;

            rotatedImage = rotate(image, deg);
            g.drawImage(rotatedImage, x, y, this);
            
        }

    }
}

