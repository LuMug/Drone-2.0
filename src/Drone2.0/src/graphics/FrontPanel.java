package graphics;

import static graphics.Model.toBufferedImage;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * This panel is the first component of the main interface where the drone'
 * state is shown: This panel shows the drone seen from the front. 
 * The image tilts according to its roll.
 *
 * @author Michea Colautti
 * @version 22.12.2021
 */
public class FrontPanel extends Model{

    /**
     * Class contructor, allows to istanciate the image.
     */
    public FrontPanel() {
        
        ImageIcon icon;
        icon = new ImageIcon(getClass().getClassLoader().getResource("DroneFrontale.png"));
        Image image = icon.getImage();
        imageBig=toBufferedImage(image);
    }



    /**
     *  Method for moving the image. It update the value of the image 
     * inclination, verifying that the values ​​recorded by the drone are not
     * exceed the maximum inclination allowed by the constant
     * ImageModel.MAXDEG.
     *
     * @param rotate the inclinaton in dergees.
     */
    public void moving(int rotate) {
        if (rotate < 0) {

                rotDeg = -rotate;
                validate();
                repaint();
        } else {
                rotDeg = -1 * rotate;
                validate();
                repaint();                
        }
    }
}
