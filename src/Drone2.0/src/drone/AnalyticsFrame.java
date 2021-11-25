package drone;

import java.awt.GridLayout;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Michea Colautti
 * @version 25/11/2021
 *
 */
public class AnalyticsFrame extends JFrame implements Runnable {

    /**
     * Queue for history.
     */
    private static volatile Queue<String> analyticsBufferData;

    public void setAnalyticsBufferData(Queue<String> analyticsBufferData) {
        this.analyticsBufferData = analyticsBufferData;

    }

    private JLabel lab;

    public AnalyticsFrame() {
        this.setLayout(new GridLayout(1, 2));
        this.setSize(200, 200);
        lab = new JLabel();
        this.add(lab);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        StringBuilder datas = new StringBuilder();
        while (true) {
            if (analyticsBufferData.size() > 0) {
                for (String data : analyticsBufferData) {
                    System.out.println(data);
                    System.out.println(analyticsBufferData.poll());
                    //datas.append(analyticsBufferData.poll());

                    try {
                        Thread.sleep(1000);
                        //lab.setText(datas.toString());
                    } catch (InterruptedException ex) {
                    }

                }
            }

        }

    }

}
