package drone;


import java.awt.GridLayout;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Michea Colautti 
 * @version 25/11/2021

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
    
    public AnalyticsFrame(){
        this.setLayout(new GridLayout(1,2));
        lab= new JLabel();
        this.add(lab);
        
    
    }
    
    
    @Override
    public void run() {
        while(true){
            lab.setText(analyticsBufferData.poll());
        }
        
    }
    
     

}
