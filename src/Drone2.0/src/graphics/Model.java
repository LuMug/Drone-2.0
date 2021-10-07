package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Michea Colautti
 * @version 07.10.2021
 */
public class Model extends JPanel {

    /**
     * L'immagine originale, quindi grande.
     */
    public BufferedImage imageBig;

    /**
     * L'immagine ruotata.
     */
    public BufferedImage rotatedImage;

    /**
     * L'immagine originale ridimensionata.
     */
    public BufferedImage image;

    /**
     * Valore fisso dell'altezza del pannello.
     */
    public int panelH;

    /**
     * Valore fisso della la argezza del pannello.
     */
    public int panelW;

    /**
     * I gradi di rotazione delle immagini.
     */
    public int rotDeg;

    /**
     * I gradi massimi rotazione delle immagini.
     */
    public static final int MAXDEG = 40;

    /**
     * Costruttore vuoto, ogni casse figlia avrà il suo personalizzato.
     */
    public Model() {
    }

    /**
     * Questo metodo mi peremtte di ridimensionare una BufferdImage.
     *
     * @param img è l'immagine che va ridimensionata.
     * @param newW è la nuova larghezza dell'immagine.
     * @param newH è la nuova altezza dell'immagine.
     * @return l'immagine ridimensionata.
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        if (img == null) {
            return null;
        }
        int w = img.getWidth();
        int h = img.getHeight();
        
        if(newW<0){
            newW=10;
        }
        if(newH<0){
            newH=10;
        }

        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

    /**
     * Questo metodo mi permette di ruotare una BuffeerdImage.
     *
     * @param img è l'immagine da ruotare.
     * @param angle è l'angolo di rotazione.
     * @return l'immagine ruotata.
     */
    public BufferedImage rotate(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, this);
        g2d.dispose();
        return rotated;
    }

    /**
     * Metodo utile per convertire un immagine di tipo Image in
     * una BufferedImage.
     * @param img l'immagine di tipo Image
     * @return l'immagine di tipo BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), 
                img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    /**
     * Metodo per disegnare le componenti sul frame. Si adatta sia per
     * 'ImagePanelFront' che per 'ImagePanelLat'. Permette quindi di
     * ridimensionare le immagini e di ruotarle.
     *
     * @param g è il parametro di default per la grafica.
     */
    public void paintComponent(Graphics g) {
        panelH = getHeight();
        panelW = getWidth();

        g.clearRect(0, 0, panelW, panelH);

        panelH = (int) (panelW / 4);

        g.setColor(Color.black);
        int x, y = 0;

        if (imageBig != null) {
            int resizeW=40;
 
            image = resize(imageBig, panelW-resizeW, (int)panelH-resizeW/4);

            x = (this.getWidth() - image.getWidth()) / 2;
            y = (this.getHeight() - image.getHeight()) / 2;

            image = rotate(image, rotDeg);
            if (rotDeg > 0) {
                
                g.drawImage(image, x, y - rotDeg, this);
            } else {
                g.drawImage(image, x, y + rotDeg, this);
            }
        }

    }
}

