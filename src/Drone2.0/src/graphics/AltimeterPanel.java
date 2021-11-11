package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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

    /**
     *
     * Font usato nel label.
     */
    private Font font1;

    /**
     * Padding dell'altimetro.
     */
    private static final int PAD = 40;

    /**
     * Valore per cui la lancetta è sullo 0.
     */
    private static final double MIN_ANGLE = 4.7;

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

        if (imageBig != null) {
            image = resize(imageBig, imageSize, imageSize);
            int x = (this.getWidth() - image.getWidth()) / 2;
            int y = (this.getHeight() - image.getHeight()) / 2;
            g.drawImage(image, x, y, this);

        }
        
        
        //Get the point where the image start
        int imgStartX = (getWidth() - imageSize) / 2;
        int imgStartY = (getHeight() - imageSize) / 2;
        //panel is on the field in the image when proportion are 3|2
        //also considered, trying again and again, the black rectagle dims
        alt.setLocation(imgStartX + imageSize / 3 + imageSize / 25,
                imgStartY + imageSize / 2 + imageSize / 10);

        //font 22 is ok when panelW is 330-->dim= panel/16
        font1 = new Font("SansSerif", Font.BOLD, imageSize / 17);
        alt.setFont(font1);
        alt.setText("H: " + altitude + " m");

        /*
        ImageIcon handIcon;
        handIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                getClass().getClassLoader().getResource("Lancetta.png")));
        Image handImage = handIcon.getImage();
        handImageBuff = toBufferedImage(handImage);
        handImageBuff = resize(handImageBuff, handW, handH);
        handImageBuff = rotate(handImageBuff, angle);
        g.drawImage(handImageBuff,
                getWidth()/2,
                getHeight()/2,
                this);
         */
        //angle goes from 4.7 to 11
        //one section is 0.63
        //1/1.63=1.58
        int handH = getHeight() - imgStartY * 2
                - (imageSize - (imageSize / 3)) + 15;
        int handW = handH / 15;

        altitude = 5.5;

        double angle = altitude / 1.58 + MIN_ANGLE;

        double xP = handH * Math.cos(angle) + getWidth() / 2;
        double yP = handH * Math.sin(angle) + getHeight() / 2;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(handW));
        g.setColor(Color.red);
        g2d.drawLine((int) xP, (int) yP, getWidth() / 2, getHeight() / 2);
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
