package testutilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TestUtilities {

    private static Properties defaultProps = new Properties();

    static {
        try {
//            String currentDir = System.getProperty("user.dir");
//            String filepath = currentDir + "\\src\\test\\resources\\config.properties";
//            FileInputStream in = new FileInputStream(filepath);
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream in = loader.getResourceAsStream("\\config.properties");
            defaultProps.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * gets key value from properties file
     * @param key value of the field that will be picked up from response json
     * @return values for specific key
     */
    public static String getProperty(String key) {
        return defaultProps.getProperty(key);
    }


}
