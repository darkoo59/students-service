package utils;

import java.io.File;
import java.net.URL;

public class DataUtils {
    public static File readDataFile(String str) {
        try {
            URL dataURL = ClassLoader.getSystemResource(str);
            File file = new File(dataURL.toURI());
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
