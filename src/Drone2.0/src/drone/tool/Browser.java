package drone.tool;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Alessandro Aloise, Michea Colautti
 * @version 02/12/2021
 *
 */
public class Browser {

    public void script() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("os")) {

            ProcessBuilder pb = new ProcessBuilder();
            pb.redirectErrorStream(true);

            String usrPath = System.getProperty("user.dir") + "/Live/Script/RunLiveMac.sh";
            pb.command("sh", "-c", usrPath);
            Process process = pb.start();


            /*int in = -1;
            InputStream is = process.getInputStream();
            try {
                while ((in = is.read()) != -1) {
                    System.out.print((char) in);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            int exitCode = process.waitFor();
            System.out.println("Exited with " + exitCode);*/
        } else {
            String usrPath = System.getProperty("user.dir") + "/Live/Script/";
            String path = "cmd /c start" + usrPath + "RunLiveWin.bat";
            Runtime rn = Runtime.getRuntime();
            Process pr = rn.exec(path);

        }
    }

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
