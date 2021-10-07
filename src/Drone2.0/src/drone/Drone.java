package drone;

/**
 *
 * @author Alessandro Aloise
 * @version 07.10.2021
 */
public class Drone extends Thread {
    
    /**
     * The value of ip.
     */
    protected final String ip = "192.168.10.1";

    /**
     * The value of portD.
     */
    protected final int portD = 8889;
    
    
    
    /**
     * Get the value of ip.
     *
     * @return the value of ip.
     */
    public String getIp() {
        return ip;
    }
    

    /**
     * Get the value of portD
     *
     * @return the value of portD
     */
    public int getPortD() {
        return portD;
    }
    
    
}
