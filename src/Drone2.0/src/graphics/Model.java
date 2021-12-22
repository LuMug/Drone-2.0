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
 * This class represents the model of a panel. It also contains methods for
 * managing and modifying BufferdImages.
 *
 * @author Michea Colautti
 * @version 22.12.2021
 */
public class Model extends JPanel {

    /**
     * The original image, therefore large
     */
    public BufferedImage imageBig;

    /**
     * The rotated image.
     */
    public BufferedImage rotatedImage;

    /**
     * Original image resized.
     */
    public BufferedImage image;

    /**
     * Fixed value for panel height
     */
    public int panelH;

    /**
     * Fixed value for panel width
     */
    public int panelW;

    /**
     * The image rotated
     */
    public int rotDeg;

    /**
     * Empty constructor, all the class will have their contrucotr.
     */
    public Model() {
    }

    /**
     * This method allows to resize a BufferdImage.
     *
     * @param img is the image to be resized.
     * @param newW is the new width of the image.
     * @param newH is the new height of the image.
     * @return the resized image.
     */
    public static BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
        if (img == null) {
            return null;
        }
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage dimg;
        if (newW > 5 && newH > 5) {

            dimg = new BufferedImage(newW, newH, img.getType());
        } else {
            dimg = new BufferedImage(10, 10, img.getType());
        }
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();

        return dimg;
    }

    /**
     * This method allows to rotate a BuffeerdImage.
     *
     * @param img is the image to be rotated.
     * @param angle is the angle of rotation.
     * @return the rotated image.
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
     * Method useful for converting an image of type Image into a BufferedImage.
     *
     * @param img the Image image.
     * @return the image of type BufferedImage.
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
     * Method for drawing components on the frame. Fits both 'ImagePanelFront'
     * and 'ImagePanelLat'. It allows to resize images and rotate them.
     *
     * @param g is the default parameter for graphics.
     */
    @Override
    public void paintComponent(Graphics g) {
        panelH = getHeight();
        panelW = getWidth();

        int droneW;
        int droneH;
        droneW = panelW - panelW / 2;
        droneH = droneW / 4;

        int droneHypo = (int) Math.sqrt(Math.pow(droneW, 2) + Math.pow(droneH, 2));
        if (droneHypo >= panelH) {

            droneW = panelH;
            droneH = droneW / 4;
        }

        g.clearRect(0, 0, panelW, panelH);
        g.drawRect(0, 0, panelW, panelH);

        g.setColor(Color.black);
        int x, y = 0;

        if (imageBig != null) {

            image = resizeImage(imageBig, droneW, droneH);

            x = (this.getWidth() - image.getWidth()) / 2;
            y = (this.getHeight() - image.getHeight()) / 2;

            image = rotate(image, rotDeg);
            if (rotDeg > 0) {
                g.drawImage(image, x - image.getWidth() / 5, y - (int) (image.getHeight() / 3.4), this);
            } else if (rotDeg < 0) {
                g.drawImage(image, x + image.getWidth() / 5, y - (int) (image.getHeight() / 3.4), this);
            } else {
                g.drawImage(image, x, y, this);

            }
        }

    }
}
