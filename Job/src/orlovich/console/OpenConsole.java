package orlovich.console;

import java.io.IOException;
import java.util.Properties;

public class OpenConsole {

    public static void main(String[] args) throws IOException {

//        try {
//            Runtime rt = Runtime.getRuntime();
//            String cmd[] = {"cmd.exe", "/C", "start"};
//            Process proc = rt.exec(cmd);
//        }  catch(IOException ex) {
//            ex.printStackTrace();
//        }

//        Runtime.getRuntime().exec("cmd");

        Properties prop = System.getProperties();
        ProcessBuilder pb = null;
        if ("Linux".equals(prop.getProperty("os.name"))) {
            pb = new ProcessBuilder("xterm");
        } else {
            pb = new ProcessBuilder("cmd.exe", "/C", "start");
        }
        pb.start();
        System.out.println("hello");

    }

}

