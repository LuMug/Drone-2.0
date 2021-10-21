package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
    private final double altitude = 0;

    private Font font1;

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

        alt = new JLabel("H: " + altitude + "");
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

        if (panelW >= panelH) {
            panelW = panelH;

        } else {
            panelH = panelW;
        }

        //Get the piint where the image start
        int imgStartX = (getWidth() - panelW) / 2;
        int imgStartY = (getHeight() - panelH) / 2;

        if (imageBig != null) {
            image = resize(imageBig, panelW, panelH);

            //panel is on the field in the image when proportion are 3|2
            //also considered, trying again and again, the black rectagle dims
            alt.setLocation(imgStartX + panelW / 3 + panelW / 25, 
                    imgStartY + panelH / 2 + panelH / 10);

            //font 30 is ok when panelW is 330 330-->dim= panel/11
            font1 = new Font("SansSerif", Font.BOLD, panelW / 12);
            alt.setFont(font1);
            int x = (this.getWidth() - image.getWidth()) / 2;
            int y = (this.getHeight() - image.getHeight()) / 2;

            g.drawImage(image, x, y, this);

        }

    }
}
