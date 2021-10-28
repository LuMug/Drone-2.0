package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Michea Colautti
 * @version 07.10.2021
 */
public class AltimeterPanel extends Model {

    /**
     * Label contenente l'altitudine e l'unità di misura.
     */
    public JLabel alt;

    /**
     * Valore contenente l'altitudine.
     */
    private double altitude = 0;

    private Font font1;

    /**
     * BufferdImage con la lancetta
     */
    private BufferedImage handImageBuff;

    private static final int PAD = 40;

    /**
     * Costruttore della classe. Permette di istanziare le due immagini e
     * richiama 'initComponents'.
     */
    public AltimeterPanel() {
        ImageIcon icon;
        icon = new ImageIcon(getClass().getClassLoader().getResource("Altimetro.png"));
        Image image = icon.getImage();
        imageBig = toBufferedImage(image);
        initComponents();

    }

    private void initComponents() {

        alt = new JLabel("H: " + altitude + " m");
        font1 = new Font("SansSerif", Font.BOLD, 30);
        alt.setFont(font1);
        alt.setForeground(Color.WHITE);
        add(alt);
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
        g.drawRect(0, 0, getWidth(), getHeight());

        panelH = getHeight();
        panelW = getWidth();

        int imageSize;
        if (panelW >= panelH) {
            imageSize = panelH - PAD;

        } else {
            imageSize = panelW - PAD;
        }

        //Get the point where the image start
        int imgStartX = (getWidth() - imageSize) / 2;
        int imgStartY = (getHeight() - imageSize) / 2;

        if (imageBig != null) {
            image = resize(imageBig, imageSize, imageSize);

            //panel is on the field in the image when proportion are 3|2
            //also considered, trying again and again, the black rectagle dims
            alt.setLocation(imgStartX + imageSize / 3 + imageSize / 25,
                    imgStartY + imageSize / 2 + imageSize / 10);

            //font 22 is ok when panelW is 330-->dim= panel/16
            font1 = new Font("SansSerif", Font.BOLD, imageSize / 17);
            alt.setFont(font1);
            alt.setText("H: " + "0.09" + " m");
            int x = (this.getWidth() - image.getWidth()) / 2;
            int y = (this.getHeight() - image.getHeight()) / 2;

            g.drawImage(image, x, y, this);

        }

        ImageIcon handIcon;
        handIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                getClass().getClassLoader().getResource("Lancetta.png")));
        Image handImage = handIcon.getImage();
        handImageBuff = toBufferedImage(handImage);

        int handH = getHeight() - imgStartY * 2
                - (imageSize - (imageSize / 3)) + 15;
        int handW = handH / 5;

        handImageBuff = resize(handImageBuff, handW, handH);

        int angle=90;
        handImageBuff = rotate(handImageBuff, angle);
        int[] newDims=getDims(handImageBuff, angle);
        g.drawImage(handImageBuff,
                getWidth()/2,
                getHeight()/2,
                this);
    }
    
    
   public int[] getDims(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);
        int[] newDims={newWidth-1, newHeight-1};
        return newDims;
    }

    /**
     * Metodo utile per convertire un immagine di tipo Image in una
     * BufferedImage.
     *
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
     * Metodo setter dell'altitudine. Similmente ai setter presenti in
     * ImageFrame questo metodo serve per aggiornare l'altitudine. Metodo
     * richimato da 'ImageFrame'.
     *
     * @param newAlt è il nuovo valore dell'altitudine.
     */
    public void setAltitude(double newAlt) {
        altitude = newAlt / 100;
        repaint();
    }
}
