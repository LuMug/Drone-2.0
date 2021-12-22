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
 * This panel is the fourth component of the main interface where the drone'
 * state is shown: In this panel it's rappresented the altitude of the drone. It
 * isn't like the other panel in this package, infact here there is not a proper
 * image of the drone, but a sketch of an altimeter. The altitude is displayed
 * with an hand and a written value.
 *
 * @author Michea Colautti
 * @version 22.12.2021
 */
public class AltimeterPanel extends Model {

    /**
     * This label contains the altitude value.
     */
    public JLabel alt;

    /**
     * This is the value with the altitude.
     */
    private double altitude = 0;

    /**
     *
     * This is the font used in the label.
     */
    private Font font1;

    /**
     * This is the margin between the altimeter and the panel.
     */
    private static final int PAD = 40;

    /**
     * This is the angle necessary to put the altimeter's hand on the start
     * value.witch
     */
    private static final double MIN_ANGLE = 4.7;

    /**
     * Constructor of the class. It allows to instantiate the two images and
     * calls the 'initComponents' method.
     */
    public AltimeterPanel() {
        ImageIcon icon;
        icon = new ImageIcon(getClass().getClassLoader().
                getResource("Altimetro.png"));
        Image image = icon.getImage();
        imageBig = toBufferedImage(image);
        initComponents();

    }

    /**
     * Initcomponent allowd to instantiate the JLabel and set the font.
     */
    private void initComponents() {

        alt = new JLabel("H: " + altitude + " m");
        font1 = new Font("Helvetica", Font.BOLD, 30);
        alt.setFont(font1);
        alt.setForeground(Color.WHITE);
        add(alt);
    }


    /**
     * Method that allows to draw the components. It's differen from the others
     * paint method: indeed here there isn't any check for the maxium degrees v
     * value, this image is free to move on 360 degrees.
     */
    @Override
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
            image = resizeImage(imageBig, imageSize, imageSize);
            int x = (this.getWidth() - image.getWidth()) / 2;
            int y = (this.getHeight() - image.getHeight()) / 2;
            g.drawImage(image, x, y, this);

        }

        //Get the point where the image start
        int imgStartX = (getWidth() - imageSize) / 2;
        int imgStartY = (getHeight() - imageSize) / 2;
        
        //The altitude label is over the correct spot if label is placed at
        //imagesize/3 for X axis, and imageStart/2 for Y axis.
        //also considered, trying again and again, the black rectagle dimension.
        alt.setLocation(imgStartX + imageSize / 3 + imageSize / 25,
                imgStartY + imageSize / 2 + imageSize / 10);

        //font 22 is ok when panelW is 330-->dim= panel/16
        font1 = new Font("Helvetica", Font.BOLD, imageSize / 17);
        alt.setFont(font1);
        alt.setText("H: " + altitude + " m");

        //angle goes from 4.7 to 11
        //one section is 0.63
        //1/0.63=1.58
        int handH = getHeight() - imgStartY * 2
                - (imageSize - (imageSize / 3)) + 15;

        int handW = handH / 15;
        double angle = altitude / 1.58 + MIN_ANGLE;

        double xP = handH * Math.cos(angle) + getWidth() / 2;
        double yP = handH * Math.sin(angle) + getHeight() / 2;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(handW));
        g.setColor(Color.red);
        g2d.drawLine((int) xP, (int) yP, getWidth() / 2, getHeight() / 2);
    }

    /**
     * Altitude setter method, converts the value from cm to meters and 
     * set the value.
     * @param newAlt the new value of the altitude.
     */
    public void setAltitude(double newAlt) {
        altitude = newAlt / 100;
        repaint();
    }
}
