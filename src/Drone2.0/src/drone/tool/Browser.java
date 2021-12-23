package drone.tool;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class allows to call the live's script and open a browser page.
 * 
 * @author Michea Colautti
 * @version 22.12.2021
 *
 */
public class Browser {

    /**
     * This method checks the OS and than it start the right scrpit.
     * 
     * @throws IOException
     * @throws InterruptedException 
     */
    public void script() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("os")) {

            ProcessBuilder pb = new ProcessBuilder();
            pb.redirectErrorStream(true);
            String usrPath = System.getProperty("user.dir") + "/Live/Script/RunLiveMac.sh";
            pb.command("sh", "-c", usrPath);
            Process process = pb.start();
        } else {
            String usrPath = System.getProperty("user.dir") + "\\Live\\Script\\";
            String path = "cmd /c start " + usrPath + "RunLiveWin.bat";
            System.out.println(path);
            Runtime rn = Runtime.getRuntime();
            Process pr = rn.exec(path);

        }
    }

    /**
     * This method open a browser page on the URL
     * 'http://localhost:3000/index.html'
     */
    public void openBrowser() {
        String url = "http://localhost:3000/index.html";

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                System.out.println("Error:" + e);
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                System.out.println("Error:" + e);
            }
        }
    }
}
