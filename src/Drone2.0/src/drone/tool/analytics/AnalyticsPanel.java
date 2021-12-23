package drone.tool.analytics;

import static graphics.Model.resizeImage;
import static graphics.Model.toBufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Queue;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * This panel is where all the analytics are shown. 
 * 
 * @author Michea Colautti
 * @version 22.12.2021
 */

public class AnalyticsPanel extends JPanel implements Runnable {

    /**
     * Queue for analytics.
     */
    private static volatile Queue<String> analyticsBufferData;

    /**
     * JLabel with analytics.
     */
    private JLabel lab;

    /**
     * Font for JLabel with analytics.
     */
    private Font font1;

    /**
     * Bufferd image with analytics image.
     */
    private BufferedImage buffImg;

    /**
     * Setter method for analytics queue.
     *
     * @param analyticsBufferData
     */
    public void setAnalyticsBufferData(Queue<String> analyticsBufferData) {
        this.analyticsBufferData = analyticsBufferData;

    }

    /**
     * Constructor, it allows to define the layout, image, and the label.
     */
    public AnalyticsPanel() {
        this.setLayout(new GridLayout(1, 1));
        this.setSize(200, 200);
        lab = new JLabel();
        this.add(lab);
        ImageIcon icon;
        icon = new ImageIcon(getClass().getClassLoader().getResource("Analytics.png"));
        Image img = icon.getImage();
        buffImg = toBufferedImage(img);
    }

    /**
     * Run method, update the analytics string and therefor the label content.
     */
    @Override
    public void run() {

        while (true) {
            font1 = new Font("Helvetica", Font.BOLD, this.getHeight() / 27);
            lab.setFont(font1);
            lab.setBorder(new EmptyBorder(0, 20, 0, 0));
            if (analyticsBufferData.size() > 0) {
                lab.setText(analyticsBufferData.poll());
            }
        }
    }

    /**
     * Paint method, it resize and display the image and the label.
     * 
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        int panelW = getWidth();

        int iconW;
        int iconH;
        iconW = panelW - panelW / 2;
        iconH = iconW / 2;
        g.clearRect(0, 0, panelW, getHeight());

        g.setColor(Color.black);
        int x, y = 0;

        BufferedImage small;
        small = resizeImage(buffImg, (int) (iconW / 1.2), (int) (iconH / 1.2));

        x = (this.getWidth() - small.getWidth()) / 2;
        y = (this.getHeight() - small.getHeight()) / 2;
        g.drawImage(small, (int) (x + x * 0.5), y, this);
    }

}
