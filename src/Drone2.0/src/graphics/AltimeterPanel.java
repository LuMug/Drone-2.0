package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
     * Costruttore della classe. Permette di istanziare le due immagini e
     * richiama 'initComponents'.
     */
    public AltimeterPanel() {
        ImageIcon icon;
        icon = new ImageIcon(getClass().getClassLoader().getResource("Altimetro.png"));
        Image image = icon.getImage();
        imageBig = toBufferedImage(image);
        this.setLayout(null);
        
        alt = new JLabel("H: "+altitude + "");
        font1 = new Font("SansSerif", Font.BOLD, 10);
        alt.setFont(font1);
        alt.setForeground(Color.WHITE);
        alt.setLocation((int)(this.getWidth()/2.6), 
                (int)(this.getHeight()/1.8));
        
        
        add(alt);

    }

    @Override
    /**
     * Metodo che mi peremtte di disegnare le componenti. Non eseguo un
     * controllo sui gradi massimi poichè quest'immagine è libera di muoversi su
     * 360 gradi.
     */
    public void paintComponent(Graphics g) {
        //System.out.println("TOT: "+this.getWidth()+" | "+this.getHeight());
        //System.out.println("lab: "+alt.getX()+" | "+alt.getY());
        
        alt.setLocation((this.getWidth()/3), 
                (this.getHeight()/2));
        
        alt.setSize(this.getWidth()/4,this.getWidth()/6);
        font1=new Font("SansSerif", Font.BOLD, 20);
        alt.setFont(font1);
        
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

        if (imageBig != null) {
            image = resize(imageBig, panelH, panelW);

            int x = (this.getWidth() - image.getWidth()) / 2;
            int y = (this.getHeight() - image.getHeight()) / 2;

            g.drawImage(image, x, y, this);

        }

    }
}
